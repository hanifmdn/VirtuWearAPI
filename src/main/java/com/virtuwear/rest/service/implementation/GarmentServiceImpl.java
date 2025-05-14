package com.virtuwear.rest.service.implementation;

import com.virtuwear.rest.dto.GarmentDto;
import com.virtuwear.rest.entity.Garment;
import com.virtuwear.rest.entity.User;
import com.virtuwear.rest.mapper.GarmentMapper;
import com.virtuwear.rest.repository.GarmentRepository;
import com.virtuwear.rest.repository.UserRepository;
import com.virtuwear.rest.service.GarmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GarmentServiceImpl implements GarmentService{
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final GarmentRepository garmentRepository;
    @Autowired
    private final GarmentMapper garmentMapper;
    @Override
    public GarmentDto createGarment(GarmentDto dto) {
        User user = userRepository.findById(dto.getUserUid())
                .orElseThrow(() -> new RuntimeException("User not found!"));

        String image = dto.getGarmentImage(); // Ambil image dari entity Garment

        Garment garments = garmentMapper.toEntity(image, user);
        return garmentMapper.toDto(garmentRepository.save(garments));
    }
}
