package com.virtuwear.rest.controller;

import com.virtuwear.rest.dto.SingleGarmentDto;
import com.virtuwear.rest.dto.UserDto;
import com.virtuwear.rest.service.SingleGarmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/single_garments")
public class SGController {
    @Autowired
    private SingleGarmentService singleGarmentService;

    @PostMapping
    public ResponseEntity<SingleGarmentDto> createUser(@RequestBody SingleGarmentDto singleGarmentDto) {
        SingleGarmentDto savedSGDto = singleGarmentService.createSG(singleGarmentDto);
        return new ResponseEntity<>(savedSGDto, HttpStatus.CREATED);
    }
}
