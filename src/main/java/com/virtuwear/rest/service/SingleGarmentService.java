package com.virtuwear.rest.service;

import com.virtuwear.rest.dto.ReferralDto;
import com.virtuwear.rest.dto.SingleGarmentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SingleGarmentService {
    List<SingleGarmentDto> getAllByUser(String userId);

    SingleGarmentDto getById(Long id);

    SingleGarmentDto createSingleGarment(SingleGarmentDto dto);

    SingleGarmentDto updateSingleGarment(Long id, SingleGarmentDto dto);

    void deleteSingleGarment(Long id);


}
