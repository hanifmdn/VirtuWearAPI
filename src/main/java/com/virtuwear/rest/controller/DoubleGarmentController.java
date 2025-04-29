package com.virtuwear.rest.controller;

import com.virtuwear.rest.dto.DoubleGarmentDto;
import com.virtuwear.rest.dto.SingleGarmentDto;
import com.virtuwear.rest.entity.DoubleGarment;
import com.virtuwear.rest.service.implementation.DoubleGarmentImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/double-garments")
@RequiredArgsConstructor
public class DoubleGarmentController {

    private final DoubleGarmentImpl doubleGarmentService;

    @PostMapping
    public ResponseEntity<DoubleGarmentDto> createDoubleGarment(@RequestBody DoubleGarmentDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doubleGarmentService.createDoubleGarment(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoubleGarmentDto> updateDoubleGarment(@PathVariable Long id, @RequestBody DoubleGarmentDto dto) {
        return ResponseEntity.ok(doubleGarmentService.updateDoubleGarment(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DoubleGarmentDto> deleteDoubleGarment(@PathVariable Long id) {

        doubleGarmentService.deleteDoubleGarment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<DoubleGarmentDto>> getAllGarmentsByUser(@PathVariable String userId) {
        return ResponseEntity.ok(doubleGarmentService.getAllDoubleByUser(userId));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<DoubleGarmentDto> getSingleGarmentById(@PathVariable Long id) {
        return ResponseEntity.ok(doubleGarmentService.getDoubleById(id));
    }

    @PutMapping("/update/bookmark-double/{id}")
    public ResponseEntity<DoubleGarmentDto> updateBookmark(@PathVariable Long id, @RequestBody DoubleGarmentDto dto) {
        return ResponseEntity.ok(doubleGarmentService.updateBookmark(id, dto));
    }
}
