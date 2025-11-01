package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.query.ReviewQueryServiceImpl;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewQueryServiceImpl reviewQueryService;


    // /api/reviews/me?memberId=7
    // /api/reviews/me?memberId=7&type=restaurant&query=반이학생마라탕마라반
    // /api/reviews/me?memberId=7&type=rating&query=4
    // /api/reviews/me?memberId=7&type=both&query=반이학생마라탕마라반&4
    @GetMapping("/me")
    public ApiResponse<ReviewResDTO.MyReviews> myReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String query
    ) {
        List<Review> list = reviewQueryService.searchMyReviews(memberId, type, query);
        ReviewResDTO.MyReviews dto = ReviewConverter.toMyReviews(list);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, dto);
    }



}
