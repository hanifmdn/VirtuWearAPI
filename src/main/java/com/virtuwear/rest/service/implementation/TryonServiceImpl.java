package com.virtuwear.rest.service.implementation;

import com.virtuwear.rest.dto.SingleGarmentDto;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<TryonDto> getAllByUser(String userUid) {
        List<Tryon> tryons = tryonRepository.findByUserUid(userUid);
        return tryons.stream().map(tryonMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<TryonDto> searchByOutfitName(String outfitName) {
        List<Tryon> tryons = tryonRepository.searchByOutfitName(outfitName);

        if (tryons.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No garments found with outfitName: " + outfitName);
        }

        return tryons.stream()
                .map(tryonMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TryonDto getById(Long id) {
        Tryon tryon = tryonRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Garment not found"));
        return tryonMapper.toDto(tryon);
    }


    @Override
    public TryonDto createTryon(TryonDto dto) {
        if (dto.getUserUid() == null || dto.getUserUid().isEmpty()) {
            throw new IllegalArgumentException("User UID must not be null or empty");
        }

        // Log DTO data
        System.out.println("Received DTO Data:");
        System.out.println("User UID: " + dto.getUserUid());
        System.out.println("Garment Image: " + dto.getGarmentImage());
        System.out.println("Model Image: " + dto.getModelImage());
        System.out.println("Outfit Name: " + dto.getOutfitName());
        System.out.println("Result Image: " + dto.getResultImage());
        System.out.println("Note: " + dto.getNote());
        System.out.println("Is Bookmark: " + dto.isBookmark());


        if (dto.getUserUid() == null || dto.getUserUid().isEmpty()) {
            throw new IllegalArgumentException("User UID must not be null or empty");
        }

        User user = userRepository.findById(dto.getUserUid())
                .orElseThrow(() -> new RuntimeException("User with ID " + dto.getUserUid() + " not found!"));

        Garment garment = garmentRepository.findById(dto.getGarmentImage())
                .orElseThrow(() -> new RuntimeException("Garment with Id" + dto.getGarmentImage() + " Not Found!"));

        Model model = modelRepository.findById(dto.getModelImage())
                .orElseThrow(() -> new RuntimeException("Model with Id" + dto.getGarmentImage() + " Not Found!"));

        Tryon tryon = tryonMapper.toEntity(dto, user, garment, model);
        tryon = tryonRepository.save(tryon);
        return tryonMapper.toDto(tryon);
    }

    @Override
    public void deleteTryon(Long id) {
        Tryon tryon = tryonRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tryon not found"));
        tryonRepository.delete(tryon);
    }

    @Override
    public TryonDto updateModelImage (Long id, TryonDto dto) {
        Tryon model = tryonRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Garment not found"));

        model.getModel().setModelImage(dto.getModelImage());

        tryonRepository.save(model);
        return tryonMapper.toDto(model);
    }

    @Override
    public TryonDto updateGarmentImage (Long id, TryonDto dto) {
        Tryon garment = tryonRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Garment not found"));

        garment.getGarment().setGarmentImage(dto.getGarmentImage());

        tryonRepository.save(garment);
        return tryonMapper.toDto(garment);
    }



    @Override
    public TryonDto updateResultImage (Long id, TryonDto dto) {
        Tryon tryon = tryonRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tryon not found"));

        tryon.setResultImage(dto.getResultImage());

        tryonRepository.save(tryon);
        return tryonMapper.toDto(tryon);
    }

    @Override
    public TryonDto updateOutfitName (Long id, TryonDto dto) {
        Tryon tryon = tryonRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tryon not found"));

        tryon.setOutfitName(dto.getOutfitName());

        tryonRepository.save(tryon);
        return tryonMapper.toDto(tryon);
    }

    @Override
    public TryonDto updateNote (Long id, TryonDto dto) {
        Tryon tryon = tryonRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tryon not found"));

        tryon.setNote(dto.getNote());

        tryonRepository.save(tryon);
        return tryonMapper.toDto(tryon);
    }

    @Override
    public TryonDto updateBookmark(Long id, TryonDto dto) {
        Tryon tryon = tryonRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tryon not found"));

        tryon.setBookmark(dto.isBookmark());

        tryonRepository.save(tryon);
        return tryonMapper.toDto(tryon);
    }

    @Override
    public List<TryonDto> getAllBookmarked(String userUid) {
        List<Tryon> tryons = tryonRepository.findByUserUidAndIsBookmarkTrue(userUid);
        return tryons.stream().map(tryonMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<TryonDto> findByCreatedAt(Long createdAt){
        LocalDate localDate = Instant.ofEpochMilli(createdAt)
                .atZone(ZoneId.systemDefault()) // atau pakai ZoneId.of("Asia/Jakarta") jika perlu
                .toLocalDate();
        Timestamp startOfDay = Timestamp.valueOf(localDate.atStartOfDay());
        Timestamp endOfDay = Timestamp.valueOf(localDate.plusDays(1).atStartOfDay().minusNanos(1));
        List<Tryon> garments = tryonRepository.findByCreatedDateBetween(startOfDay,endOfDay);
        return garments.stream().map(tryonMapper::toDto).collect(Collectors.toList());
    }
}
