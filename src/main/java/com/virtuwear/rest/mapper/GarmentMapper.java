package com.virtuwear.rest.mapper;

import com.virtuwear.rest.dto.GarmentDto;
import com.virtuwear.rest.entity.Garment;
import com.virtuwear.rest.entity.User;
import org.springframework.stereotype.Component;

@Component
public class GarmentMapper {
    public GarmentDto toDto(Garment garment){
        return new GarmentDto(
          garment.getGarmentImage(),
          garment.getUser().getUid()
        );
    }

    public Garment toEntity(String garmentImage, User user){
        Garment garment = new Garment();
        garment.setGarmentImage(garmentImage);
        garment.setUser(user);
        return garment;
    }
}
