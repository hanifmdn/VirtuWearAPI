package com.virtuwear.rest.controller;

import com.virtuwear.rest.dto.BookmarkUpdateDto;
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
import java.util.Map;

@RestController
@RequestMapping("/api/single-garments")
@RequiredArgsConstructor
public class SGController {

    private final SingleGarmentImpl singleGarmentService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<SingleGarmentDto>> getAllGarmentsByUser(@PathVariable String userId) {
        return ResponseEntity.ok(singleGarmentService.getAllByUser(userId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<SingleGarmentDto>> searchByOutfitName(@RequestParam String outfitName) {
        return ResponseEntity.ok(singleGarmentService.searchByOutfitName(outfitName));
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

    @PutMapping("/update/model/{id}")
    public ResponseEntity<SingleGarmentDto> updateModelImage(@PathVariable Long id, @RequestBody SingleGarmentDto dto) {
        return ResponseEntity.ok(singleGarmentService.updateModelImg(id, dto));
    }

    @PutMapping("/update/garment/{id}")
    public ResponseEntity<SingleGarmentDto> updateGarmentImage(@PathVariable Long id, @RequestBody SingleGarmentDto dto) {
        return ResponseEntity.ok(singleGarmentService.updateGarmentImg(id, dto));
    }

    @PutMapping("/update/result/{id}")
    public ResponseEntity<SingleGarmentDto> updateResultImage(@PathVariable Long id, @RequestBody SingleGarmentDto dto) {
        return ResponseEntity.ok(singleGarmentService.updateResultImg(id, dto));
    }

    @PutMapping("/update/outfit_name/{id}")
    public ResponseEntity<SingleGarmentDto> updateOutfitName(@PathVariable Long id, @RequestBody SingleGarmentDto dto) {
        return ResponseEntity.ok(singleGarmentService.updateOutfitName(id, dto));
    }

    @PutMapping("/update/notes/{id}")
    public ResponseEntity<SingleGarmentDto> updateNotes(@PathVariable Long id, @RequestBody SingleGarmentDto dto) {
        return ResponseEntity.ok(singleGarmentService.updateNotes(id, dto));
    }

    @PutMapping("/update/bookmark-single/{id}")
    public ResponseEntity<SingleGarmentDto> updateBookmark(@PathVariable Long id, @RequestBody SingleGarmentDto dto) {
        System.out.println("Received bookmark update for ID: " + id);
        System.out.println("DTO isBookmark value: " + dto.isBookmark());

        SingleGarmentDto result = singleGarmentService.updateBookmark(id, dto.isBookmark());

        System.out.println("Updated bookmark value in DB: " + result.isBookmark());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/bookmarked/{userId}")
    public ResponseEntity<List<SingleGarmentDto>> getAllBookmarked(@PathVariable String userId) {
        List<SingleGarmentDto> bookmarked = singleGarmentService.getAllBookmarked(userId);
        return ResponseEntity.ok(bookmarked);
    }









}
