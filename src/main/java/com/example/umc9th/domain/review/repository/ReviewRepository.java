package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.restaurant.entity.Restaurant;
import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {
    // 특정 식당의 리뷰 조회
    List<Review> findByRestaurant(Restaurant restaurant);

    // 특정 회원이 작성한 리뷰 조회
    Page<Review> findByMember(Member member, PageRequest pageRequest);

    Page<Review> findAllByRestaurant(Restaurant restaurantName, PageRequest pageRequest);
}
