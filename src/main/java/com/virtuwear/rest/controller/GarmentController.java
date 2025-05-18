package com.virtuwear.rest.controller;

import com.virtuwear.rest.dto.GarmentDto;
import com.virtuwear.rest.dto.TryonDto;
import com.virtuwear.rest.service.GarmentService;
import com.virtuwear.rest.service.TryonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/garment")
@RequiredArgsConstructor
public class GarmentController {

    private final GarmentService garmentService;

    @PostMapping
    public ResponseEntity<GarmentDto> createGarment(@RequestBody GarmentDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(garmentService.createGarment(dto));
    }

}
