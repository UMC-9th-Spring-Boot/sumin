package com.example.umc9th.domain.user.entity;

import com.example.umc9th.domain.user.entity.mapping.UserFood;
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
@Table(name = "food_category")
public class Food extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name", length = 10, nullable = false)
    private String categoryName; // 음식 카테고리

    @OneToMany(mappedBy = "food", cascade = CascadeType.REMOVE)
    private List<UserFood> userFoodList = new ArrayList<>();
}
