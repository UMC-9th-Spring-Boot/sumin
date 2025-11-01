package com.example.umc9th.domain.review.dto.res;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    @Getter
    public static class MyReview {
        private Long reviewId;
        private String restaurantName;
        private String content;
        private Double rating;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    public static class MyReviews {
        private List<MyReview> reviews;
        private Integer totalCount;
    }
}
