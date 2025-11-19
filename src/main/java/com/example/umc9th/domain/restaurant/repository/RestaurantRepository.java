package com.example.umc9th.domain.restaurant.repository;

import com.example.umc9th.domain.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    Optional<Restaurant> findByRestaurantName(String restaurantName);    // 특정 식당의 리뷰 조회
}
