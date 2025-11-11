package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.mapping.MemberMission;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    // 내가 진행 중인 미션 조회
    Page<MemberMission> findAllByMember_IdAndIsCompletedFalse(Long memberId, Pageable pageable);

    // 내가 진행 완료한 미션 조회
    Page<MemberMission> findAllByMember_IdAndIsCompletedTrue(Long memberId, Pageable pageable);

    boolean existsByMember_IdAndMission_Id(Long memberId, Long missionId);
}
