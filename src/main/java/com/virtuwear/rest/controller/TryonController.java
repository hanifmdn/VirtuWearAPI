package com.virtuwear.rest.controller;

import com.virtuwear.rest.dto.SingleGarmentDto;
import com.virtuwear.rest.dto.TryonDto;
import com.virtuwear.rest.service.SingleGarmentService;
import com.virtuwear.rest.service.TryonService;
import com.virtuwear.rest.service.implementation.SingleGarmentImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tryon")
@RequiredArgsConstructor
public class TryonController {

    private final TryonService tryonService;

    @PostMapping
    public ResponseEntity<TryonDto> createTryon(@RequestBody TryonDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tryonService.createTryon(dto));
    }

}
