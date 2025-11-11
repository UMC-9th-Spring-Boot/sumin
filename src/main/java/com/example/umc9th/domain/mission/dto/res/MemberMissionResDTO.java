package com.example.umc9th.domain.mission.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberMissionResDTO {

    @Builder
    public record ChallengeDTO(
            Long memberMissionId,
            Long memberId,
            Long missionId,
            Boolean isCompleted,
            LocalDateTime createdAt
    ) {}
}
