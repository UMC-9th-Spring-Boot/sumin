package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewQueryService {
    private final ReviewRepository reviewRepository;


    public List<Review> searchMyReviews(Long memberId, String type, String query){
        QReview review = QReview.review;

        // BooleanBuilder 선언
        BooleanBuilder builder = new BooleanBuilder();

        // BooleanBuilder 사용

        // 1) 내가 작성한 리뷰
        builder.and(review.member.id.eq(memberId));

        // 동적 쿼리 : 조회 조건
        if (type.equals("restaurant")) {
            builder.and(review.restaurant.restaurantName.contains(query));
        }
        if (type.equals("rating")) {
            double base = Double.parseDouble(query.trim());
            if (base >= 5.0) {
                // 정확히 5.0만
                builder.and(review.rating.eq(5.0));
            } else if (base >= 0.0) {
                // n점대
                double lower = base;
                double upper = Math.min(5.0, base + 1.0);
                builder.and(review.rating.goe(lower));
                builder.and(review.rating.lt(upper));
            }

        }
        if(type.equals("both")) {
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            builder.and(review.restaurant.restaurantName.contains(query));

            double base = Double.parseDouble(query.trim());
            if (base >= 5.0) {
                // 정확히 5.0만
                builder.and(review.rating.eq(5.0));
            } else if (base >= 0.0) {
                // n점대
                double lower = base;
                double upper = Math.min(5.0, base + 1.0);
                builder.and(review.rating.goe(lower));
                builder.and(review.rating.lt(upper));
            }

        }


        List<Review> reviewList = reviewRepository.searchReview(builder);

        return reviewList;
    }

}
