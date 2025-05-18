package com.virtuwear.rest.service;

import com.virtuwear.rest.dto.GarmentDto;
import com.virtuwear.rest.dto.ModelDto;

import java.util.List;

public interface GarmentService {
    GarmentDto createGarment(GarmentDto dto);

    List<GarmentDto> findGarmentByUser(String userUid);

}
