package com.virtuwear.rest.mapper;

import com.virtuwear.rest.dto.SingleGarmentDto;
import com.virtuwear.rest.dto.TryonDto;
import com.virtuwear.rest.entity.*;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

@Component
public class TryonMapper {
    public TryonDto toDto(Tryon tryon) {
        return new TryonDto(
                tryon.getId(),
                tryon.getResultImage(),
                tryon.getOutfitName(),
                tryon.getNote(),
                tryon.isBookmark(),
                tryon.getGarment().getGarmentImage(),
                tryon.getModel().getModelImage(),
                tryon.getUser().getUid()
        );
    }

    public Tryon toEntity(TryonDto dto, User user, Garment garment, Model model) {
        Tryon tryon = new Tryon();
        tryon.setId(dto.getId());
        tryon.setResultImage(dto.getResultImage());
        tryon.setOutfitName(dto.getOutfitName());
        tryon.setNote(dto.getNote());
        tryon.setBookmark(dto.isBookmark());
        tryon.setGarment(garment);
        tryon.setModel(model);
        tryon.setUser(user);
        return tryon;
    }
}
