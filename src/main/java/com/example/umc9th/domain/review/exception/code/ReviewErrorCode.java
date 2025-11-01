package com.example.umc9th.domain.review.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {
    INVALID_TYPE(HttpStatus.BAD_REQUEST, "REVIEW400_1", "type 값이 올바르지 않습니다. [restaurant|rating|both|all]"),
    MISSING_QUERY(HttpStatus.BAD_REQUEST, "REVIEW400_2", "해당 type에 필요한 query 값이 없습니다."),
    INVALID_BOTH_QUERY(HttpStatus.BAD_REQUEST, "REVIEW400_3", "both 타입의 query는 '이름&숫자' 형식이어야 합니다."),
    INVALID_RATING(HttpStatus.BAD_REQUEST, "REVIEW400_4", "rating은 0.0 ~ 5.0 사이의 숫자여야 합니다."),;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
