package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface MissionControllerDocs {

    @Operation(
            summary = "특정 가게의 미션 목록 조회",
            description = "restaurantId에 해당하는 가게의 미션 목록을 page 단위(1페이지당 10개)로 조회합니다. page는 1 이상의 값이어야 합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<MissionResDTO.RestaurantMissionListDTO> getMissionsByRestaurant(
            @PathVariable Long restaurantId,
            @RequestParam @Valid @ValidPage Integer page
    );

}
