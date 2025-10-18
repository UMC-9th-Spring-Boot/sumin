package com.example.umc9th.domain.user.entity;

import com.example.umc9th.domain.user.entity.mapping.UserTerm;
import com.example.umc9th.domain.user.enums.TermName;
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
    private List<UserTerm> userTermList = new ArrayList<>();
}
