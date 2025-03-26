package com.virtuwear.rest.mapper;

import com.virtuwear.rest.dto.ReferralDto;
import com.virtuwear.rest.dto.SingleGarmentDto;
import com.virtuwear.rest.entity.Referral;
import com.virtuwear.rest.entity.SingleGarment;
import com.virtuwear.rest.entity.User;

import java.sql.Timestamp;

public class SingleGarmentMapper {

    public static SingleGarmentDto mapToSGDto(SingleGarment singleGarment) {
        return new SingleGarmentDto(
                singleGarment.getIdSingle(),
                singleGarment.getResultImg(),
                singleGarment.getModelImg(),
                singleGarment.getGarmentImg(),
                singleGarment.getOutfitName(),
                singleGarment.getNotes(),
                singleGarment.isBookmark(),
                singleGarment.getUser(),
                singleGarment.getCreatedDate(),
                singleGarment.getUpdatedDate()
                );
    }



    public static SingleGarment mapToSG(SingleGarmentDto singleGarmentDto) {
        return new SingleGarment(
                singleGarmentDto.getIdSingle(),
                singleGarmentDto.getResultImg(),
                singleGarmentDto.getModelImg(),
                singleGarmentDto.getGarmentImg(),
                singleGarmentDto.getOutfitName(),
                singleGarmentDto.getNotes(),
                singleGarmentDto.isBookmark(),
                singleGarmentDto.getUser(),
                singleGarmentDto.getCreatedDate(),
                singleGarmentDto.getUpdatedDate()
        );
    }
}
