package com.virtuwear.rest.controller;

import com.virtuwear.rest.dto.GarmentDto;
import com.virtuwear.rest.dto.ModelDto;
import com.virtuwear.rest.dto.TryonDto;
import com.virtuwear.rest.service.GarmentService;
import com.virtuwear.rest.service.TryonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/garment")
@RequiredArgsConstructor
public class GarmentController {

    private final GarmentService garmentService;

    @PostMapping
    public ResponseEntity<GarmentDto> createGarment(@RequestBody GarmentDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(garmentService.createGarment(dto));
    }

    @GetMapping("/{userUid}")
    public ResponseEntity<List<GarmentDto>> getGarmentByUserUid(@PathVariable String userUid) {
        return ResponseEntity.ok(garmentService.findGarmentByUser(userUid));
    }


}
