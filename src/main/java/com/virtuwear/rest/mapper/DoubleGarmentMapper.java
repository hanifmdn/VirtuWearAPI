package com.virtuwear.rest.mapper;

import com.virtuwear.rest.dto.DoubleGarmentDto;
import com.virtuwear.rest.dto.SingleGarmentDto;
import com.virtuwear.rest.entity.DoubleGarment;
import com.virtuwear.rest.entity.SingleGarment;
import com.virtuwear.rest.entity.User;
import org.springframework.stereotype.Component;

@Component

public class DoubleGarmentMapper {
    public DoubleGarmentDto toDto(DoubleGarment doubleGarment) {
        return new DoubleGarmentDto(
                doubleGarment.getIdDouble(),
                doubleGarment.getResultImg(),
                doubleGarment.getModelImg(),
                doubleGarment.getTopImg(),
                doubleGarment.getBottomImg(),
                doubleGarment.getOutfitName(),
                doubleGarment.getNotes(),
                doubleGarment.isBookmark(),
                doubleGarment.getUser().getUid()
        );
    }


    public DoubleGarment toEntity(DoubleGarmentDto dto, User user) {
        DoubleGarment doubleGarment = new DoubleGarment();
        doubleGarment.setIdDouble(dto.getIdDouble());
        doubleGarment.setResultImg(dto.getResultImg());
        doubleGarment.setModelImg(dto.getModelImg());
        doubleGarment.setTopImg(dto.getTopImg());
        doubleGarment.setBottomImg(dto.getBottomImg());
        doubleGarment.setOutfitName(dto.getOutfitName());
        doubleGarment.setNotes(dto.getNotes());
        doubleGarment.setBookmark(dto.isBookmark());
        doubleGarment.setUser(user);
        return doubleGarment;
    }
}

