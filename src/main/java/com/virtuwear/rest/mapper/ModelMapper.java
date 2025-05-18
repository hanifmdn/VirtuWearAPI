package com.virtuwear.rest.mapper;

import com.virtuwear.rest.dto.GarmentDto;
import com.virtuwear.rest.dto.ModelDto;
import com.virtuwear.rest.entity.Garment;
import com.virtuwear.rest.entity.Model;
import com.virtuwear.rest.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {
    public ModelDto toDto(Model model){
        return new ModelDto(
                model.getModelImage(),
                model.getUser().getUid()
        );
    }

    public Model toEntity(String modelImage, User user){
        Model model = new Model();
        model.setModelImage(modelImage);
        model.setUser(user);
        return model;
    }
}