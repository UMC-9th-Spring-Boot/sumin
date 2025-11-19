package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;

import java.util.List;

public interface ReviewQueryService {
    List<Review> searchMyReviews(Long memberId, String type, String query);

    ReviewResDTO.ReviewPreViewListDTO findReview(String restaurantName, Integer page);

    // 내가 작성한 리뷰 목록
    ReviewResDTO.MyReviewListDTO findMyReviews(Long memberId, Integer page);
}
