package com.virtuwear.rest.service;

import com.virtuwear.rest.dto.GarmentDto;
import com.virtuwear.rest.dto.ModelDto;
import com.virtuwear.rest.entity.Model;

public interface ModelService {
    ModelDto createModel(ModelDto dto);
}