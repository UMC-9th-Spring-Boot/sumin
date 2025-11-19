package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

import java.util.Objects;

public class MissionConverter {

    public static MissionResDTO.RestaurantMissionDTO toRestaurantMissionDTO(Mission mission) {
        return MissionResDTO.RestaurantMissionDTO.builder()
                .missionId(mission.getId())
                .missionName(mission.getMissionName())
                .deadline(mission.getDeadline())
                .missionPoint(mission.getMissionPoint())
                .restaurantId(mission.getRestaurant().getId())
                .restaurantName(mission.getRestaurant().getRestaurantName())
                .build();
    }

    public static MissionResDTO.RestaurantMissionListDTO toRestaurantMissionPageDTO(Page<Mission> page) {
        return MissionResDTO.RestaurantMissionListDTO.builder()
                .missions(page.getContent().stream()
                        .filter(Objects::nonNull)
                        .map(MissionConverter::toRestaurantMissionDTO)
                        .toList())
                .listSize(page.getSize())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}
