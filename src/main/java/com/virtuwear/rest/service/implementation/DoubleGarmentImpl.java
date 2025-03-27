package com.virtuwear.rest.service.implementation;

import com.virtuwear.rest.dto.DoubleGarmentDto;
import com.virtuwear.rest.entity.DoubleGarment;
import com.virtuwear.rest.entity.User;
import com.virtuwear.rest.mapper.DoubleGarmentMapper;
import com.virtuwear.rest.repository.DoubleGarmentRepository;
import com.virtuwear.rest.repository.UserRepository;
import com.virtuwear.rest.service.DoubleGarmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DoubleGarmentImpl implements DoubleGarmentService {
    @Autowired
    private final DoubleGarmentRepository doubleGarmentRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final DoubleGarmentMapper doubleGarmentMapper;


    @Override
    public List<DoubleGarmentDto> getAllDoubleByUser(String userId) {
        List<DoubleGarment> doubleGarments = doubleGarmentRepository.findByUserUid(userId);
        return doubleGarments.stream().map(doubleGarmentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public DoubleGarmentDto getDoubleById(Long id) {
        DoubleGarment doubleGarment = doubleGarmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Garment not found"));
        return doubleGarmentMapper.toDto(doubleGarment);
    }

    @Override
    public DoubleGarmentDto createDoubleGarment(DoubleGarmentDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found!"));

        DoubleGarment doubleGarment = doubleGarmentMapper.toEntity(dto, user);
        doubleGarment = doubleGarmentRepository.save(doubleGarment);
        return doubleGarmentMapper.toDto(doubleGarment);
    }

    @Override
    public DoubleGarmentDto updateDoubleGarment(Long id, DoubleGarmentDto dto) {
        DoubleGarment doubleGarment = doubleGarmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Garment not found"));

        doubleGarment.setResultImg(dto.getResultImg());
        doubleGarment.setModelImg(dto.getModelImg());
        doubleGarment.setTopImg(dto.getTopImg());
        doubleGarment.setBottomImg(dto.getBottomImg());
        doubleGarment.setOutfitName(dto.getOutfitName());
        doubleGarment.setNotes(dto.getNotes());
        doubleGarment.setBookmark(dto.isBookmark());


        doubleGarmentRepository.save(doubleGarment);
        return doubleGarmentMapper.toDto(doubleGarment);
    }

    @Override
    public void deleteDoubleGarment(Long id) {
        DoubleGarment doubleGarment = doubleGarmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Garment not found"));
        doubleGarmentRepository.delete(doubleGarment);

    }
}
