package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.member.entity.mapping.MemberTerm;
import com.example.umc9th.domain.member.enums.TermName;
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
@Table(name = "term")
public class Term extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "term_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private TermName name;

    @OneToMany(mappedBy = "term", cascade = CascadeType.REMOVE)
    private List<MemberTerm> memberTermList = new ArrayList<>();
}
