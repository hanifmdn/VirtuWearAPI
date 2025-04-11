package com.virtuwear.rest.service;

import com.virtuwear.rest.dto.ReferralDto;
import com.virtuwear.rest.dto.SingleGarmentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SingleGarmentService {
    List<SingleGarmentDto> getAllByUser(String userId);

    List<SingleGarmentDto> getByOutfitName(String outfitName);

    SingleGarmentDto getById(Long id);

    SingleGarmentDto createSingleGarment(SingleGarmentDto dto);

    SingleGarmentDto updateSingleGarment(Long id, SingleGarmentDto dto);

    void deleteSingleGarment(Long id);

    SingleGarmentDto updateModelImg (Long id, SingleGarmentDto dto);

    SingleGarmentDto updateGarmentImg (Long id, SingleGarmentDto dto);

    SingleGarmentDto updateResultImg (Long id, SingleGarmentDto dto);

    SingleGarmentDto updateOutfitName (Long id, SingleGarmentDto dto);

    SingleGarmentDto updateNotes (Long id, SingleGarmentDto dto);

    SingleGarmentDto updateBookmark (Long id, SingleGarmentDto dto);

}
