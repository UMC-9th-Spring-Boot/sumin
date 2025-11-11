package com.example.umc9th.domain.review.service.command;

import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;

public interface ReviewCommandService {
    // 가게에 리뷰 추가하기
    ReviewResDTO.CreateReview createReview(Long restaurantId, ReviewReqDTO.CreateReview dto);
}
