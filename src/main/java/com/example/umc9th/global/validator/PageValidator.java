package com.example.umc9th.global.validator;

import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PageValidator implements ConstraintValidator<ValidPage, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {

        // null이거나 1 미만이면 바로 GeneralException 던짐
        if (value == null || value < 1) {
            throw new GeneralException(GeneralErrorCode.VALID_FAIL);
        }

        // 여기까지 왔으면 유효
        return true;
    }
}
