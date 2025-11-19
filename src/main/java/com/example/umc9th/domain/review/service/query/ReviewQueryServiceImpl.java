package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.restaurant.entity.Restaurant;
import com.example.umc9th.domain.restaurant.exception.RestaurantException;
import com.example.umc9th.domain.restaurant.exception.code.RestaurantErrorCode;
import com.example.umc9th.domain.restaurant.repository.RestaurantRepository;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.exception.ReviewException;
import com.example.umc9th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;


    @Override
    public List<Review> searchMyReviews(Long memberId, String type, String query){
        String safeType  = (type  == null) ? "all" : type.trim();
        String safeQuery = (query == null) ? ""    : query.trim();

        QReview review = QReview.review;

        // BooleanBuilder 선언
        BooleanBuilder builder = new BooleanBuilder();

        // BooleanBuilder 사용

        // 1) 내가 작성한 리뷰
        builder.and(review.member.id.eq(memberId));

        // 동적 쿼리 : 조회 조건
        switch (type) {
            case "all" -> {
                // 추가 필터 없음
            }
            case "restaurant" -> {
                if (query.isEmpty()) throw new ReviewException(ReviewErrorCode.MISSING_QUERY);
                builder.and(review.restaurant.restaurantName.contains(query));
            }
            case "rating" -> {
                if (query.isEmpty()) throw new ReviewException(ReviewErrorCode.MISSING_QUERY);
                applyRatingFilterOrThrow(builder, review, query);
            }
            case "both" -> {
                if (query.isEmpty() || !query.contains("&")) {
                    throw new ReviewException(ReviewErrorCode.INVALID_BOTH_QUERY);
                }
                String[] parts = query.split("&", 2);
                String name = parts[0].trim();
                String ratingStr = parts[1].trim();
                if (name.isEmpty() || ratingStr.isEmpty()) {
                    throw new ReviewException(ReviewErrorCode.INVALID_BOTH_QUERY);
                }
                builder.and(review.restaurant.restaurantName.contains(name));
                applyRatingFilterOrThrow(builder, review, ratingStr);
            }
            default -> throw new ReviewException(ReviewErrorCode.INVALID_TYPE);
        }


        List<Review> reviewList = reviewRepository.searchReview(builder);

        return reviewList;
    }

    @Override
    public ReviewResDTO.ReviewPreViewListDTO findReview(String restaurantName, Integer page){

        // 가게를 가져온다 (가게 존재 여부 검증)
        Restaurant restaurant = restaurantRepository.findByRestaurantName(restaurantName)
                // 없으면 예외 터뜨리기
                .orElseThrow(() -> new RestaurantException(RestaurantErrorCode.NOT_FOUND));
        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Review> result = reviewRepository.findAllByRestaurant(restaurant, pageRequest);
        return ReviewConverter.toReviewPreViewListDTO(result);
    }

    // 내가 작성한 리뷰 목록 페이징
    @Override
    public ReviewResDTO.MyReviewListDTO findMyReviews(Long memberId, Integer page) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 프론트는 1 이상 전달 PageRequest는 0부터
        int pageIndex = page - 1;
        int size = 10; // 한 페이지당 10개

        PageRequest pageRequest = PageRequest.of(pageIndex, size);

        Page<Review> result = reviewRepository.findByMember(member, pageRequest);

        return ReviewConverter.toMyReviewPageDTO(result);
    }

    private void applyRatingFilterOrThrow(BooleanBuilder builder, QReview review, String ratingText) {
        if (ratingText == null || ratingText.isEmpty()) return;

        double base;
        try {
            base = Double.parseDouble(ratingText);
        } catch (NumberFormatException e) {
            throw new ReviewException(ReviewErrorCode.INVALID_RATING);
        }

        if (base < 0.0 || base > 5.0) {
            throw new ReviewException(ReviewErrorCode.INVALID_RATING);
        }

        if (base >= 5.0) {
            builder.and(review.rating.eq(5.0));
        } else if (base >= 0.0) {
            double lower = base;
            double upper = Math.min(5.0, base + 1.0);
            builder.and(review.rating.goe(lower));
            builder.and(review.rating.lt(upper));
        }
    }
}
