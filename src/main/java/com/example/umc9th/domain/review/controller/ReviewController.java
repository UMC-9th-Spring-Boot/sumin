package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewQueryService reviewQueryService;

    // /api/reviews/me?memberId=7
    // /api/reviews/me?memberId=7&type=restaurant&query=반이학생마라탕마라반
    // /api/reviews/me?memberId=7&type=rating&query=4
    // /api/reviews/me?memberId=7&type=both&query=반이학생마라탕마라반&4
    @GetMapping("/me")
    public List<Review> myReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String query
    ) {
        return reviewQueryService.searchMyReviews(memberId, type, query);
    }
}
