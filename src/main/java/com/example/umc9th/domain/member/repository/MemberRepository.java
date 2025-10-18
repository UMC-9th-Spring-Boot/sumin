package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    // 활성 상태인 회원 단건 조회
    Optional<Member> findByIdAndIsActiveTrue(Long id);
}
