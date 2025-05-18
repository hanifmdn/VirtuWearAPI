package com.virtuwear.rest.service;

import com.virtuwear.rest.dto.GarmentDto;
import com.virtuwear.rest.dto.ModelDto;
import com.virtuwear.rest.dto.TryonDto;
import com.virtuwear.rest.entity.Model;

import java.util.List;

public interface ModelService {
    ModelDto createModel(ModelDto dto);

    List<ModelDto> findModelByUser(String userUid);
}