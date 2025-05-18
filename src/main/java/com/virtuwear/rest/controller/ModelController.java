package com.virtuwear.rest.controller;

import com.virtuwear.rest.dto.GarmentDto;
import com.virtuwear.rest.dto.ModelDto;
import com.virtuwear.rest.dto.TryonDto;
import com.virtuwear.rest.entity.Model;
import com.virtuwear.rest.service.GarmentService;
import com.virtuwear.rest.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/model")
@RequiredArgsConstructor

public class ModelController {
    private final ModelService modelService;

    @PostMapping
    public ResponseEntity<ModelDto> createModel(@RequestBody ModelDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(modelService.createModel(dto));
    }

    @GetMapping("/{userUid}")
    public ResponseEntity<List<ModelDto>> getModelByUserUid(@PathVariable String userUid) {
        return ResponseEntity.ok(modelService.findModelByUser(userUid));
    }
}
