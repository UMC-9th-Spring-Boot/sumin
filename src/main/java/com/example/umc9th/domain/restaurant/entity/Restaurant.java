package com.example.umc9th.domain.restaurant.entity;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "restaurant")
public class Restaurant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "restaurant_name", length = 50, nullable = false)
    private String restaurantName;

    @Column(name = "restaurant_category", length = 50, nullable = false)
    private String restaurantCategory;

    @Column(name = "rating", nullable = false)
    @Builder.Default
    private Double rating = 0.0;

    @Column(name = "owner_number")
    private Long ownerNumber; // Integer가 나을까??

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToMany(mappedBy="restaurant",  cascade = CascadeType.REMOVE)
    private List<Mission> missions = new ArrayList<>();

}

