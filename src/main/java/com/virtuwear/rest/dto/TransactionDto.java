package com.virtuwear.rest.dto;

import com.virtuwear.rest.utility.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TransactionDto {
    private Long transactionId;
    private Long coinId;
    private TransactionType transactionType;
    private int amount;
}
