package com.example.umc9th.domain.notification.entity;

import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "notice")
public class Notice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_confirmed", nullable = false)
    private boolean isConfirmed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_notice_id", nullable = false)
    private EventNotice eventNotice;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_notice_id", nullable = false)
    private ReviewNotice reviewNotice;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Inquiry_notice_id", nullable = false)
    private InquiryNotice inquiryNotice;
}
