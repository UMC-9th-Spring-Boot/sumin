package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    MISSION_CHALLENGED(
            HttpStatus.CREATED,
            "MISSION201_1",
            "미션 도전에 성공했습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
