package com.virtuwear.rest.controller;

import com.virtuwear.rest.dto.TryonDto;
import com.virtuwear.rest.service.TryonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tryon")
@RequiredArgsConstructor
public class TryonController {

    private final TryonService tryonService;

    @GetMapping("/{userUid}")
    public ResponseEntity<List<TryonDto>> getAllGarmentsByUser(@PathVariable String userUid) {
        return ResponseEntity.ok(tryonService.getAllByUser(userUid));
    }

    @GetMapping("/search")
    public ResponseEntity<List<TryonDto>> searchByOutfitName(@RequestParam String outfitName) {
        return ResponseEntity.ok(tryonService.searchByOutfitName(outfitName));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<TryonDto> getTryonById(@PathVariable Long id) {
        return ResponseEntity.ok(tryonService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TryonDto> createTryon(@RequestBody TryonDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tryonService.createTryon(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTryon(@PathVariable Long id) {
        tryonService.deleteTryon(id);
        return ResponseEntity.ok("Tryon with id:"+ id + " deleted succesfully");
    }

    @PutMapping("/update/model/{id}")
    public ResponseEntity<TryonDto> updateModelImage(@PathVariable Long id, @RequestBody TryonDto dto) {
        return ResponseEntity.ok(tryonService.updateModelImage(id, dto));
    }

    @PutMapping("/update/garment/{id}")
    public ResponseEntity<TryonDto> updateGarmentImage(@PathVariable Long id, @RequestBody TryonDto dto) {
        return ResponseEntity.ok(tryonService.updateGarmentImage(id, dto));
    }

    @PutMapping("/update/result/{id}")
    public ResponseEntity<TryonDto> updateResultImage(@PathVariable Long id, @RequestBody TryonDto dto) {
        return ResponseEntity.ok(tryonService.updateResultImage(id, dto));
    }

    @PutMapping("/update/outfit_name/{id}")
    public ResponseEntity<TryonDto> updateOutfitName(@PathVariable Long id, @RequestBody TryonDto dto) {
        return ResponseEntity.ok(tryonService.updateOutfitName(id, dto));
    }

    @PutMapping("/update/notes/{id}")
    public ResponseEntity<TryonDto> updateNote(@PathVariable Long id, @RequestBody TryonDto dto) {
        return ResponseEntity.ok(tryonService.updateNote(id, dto));
    }

    @PutMapping("/update/bookmark/{id}")
    public ResponseEntity<TryonDto> updateBookmark(@PathVariable Long id, @RequestBody TryonDto dto) {
        return ResponseEntity.ok(tryonService.updateBookmark(id, dto));
    }

    @GetMapping("/bookmarked/{userUid}")
    public ResponseEntity<List<TryonDto>> getAllBookmarked(@PathVariable String userUid) {
        List<TryonDto> bookmarked = tryonService.getAllBookmarked(userUid);
        return ResponseEntity.ok(bookmarked);
    }

    @GetMapping("/sort/date/{timeMillis}")
    public ResponseEntity<List<TryonDto>> findByCreatedAt(@PathVariable Long timeMillis) {
        List<TryonDto> tryons = tryonService.findByCreatedAt(timeMillis);
        return ResponseEntity.ok(tryons);
    }
}
