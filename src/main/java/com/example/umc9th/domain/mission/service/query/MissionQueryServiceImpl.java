package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.restaurant.entity.Restaurant;
import com.example.umc9th.domain.restaurant.exception.RestaurantException;
import com.example.umc9th.domain.restaurant.exception.code.RestaurantErrorCode;
import com.example.umc9th.domain.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {
    private final MissionRepository missionRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public MissionResDTO.RestaurantMissionListDTO findMissionsByRestaurant(Long restaurantId, Integer page) {

        // 1) 가게 존재 여부 확인
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantException(RestaurantErrorCode.NOT_FOUND));

        int pageIndex = page - 1;
        int size = 10; //한 페이지 10개

        PageRequest pageRequest = PageRequest.of(pageIndex, size);

        // 3) 해당 가게의 미션 목록 조회
        Page<Mission> result = missionRepository.findAllByRestaurant(restaurant, pageRequest);

        // 4) DTO로 변환
        return MissionConverter.toRestaurantMissionPageDTO(result);
    }
}
