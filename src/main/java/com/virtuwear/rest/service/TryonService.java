package com.virtuwear.rest.service;

import com.virtuwear.rest.dto.TryonDto;
import org.springframework.stereotype.Service;

@Service
public interface TryonService {
    TryonDto createTryon(TryonDto dto);
}
