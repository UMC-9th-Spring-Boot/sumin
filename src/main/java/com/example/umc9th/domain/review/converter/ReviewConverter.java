package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.restaurant.entity.Restaurant;
import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        List<ReviewResDTO.MyReview> reviews =
                list == null
                        ? List.of()
                        : list.stream()
                        .filter(Objects::nonNull)
                        .map(ReviewConverter::toMyReview)
                        .toList();

        return ReviewResDTO.MyReviews.builder()
                .reviews(reviews)
                .totalCount(reviews.size())
                .build();
    }

    public static ReviewResDTO.MyReviewListDTO toMyReviewPageDTO(Page<Review> result) {
        return ReviewResDTO.MyReviewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toMyReview)
                        .toList())
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    // 리뷰 생성 : DTO -> Entity
    public static Review toReview(ReviewReqDTO.CreateReview dto, Member member, Restaurant restaurant) {
        return Review.builder()
                .reviewContent(dto.reviewContent())
                .rating(dto.rating())
                .member(member)
                .restaurant(restaurant)
                .build();
    }

    // 리뷰 생성 응답용 : Entity -> DTO
    public static ReviewResDTO.CreateReview toCreateReview(Review review) {
        return ReviewResDTO.CreateReview.builder()
                .reviewId(review.getId())
                .restaurantId(review.getRestaurant().getId())
                .memberId(review.getMember().getId())
                .reviewContent(review.getReviewContent())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // result -> DTO
    public static ReviewResDTO.ReviewPreViewListDTO toReviewPreViewListDTO(Page<Review> result) {
        return ReviewResDTO.ReviewPreViewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewPreviewDTO)
                        .toList())
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }


    public static ReviewResDTO.ReviewPreViewDTO toReviewPreviewDTO(
            Review review
    ){
        return ReviewResDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getRating())
                .body(review.getReviewContent())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }
}
