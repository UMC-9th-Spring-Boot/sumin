package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.service.query.MissionQueryService;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Validated
public class MissionController implements MissionControllerDocs {

    private final MissionQueryService missionQueryService;

    @Override
    @GetMapping("/restaurants/{restaurantId}/missions")
    public ApiResponse<MissionResDTO.RestaurantMissionListDTO> getMissionsByRestaurant(
            @PathVariable Long restaurantId,
            @RequestParam @Valid @ValidPage Integer page
    ) {
        MissionResDTO.RestaurantMissionListDTO dto =
                missionQueryService.findMissionsByRestaurant(restaurantId, page);

        return ApiResponse.onSuccess(MissionSuccessCode.RESTAURANT_MISSIONS_FOUND, dto);
    }
}
