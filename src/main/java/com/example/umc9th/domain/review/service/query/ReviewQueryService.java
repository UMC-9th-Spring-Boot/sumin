package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.entity.Review;

import java.util.List;

public interface ReviewQueryService {
    List<Review> searchMyReviews(Long memberId, String type, String query);
}
