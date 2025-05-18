package com.virtuwear.rest.mapper;

import com.virtuwear.rest.dto.CoinDto;
import com.virtuwear.rest.dto.TransactionDto;
import com.virtuwear.rest.entity.Coin;
import com.virtuwear.rest.entity.Transaction;
import com.virtuwear.rest.entity.User;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public TransactionDto toDto(Transaction transaction) {
        return new TransactionDto(
                transaction.getTransactionId(),
                transaction.getCoin().getCoinId(),
                transaction.getTransactionType(),
                transaction.getAmount()
        );
    }

    public Transaction toEntity(TransactionDto dto, Coin coin) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(dto.getTransactionId());
        transaction.setCoin(coin);
        transaction.setTransactionType(dto.getTransactionType());
        transaction.setAmount(dto.getAmount());
        return transaction;
    }
}
