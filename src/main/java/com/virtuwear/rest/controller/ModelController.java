package com.virtuwear.rest.controller;

import com.virtuwear.rest.dto.GarmentDto;
import com.virtuwear.rest.dto.ModelDto;
import com.virtuwear.rest.service.GarmentService;
import com.virtuwear.rest.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/model")
@RequiredArgsConstructor

public class ModelController {
    private final ModelService modelService;

    @PostMapping
    public ResponseEntity<ModelDto> createModel(@RequestBody ModelDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(modelService.createModel(dto));
    }

}
