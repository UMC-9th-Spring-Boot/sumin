package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.command.ReviewCommandService;
import com.example.umc9th.domain.review.service.query.ReviewQueryServiceImpl;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {
    private final ReviewQueryServiceImpl reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    // 내 리뷰 조회
    // /api/reviews/me?memberId=7
    // /api/reviews/me?memberId=7&type=restaurant&query=반이학생마라탕마라반
    // /api/reviews/me?memberId=7&type=rating&query=4
    // /api/reviews/me?memberId=7&type=both&query=반이학생마라탕마라반&4
    @GetMapping("/reviews/me")
    public ApiResponse<ReviewResDTO.MyReviews> myReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String query
    ) {
        List<Review> list = reviewQueryService.searchMyReviews(memberId, type, query);
        ReviewResDTO.MyReviews dto = ReviewConverter.toMyReviews(list);
        return ApiResponse.onSuccess(ReviewSuccessCode.MY_REVIEWS_FOUND, dto);
    }

    // 가게에 리뷰 추가하기
    @Operation(summary = "가게에 리뷰 추가하기", description = "특정 가게(storeId)에 대한 리뷰를 작성합니다.")
    @PostMapping("/restaurant/{restaurantId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReview> createReview(
            @PathVariable Long restaurantId,
            @Valid @RequestBody ReviewReqDTO.CreateReview dto
    ) {
        ReviewResDTO.CreateReview response = reviewCommandService.createReview(restaurantId, dto);
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATED, response);
    }

}
