package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.req.MemberMissionReqDTO;
import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.service.command.MemberMissionCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberMissionController {
    private final MemberMissionCommandService memberMissionCommandService;

    @Operation(
            summary = "미션 도전하기",
            description = "현재 회원(me)에 대해 특정 미션 도전 상태를 추가합니다. (과제에서는 하드코딩 회원 사용)"
    )
    @PostMapping("/members/me/missions")
    public ApiResponse<MemberMissionResDTO.ChallengeDTO> challengeMission(
            @Valid @RequestBody MemberMissionReqDTO.ChallengeDTO dto
    ) {
        MemberMissionResDTO.ChallengeDTO response = memberMissionCommandService.challengeMission(dto);
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_CHALLENGED, response);
    }
}
