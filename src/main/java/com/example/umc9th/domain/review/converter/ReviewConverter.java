package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewConverter {
    public static ReviewResDTO.MyReview toMyReview(Review review) {
        return ReviewResDTO.MyReview.builder()
                .reviewId(review.getId())
                .restaurantName(review.getRestaurant().getRestaurantName())
                .content(review.getReviewContent())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.MyReviews toMyReviews(List<Review> list) {
        List<ReviewResDTO.MyReview> reviews = new ArrayList<>();
        if (list != null) {
            for (Review review : list) {
                if (review != null) {
                    reviews.add(toMyReview(review));
                }
            }
        }
        return ReviewResDTO.MyReviews.builder()
                .reviews(reviews)
                .totalCount(reviews.size())
                .build();
    }
}
