package com.virtuwear.rest.service.implementation;

import com.virtuwear.rest.dto.ReferralDto;
import com.virtuwear.rest.dto.SingleGarmentDto;
import com.virtuwear.rest.entity.Referral;
import com.virtuwear.rest.entity.SingleGarment;
import com.virtuwear.rest.mapper.ReferralMapper;
import com.virtuwear.rest.mapper.SingleGarmentMapper;
import com.virtuwear.rest.repository.SingleGarmentRepository;
import com.virtuwear.rest.service.SingleGarmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class SingleGarmentImpl implements SingleGarmentService {

    @Autowired
    private SingleGarmentRepository singleGarmentRepository;


    @Override
    public SingleGarmentDto createSG(SingleGarmentDto singleGarmentDto) {
        SingleGarment singleGarment = SingleGarmentMapper.mapToSG(singleGarmentDto);
        SingleGarment savedSingleGarment = singleGarmentRepository.save(singleGarment);
        return SingleGarmentMapper.mapToSGDto(savedSingleGarment);
    }

    @Override
    public SingleGarmentDto getSGById(Long idSingle) {
        return null;
    }

    @Override
    public List<SingleGarmentDto> getAllUser() {
        return List.of();
    }

    @Override
    public SingleGarmentDto updateSG(Long idSingle, SingleGarmentDto updatedSG) {
        return null;
    }

    @Override
    public void deleteSG(Long idSingle) {

    }
}
