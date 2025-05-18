package com.virtuwear.rest.service.implementation;

import com.virtuwear.rest.dto.GarmentDto;
import com.virtuwear.rest.dto.ModelDto;
import com.virtuwear.rest.dto.TryonDto;
import com.virtuwear.rest.entity.Garment;
import com.virtuwear.rest.entity.Model;
import com.virtuwear.rest.entity.Tryon;
import com.virtuwear.rest.entity.User;
import com.virtuwear.rest.mapper.GarmentMapper;
import com.virtuwear.rest.mapper.ModelMapper;
import com.virtuwear.rest.repository.GarmentRepository;
import com.virtuwear.rest.repository.ModelRepository;
import com.virtuwear.rest.repository.UserRepository;
import com.virtuwear.rest.service.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModelServiceImpl implements ModelService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final ModelRepository modelRepository;
    @Autowired
    private final ModelMapper modelMapper;
    @Override
    public ModelDto createModel(ModelDto dto) {
        User user = userRepository.findById(dto.getUserUid())
                .orElseThrow(() -> new RuntimeException("User not found!"));

        String image = dto.getModelImage();

        Model models = modelMapper.toEntity(image, user);
        return modelMapper.toDto(modelRepository.save(models));
    }

    @Override
    public List<ModelDto> findModelByUser(String userUid) {
        List<Model> models = modelRepository.findByUserUid(userUid);
        return models.stream().map(modelMapper::toDto).collect(Collectors.toList());
    }
}
