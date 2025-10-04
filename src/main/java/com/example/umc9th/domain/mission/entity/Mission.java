package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.domain.mission.entity.mapping.UserMission;
import com.example.umc9th.domain.restaurant.entity.Restaurant;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "mission")
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mission_name", length = 50, nullable = false)
    private String missionName;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @Column(name = "mission_point", nullable = false)
    private Integer missionPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "mission")
    private List<UserMission> userMissionList = new ArrayList<>();
}