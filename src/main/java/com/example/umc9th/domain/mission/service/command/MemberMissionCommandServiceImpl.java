package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.converter.MemberMissionConverter;
import com.example.umc9th.domain.mission.dto.req.MemberMissionReqDTO;
import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.exception.MissionException;
import com.example.umc9th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    // 미션용 하드코디 회원
    private static final Long HARDCODED_MEMBER_ID = 1L;

    @Override
    @Transactional
    public MemberMissionResDTO.ChallengeDTO challengeMission(MemberMissionReqDTO.ChallengeDTO dto) {
        // 1. 미션 존재 여부 체크
        Mission mission = missionRepository.findById(dto.missionId())
                .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));

        // 2. 회원 존재 여부 체크
        Member member = memberRepository.findById(HARDCODED_MEMBER_ID)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 3. 이미 도전 중인지 검사 (중복 방지)
        if (memberMissionRepository.existsByMember_IdAndMission_Id(member.getId(), mission.getId())) {
            throw new MissionException(MissionErrorCode.ALREADY_CHALLENGED);
        }

        // 4. MemberMission 생성 및 저장
        MemberMission memberMission = MemberMissionConverter.toMemberMission(member, mission);
        MemberMission saved = memberMissionRepository.save(memberMission);

        return MemberMissionConverter.toChallengeDTO(saved);
    }
}
