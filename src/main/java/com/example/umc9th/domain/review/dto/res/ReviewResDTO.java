package com.example.umc9th.domain.review.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    // 내 리뷰 목록 + 페이징 정보
    @Builder
    public record MyReviewListDTO(
            List<MyReview> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    @Getter
    public static class MyReviews {
        private List<MyReview> reviews;
        private Integer totalCount;
    }

    // 가게에 리뷰 작성 응답 DTO
    @Builder
    public record CreateReview(
            Long reviewId,
            Long restaurantId,
            Long memberId,
            String reviewContent,
            Double rating,
            LocalDateTime createdAt
    ){}

    @Builder
    public record ReviewPreViewListDTO(
            List<ReviewPreViewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    // static class로 구현
//    @Builder
//    @Getter
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class ReviewPreViewListDTO(
//            List<ReviewPreViewDTO> reviewList,
//            Integer listSize,
//            Integer totalPage,
//            Long totalElements,
//            Boolean isFirst,
//            Boolean isLast
//    ){}

    @Builder
    public record ReviewPreViewDTO(
            String ownerNickname,
            Double score,
            String body,
            LocalDate createdAt
    ){}




}
