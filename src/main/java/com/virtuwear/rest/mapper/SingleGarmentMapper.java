package com.virtuwear.rest.mapper;

import com.virtuwear.rest.dto.SingleGarmentDto;
import com.virtuwear.rest.entity.SingleGarment;
import com.virtuwear.rest.entity.User;
import org.springframework.stereotype.Component;

@Component
public class SingleGarmentMapper {
    public SingleGarmentDto toDto(SingleGarment singleGarment) {
        return new SingleGarmentDto(
                singleGarment.getIdSingle(),
                singleGarment.getResultImg(),
                singleGarment.getModelImg(),
                singleGarment.getGarmentImg(),
                singleGarment.getOutfitName(),
                singleGarment.getNotes(),
                singleGarment.isBookmark(),
                singleGarment.getUser().getUid()
        );
    }


    public SingleGarment toEntity(SingleGarmentDto dto, User user) {
        SingleGarment singleGarment = new SingleGarment();
        singleGarment.setIdSingle(dto.getIdSingle());
        singleGarment.setResultImg(dto.getResultImg());
        singleGarment.setModelImg(dto.getModelImg());
        singleGarment.setGarmentImg(dto.getGarmentImg());
        singleGarment.setOutfitName(dto.getOutfitName());
        singleGarment.setNotes(dto.getNotes());
        singleGarment.setBookmark(dto.isBookmark());
        singleGarment.setUser(user);
        return singleGarment;
    }
}

