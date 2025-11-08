package com.example.umc9th.domain.review.dto.req;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {
    // 가게에 리뷰 추가하기 요청 DTO
    public record CreateReview(
            @NotBlank(message = "리뷰 내용을 입력해주세요.")
            String reviewContent,

            @NotNull(message = "별점을 입력해주세요.")
            @DecimalMin(value = "0.0", message = "별점은 0.0 이상이어야 합니다.")
            @DecimalMax(value = "5.0", message = "별점은 5.0 이하이어야 합니다.")
            Double rating
    ) {}
}
