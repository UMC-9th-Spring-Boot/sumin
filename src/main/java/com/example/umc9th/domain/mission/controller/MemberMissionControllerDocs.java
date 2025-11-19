package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.req.MemberMissionReqDTO;
import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface MemberMissionControllerDocs {

    @Operation(
            summary = "미션 도전하기",
            description = "현재 회원(me)에 대해 특정 미션 도전 상태를 추가합니다. (과제에서는 하드코딩 회원 사용)"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<MemberMissionResDTO.ChallengeDTO> challengeMission(
            @Valid @RequestBody MemberMissionReqDTO.ChallengeDTO dto
    );

    @Operation(
            summary = "내가 진행 중인 미션 목록 조회",
            description = "현재 회원(me)이 도전 중이며 아직 완료하지 않은 미션들을 page 단위(1페이지당 10개)로 조회합니다. page는 1 이상의 정수입니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<MemberMissionResDTO.OngoingMissionListDTO> getMyOngoingMissions(
            @RequestParam Long memberId,
            @RequestParam @Valid @ValidPage Integer page
    );



}
