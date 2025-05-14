package com.virtuwear.rest.service.implementation;

import com.virtuwear.rest.dto.TryonDto;
import com.virtuwear.rest.entity.*;
import com.virtuwear.rest.mapper.SingleGarmentMapper;
import com.virtuwear.rest.mapper.TryonMapper;
import com.virtuwear.rest.repository.GarmentRepository;
import com.virtuwear.rest.repository.ModelRepository;
import com.virtuwear.rest.repository.TryonRepository;
import com.virtuwear.rest.repository.UserRepository;
import com.virtuwear.rest.service.TryonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TryonServiceImpl implements TryonService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final GarmentRepository garmentRepository;
    @Autowired
    private final ModelRepository modelRepository;
    @Autowired
    private final TryonRepository tryonRepository;

    @Autowired
    private final TryonMapper tryonMapper;

    @Override
    public TryonDto createTryon(TryonDto dto) {
        User user = userRepository.findById(dto.getUserUid())
                .orElseThrow(() -> new RuntimeException("User not found!"));

        Garment garment = garmentRepository.findById(dto.getGarmentImage())
                .orElseThrow(() -> new RuntimeException("Garment Not Found!"));

        Model model = modelRepository.findById(dto.getModelImage())
                .orElseThrow(() -> new RuntimeException("Garment Not Found!"));

        Tryon tryon = tryonMapper.toEntity(dto, user, garment, model);
        tryon = tryonRepository.save(tryon);
        return tryonMapper.toDto(tryon);
    }
}
