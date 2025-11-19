package com.example.umc9th.domain.mission.dto.res;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MemberMissionResDTO {

    @Builder
    public record ChallengeDTO(
            Long memberMissionId,
            Long memberId,
            Long missionId,
            Boolean isCompleted,
            LocalDateTime createdAt
    ) {}


    // 진행중인(미완료) 미션 1개 정보
    @Builder
    public record OngoingMissionDTO(
            Long memberMissionId,
            Long missionId,
            String missionName,
            LocalDate deadline,
            Integer missionPoint,
            Long restaurantId,
            String restaurantName,
            LocalDateTime challengedAt
    ) {}

    // 내가 진행중인 미션 목록 + 페이징 정보
    @Builder
    public record OngoingMissionListDTO(
            List<OngoingMissionDTO> missions,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}
}
