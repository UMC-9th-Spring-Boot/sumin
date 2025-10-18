package com.example.umc9th.domain.user.entity;

import com.example.umc9th.domain.mission.entity.mapping.UserMission;
import com.example.umc9th.domain.user.entity.mapping.UserFood;
import com.example.umc9th.domain.user.entity.mapping.UserTerm;
import com.example.umc9th.domain.user.enums.Gender;
import com.example.umc9th.domain.user.enums.PhoneVerificationStatus;
import com.example.umc9th.domain.user.enums.SocialType;
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
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "name", length = 10, nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "phone_num", length = 15, nullable = false)
    private String phoneNum;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "address", length = 125, nullable = false)
    private String address;

    @Column(name = "point", nullable = false)
    @Builder.Default
    private Integer point = 0;

    @Column(name = "social_type")
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(name = "phone_verification_status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private PhoneVerificationStatus phoneVerificationStatus = PhoneVerificationStatus.UNVERIFIED;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserFood> userFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserTerm> userTermList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserMission> userMissionList = new ArrayList<>();
}
