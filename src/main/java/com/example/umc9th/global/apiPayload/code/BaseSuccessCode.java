package com.example.umc9th.global.apiPayload.code;

import org.springframework.http.HttpStatus;

public interface BaseSuccessCode {
    HttpStatus getStatus(); // HttpStatus를 가지고 있는 enum
    String getCode();
    String getMessage();
}
