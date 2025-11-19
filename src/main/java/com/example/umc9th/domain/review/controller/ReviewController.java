package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.command.ReviewCommandService;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.domain.review.service.query.ReviewQueryServiceImpl;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController implements ReviewControllerDocs{
    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    // 가게의 리뷰 목록 조회
    @Override
    @GetMapping("/reviews")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam String restaurantName,
            @RequestParam Integer page
    ){

        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findReview(restaurantName, page));
    }

    // 내 리뷰 조회
    // /api/reviews/me?memberId=7
    // /api/reviews/me?memberId=7&type=restaurant&query=반이학생마라탕마라반
    // /api/reviews/me?memberId=7&type=rating&query=4
    // /api/reviews/me?memberId=7&type=both&query=반이학생마라탕마라반&4
    @Override
    @GetMapping("/reviews/me")
    public ApiResponse<ReviewResDTO.MyReviewListDTO> myReviews(
            @RequestParam Long memberId,
            @RequestParam @Valid @ValidPage Integer page
    ) {
        ReviewResDTO.MyReviewListDTO dto = reviewQueryService.findMyReviews(memberId, page);
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
