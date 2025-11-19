// package 예시: com.example.umc9th.global.validation

package com.example.umc9th.global.annotation;

import com.example.umc9th.global.validator.PageValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PageValidator .class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPage {

    String message() default "page는 1 이상의 값이어야 합니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
