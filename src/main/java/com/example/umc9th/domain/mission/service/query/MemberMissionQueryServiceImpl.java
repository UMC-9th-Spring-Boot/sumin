package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.converter.MemberMissionConverter;
import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    private static final Long HARD_CODED_MEMBER_ID = 1L;

    @Override
    public MemberMissionResDTO.OngoingMissionListDTO getMyOngoingMissions(Long memberId, Integer page) {

        // 컨트롤러에서 받은 memberId로 조회
        Member member = memberRepository.findByIdAndIsActiveTrue(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        int pageIndex = page - 1;
        int size = 10; // 한 페이지당 10개

        PageRequest pageRequest = PageRequest.of(pageIndex, size);

        Page<MemberMission> result =
                memberMissionRepository.findAllByMember_IdAndIsCompletedFalse(member.getId(), pageRequest);

        return MemberMissionConverter.toOngoingMissionListDTO(result);
    }
}
