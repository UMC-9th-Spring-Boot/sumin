package com.example.umc9th.domain.review.service.command;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.restaurant.entity.Restaurant;
import com.example.umc9th.domain.restaurant.exception.RestaurantException;
import com.example.umc9th.domain.restaurant.exception.code.RestaurantErrorCode;
import com.example.umc9th.domain.restaurant.repository.RestaurantRepository;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;

    // 미션용 하드코딩 유저 ID
    private static final Long HARDCODED_MEMBER_ID = 1L;

    @Override
    @Transactional
    public ReviewResDTO.CreateReview createReview(Long restaurantId, ReviewReqDTO.CreateReview dto) {
        // 가게 존재 여부 검증
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantException(RestaurantErrorCode.NOT_FOUND));

        // 회원 존재 여부 검증
        Member member = memberRepository.findById(HARDCODED_MEMBER_ID)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 리뷰 엔티티 생성 및 저장
        Review review = ReviewConverter.toReview(dto, member, restaurant);
        Review saved = reviewRepository.save(review);

        return ReviewConverter.toCreateReview(saved);
    }
}
