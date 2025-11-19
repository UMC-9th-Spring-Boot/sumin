package com.example.umc9th.domain.mission.dto.res;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

    // 단일 미션 정보
    @Builder
    public record RestaurantMissionDTO(
            Long missionId,
            String missionName,
            LocalDate deadline,
            Integer missionPoint,
            Long restaurantId,
            String restaurantName
    ) {}

    // 특정 가게의 미션 목록 + 페이징 정보
    @Builder
    public record RestaurantMissionListDTO(
            List<RestaurantMissionDTO> missions,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}
}
