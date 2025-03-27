package com.virtuwear.rest.service.implementation;

import com.virtuwear.rest.dto.ReferralDto;
import com.virtuwear.rest.dto.SingleGarmentDto;
import com.virtuwear.rest.entity.Referral;
import com.virtuwear.rest.entity.SingleGarment;
import com.virtuwear.rest.entity.User;
import com.virtuwear.rest.mapper.ReferralMapper;
import com.virtuwear.rest.mapper.SingleGarmentMapper;
import com.virtuwear.rest.repository.SingleGarmentRepository;
import com.virtuwear.rest.repository.UserRepository;
import com.virtuwear.rest.service.SingleGarmentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SingleGarmentImpl implements SingleGarmentService {
    @Autowired
    private final SingleGarmentRepository singleGarmentRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final SingleGarmentMapper singleGarmentMapper;

    public List<SingleGarmentDto> getAllByUser(String userId) {
        List<SingleGarment> garments = singleGarmentRepository.findByUserUid(userId);
        return garments.stream().map(singleGarmentMapper::toDto).collect(Collectors.toList());
    }

    public SingleGarmentDto getById(Long id) {
        SingleGarment garment = singleGarmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Garment not found"));
        return singleGarmentMapper.toDto(garment);
    }

    public SingleGarmentDto createSingleGarment(SingleGarmentDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found!"));

        SingleGarment singleGarment = singleGarmentMapper.toEntity(dto, user);
        singleGarment = singleGarmentRepository.save(singleGarment);
        return singleGarmentMapper.toDto(singleGarment);
    }

    public SingleGarmentDto updateSingleGarment(Long id, SingleGarmentDto dto) {
        SingleGarment garment = singleGarmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Garment not found"));

        garment.setResultImg(dto.getResultImg());
        garment.setModelImg(dto.getModelImg());
        garment.setGarmentImg(dto.getGarmentImg());
        garment.setOutfitName(dto.getOutfitName());
        garment.setNotes(dto.getNotes());
        garment.setBookmark(dto.isBookmark());

        singleGarmentRepository.save(garment);
        return singleGarmentMapper.toDto(garment);
    }

    public void deleteSingleGarment(Long id) {
        SingleGarment garment = singleGarmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Garment not found"));
        singleGarmentRepository.delete(garment);
    }

}
