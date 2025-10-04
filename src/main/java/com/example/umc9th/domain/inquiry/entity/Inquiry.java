package com.example.umc9th.domain.inquiry.entity;

import com.example.umc9th.domain.inquiry.enums.InquiryType;
import com.example.umc9th.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "inquiry")
public class Inquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "inquiry_title", length = 150, nullable = false)
    private String inquiryTitle;

    @Column(name = "inquiry_content", nullable = false)
    private String inquiryContent;

    @Column(name = "inquiry_type", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private InquiryType inquiryType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "inquiry", cascade = CascadeType.REMOVE)
    private List<InquiryPhoto> photos = new ArrayList<>();

    @OneToMany(mappedBy = "inquiry", cascade = CascadeType.REMOVE)
    private List<InquiryReply> replies = new ArrayList<>();

}
