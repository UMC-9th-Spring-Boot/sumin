package com.example.umc9th.domain.mission.dto.req;

import jakarta.validation.constraints.NotNull;

public class MemberMissionReqDTO {

    // 미션 도전 요청 DTO
    public record ChallengeDTO(
            @NotNull(message = "missionId는 필수입니다.")
            Long missionId
    ) {}
}
