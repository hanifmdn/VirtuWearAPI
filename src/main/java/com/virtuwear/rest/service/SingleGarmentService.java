package com.virtuwear.rest.service;

import com.virtuwear.rest.dto.ReferralDto;
import com.virtuwear.rest.dto.SingleGarmentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SingleGarmentService {
    SingleGarmentDto createSG(SingleGarmentDto singleGarmentDto);

    SingleGarmentDto getSGById(Long idSingle);

    List<SingleGarmentDto> getAllUser();

    SingleGarmentDto updateSG(Long idSingle, SingleGarmentDto updatedSG);

    void deleteSG (Long idSingle);

}
