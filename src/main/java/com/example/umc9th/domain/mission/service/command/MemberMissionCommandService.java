package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.mission.dto.req.MemberMissionReqDTO;
import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;

public interface MemberMissionCommandService {

    MemberMissionResDTO.ChallengeDTO challengeMission(MemberMissionReqDTO.ChallengeDTO dto);
}
