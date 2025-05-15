package com.virtuwear.rest.service;

import com.virtuwear.rest.dto.SingleGarmentDto;
import com.virtuwear.rest.dto.TryonDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TryonService {

    List<TryonDto> getAllByUser(String userUid);

    List<TryonDto> searchByOutfitName(String outfitName);

    TryonDto getById(Long id);

    TryonDto createTryon(TryonDto dto);

    void deleteTryon(Long id);

    TryonDto updateModelImage (Long id, TryonDto dto);

    TryonDto updateGarmentImage (Long id, TryonDto dto);

    TryonDto updateResultImage (Long id, TryonDto dto);

    TryonDto updateOutfitName (Long id, TryonDto dto);

    TryonDto updateNote (Long id, TryonDto dto);

    TryonDto updateBookmark(Long id, TryonDto dto);

    List<TryonDto> getAllBookmarked(String userUid);

    List<TryonDto> findByCreatedAt(Long createdAt);
}
