package com.virtuwear.rest.service;

import com.virtuwear.rest.dto.DoubleGarmentDto;

import java.util.List;

public interface DoubleGarmentService {
    List<DoubleGarmentDto> getAllDoubleByUser(String userId);

    DoubleGarmentDto getDoubleById(Long id);

    DoubleGarmentDto createDoubleGarment(DoubleGarmentDto dto);

    DoubleGarmentDto updateDoubleGarment(Long id, DoubleGarmentDto dto);

    void deleteDoubleGarment(Long id);
}
