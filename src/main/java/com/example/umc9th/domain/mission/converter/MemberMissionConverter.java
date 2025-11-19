package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Objects;

public class MemberMissionConverter {

    // 리뷰 생성 : DTO -> Entity
    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .isCompleted(false)
                .build();
    }

    // 리뷰 생성 응답용 : Entity -> DTO
    public static MemberMissionResDTO.ChallengeDTO toChallengeDTO(MemberMission memberMission) {
        return MemberMissionResDTO.ChallengeDTO.builder()
                .memberMissionId(memberMission.getId())
                .memberId(memberMission.getMember().getId())
                .missionId(memberMission.getMission().getId())
                .isCompleted(memberMission.isCompleted())
                .createdAt(memberMission.getCreatedAt())
                .build();
    }


    // 진행중인 미션 1개 -> DTO
    public static MemberMissionResDTO.OngoingMissionDTO toOngoingMissionDTO(MemberMission memberMission) {
        Mission mission = memberMission.getMission();

        return MemberMissionResDTO.OngoingMissionDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(mission.getId())
                .missionName(mission.getMissionName())
                .deadline(mission.getDeadline())
                .missionPoint(mission.getMissionPoint())
                .restaurantId(mission.getRestaurant().getId())
                .restaurantName(mission.getRestaurant().getRestaurantName())
                .challengedAt(memberMission.getCreatedAt())
                .build();
    }

    // Page<MemberMission> -> OngoingMissionPageDTO
    public static MemberMissionResDTO.OngoingMissionListDTO toOngoingMissionListDTO(Page<MemberMission> page) {

        List<MemberMissionResDTO.OngoingMissionDTO> missionList =
                page.getContent().stream()
                        .filter(Objects::nonNull)
                        .map(MemberMissionConverter::toOngoingMissionDTO)
                        .toList();

        return MemberMissionResDTO.OngoingMissionListDTO.builder()
                .missions(missionList)
                .listSize(page.getSize())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}
