package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.mapping.MemberFood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberFoodRepository extends JpaRepository<MemberFood,Long> {

}
