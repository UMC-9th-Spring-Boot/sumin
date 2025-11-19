package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.restaurant.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 지역ID + 오늘 이후 마감 + 해당 회원이 '완료'한 미션 제외
    @Query("""
        select m
        from Mission m
        join m.restaurant r
        left join m.memberMissionList mm
               with mm.member.id = :memberId
        where r.region.id = :regionId
          and m.deadline >= :today
          and (mm is null or mm.isCompleted = false)
        """)
    Page<Mission> findAvailableMissionsByRegionId(
            @Param("memberId") Long memberId,
            @Param("regionId") Long regionId,
            @Param("today") LocalDate today,
            Pageable pageable
    );

    Page<Mission> findAllByRestaurant(
            Restaurant restaurant,
            PageRequest pageRequest
    );
}
