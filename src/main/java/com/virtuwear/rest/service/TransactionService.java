package com.virtuwear.rest.service;

import com.virtuwear.rest.dto.TransactionDto;
import com.virtuwear.rest.entity.User;

public interface TransactionService {
    void validateGenerateTryon(String userUid);

    TransactionDto reduceCoin(String userUid);
}
