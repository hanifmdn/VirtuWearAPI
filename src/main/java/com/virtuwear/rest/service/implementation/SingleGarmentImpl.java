package com.virtuwear.rest.service.implementation;

import com.virtuwear.rest.dto.SingleGarmentDto;
import com.virtuwear.rest.dto.UserDto;
import com.virtuwear.rest.entity.SingleGarment;
import com.virtuwear.rest.entity.User;
import com.virtuwear.rest.mapper.SingleGarmentMapper;
import com.virtuwear.rest.repository.SingleGarmentRepository;
import com.virtuwear.rest.repository.UserRepository;
import com.virtuwear.rest.service.SingleGarmentService;
import lombok.AllArgsConstructor;
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

    @Override
    public List<SingleGarmentDto> getAllByUser(String userId) {
        List<SingleGarment> garments = singleGarmentRepository.findByUserUid(userId);
        return garments.stream().map(singleGarmentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<SingleGarmentDto> searchByOutfitName(String outfitName) {
        List<SingleGarment> garments = singleGarmentRepository.searchByOutfitName(outfitName);

        if (garments.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No garments found with outfitName: " + outfitName);
        }

        return garments.stream()
                .map(singleGarmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SingleGarmentDto getById(Long id) {
        SingleGarment garment = singleGarmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Garment not found"));
        return singleGarmentMapper.toDto(garment);
    }

    @Override
    public SingleGarmentDto createSingleGarment(SingleGarmentDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found!"));

        SingleGarment singleGarment = singleGarmentMapper.toEntity(dto, user);
        singleGarment = singleGarmentRepository.save(singleGarment);
        return singleGarmentMapper.toDto(singleGarment);
    }

    @Override
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

    @Override
    public void deleteSingleGarment(Long id) {
        SingleGarment garment = singleGarmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Garment not found"));
        singleGarmentRepository.delete(garment);
    }

    @Override
    public SingleGarmentDto updateModelImg (Long id, SingleGarmentDto dto) {
        SingleGarment garment = singleGarmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Garment not found"));

        garment.setModelImg(dto.getModelImg());

        singleGarmentRepository.save(garment);
        return singleGarmentMapper.toDto(garment);
    }

    @Override
    public SingleGarmentDto updateGarmentImg (Long id, SingleGarmentDto dto) {
        SingleGarment garment = singleGarmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Garment not found"));

        garment.setGarmentImg(dto.getGarmentImg());

        singleGarmentRepository.save(garment);
        return singleGarmentMapper.toDto(garment);
    }

    @Override
    public SingleGarmentDto updateResultImg (Long id, SingleGarmentDto dto) {
        SingleGarment garment = singleGarmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Garment not found"));

        garment.setResultImg(dto.getResultImg());

        singleGarmentRepository.save(garment);
        return singleGarmentMapper.toDto(garment);
    }

    @Override
    public SingleGarmentDto updateOutfitName (Long id, SingleGarmentDto dto) {
        SingleGarment garment = singleGarmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Garment not found"));

        garment.setOutfitName(dto.getOutfitName());

        singleGarmentRepository.save(garment);
        return singleGarmentMapper.toDto(garment);
    }

    @Override
    public SingleGarmentDto updateNotes (Long id, SingleGarmentDto dto) {
        SingleGarment garment = singleGarmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Garment not found"));

        garment.setNotes(dto.getNotes());

        singleGarmentRepository.save(garment);
        return singleGarmentMapper.toDto(garment);
    }

    @Override
    public SingleGarmentDto updateBookmark(Long id, boolean isBookmark) {
        SingleGarment garment = singleGarmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Garment not found"));

        System.out.println("Before update, bookmark value: " + garment.isBookmark());
        garment.setBookmark(isBookmark);
        SingleGarment saved = singleGarmentRepository.save(garment);
        System.out.println("After update, bookmark value: " + saved.isBookmark());

        return singleGarmentMapper.toDto(saved);
    }

    @Override
    public List<SingleGarmentDto> getAllBookmarked(String userId) {
        List<SingleGarment> garments = singleGarmentRepository.findByUserUidAndIsBookmarkTrue(userId);
        return garments.stream().map(singleGarmentMapper::toDto).collect(Collectors.toList());
    }
}