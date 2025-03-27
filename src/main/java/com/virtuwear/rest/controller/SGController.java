package com.virtuwear.rest.controller;

import com.virtuwear.rest.dto.SingleGarmentDto;
import com.virtuwear.rest.dto.UserDto;
import com.virtuwear.rest.service.SingleGarmentService;
import com.virtuwear.rest.service.implementation.SingleGarmentImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/single-garments")
@RequiredArgsConstructor
public class SGController {

    private final SingleGarmentImpl singleGarmentService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<SingleGarmentDto>> getAllGarmentsByUser(@PathVariable String userId) {
        return ResponseEntity.ok(singleGarmentService.getAllByUser(userId));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<SingleGarmentDto> getSingleGarmentById(@PathVariable Long id) {
        return ResponseEntity.ok(singleGarmentService.getById(id));
    }

    @PostMapping
    public ResponseEntity<SingleGarmentDto> createGarment(@RequestBody SingleGarmentDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(singleGarmentService.createSingleGarment(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SingleGarmentDto> updateGarment(@PathVariable Long id, @RequestBody SingleGarmentDto dto) {
        return ResponseEntity.ok(singleGarmentService.updateSingleGarment(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGarment(@PathVariable Long id) {
        singleGarmentService.deleteSingleGarment(id);
        return ResponseEntity.noContent().build();
    }
}
