package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.req.MemberMissionReqDTO;
import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.service.command.MemberMissionCommandService;
import com.example.umc9th.domain.mission.service.query.MemberMissionQueryService;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Validated
public class MemberMissionController implements MemberMissionControllerDocs {
    private final MemberMissionCommandService memberMissionCommandService;
    private final MemberMissionQueryService memberMissionQueryService;

    @Override
    @PostMapping("/members/me/missions")
    public ApiResponse<MemberMissionResDTO.ChallengeDTO> challengeMission(
            @Valid @RequestBody MemberMissionReqDTO.ChallengeDTO dto
    ) {
        MemberMissionResDTO.ChallengeDTO response = memberMissionCommandService.challengeMission(dto);
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_CHALLENGED, response);
    }

    // 내가 진행중인 미션 목록 조회
    @Override
    @GetMapping("/members/me/missions/in-progress")
    public ApiResponse<MemberMissionResDTO.OngoingMissionListDTO> getMyOngoingMissions(
            @RequestParam Long memberId,
            @RequestParam @Valid @ValidPage Integer page
    ) {
        MemberMissionResDTO.OngoingMissionListDTO dto =
                memberMissionQueryService.getMyOngoingMissions(memberId, page);

        return ApiResponse.onSuccess(MissionSuccessCode.MY_ONGOING_MISSIONS_FOUND, dto);
    }
}


