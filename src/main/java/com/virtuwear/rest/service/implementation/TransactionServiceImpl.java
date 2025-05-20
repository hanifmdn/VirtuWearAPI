package com.virtuwear.rest.service.implementation;

import com.virtuwear.rest.dto.TransactionDto;
import com.virtuwear.rest.entity.Coin;
import com.virtuwear.rest.entity.Transaction;
import com.virtuwear.rest.exception.InsufficientBalanceException;
import com.virtuwear.rest.mapper.TransactionMapper;
import com.virtuwear.rest.repository.CoinRepository;
import com.virtuwear.rest.repository.TransactionRepository;
import com.virtuwear.rest.service.TransactionService;
import com.virtuwear.rest.utility.TransactionType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private CoinRepository coinRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public void validateGenerateTryon(String userUid) {
        int priceGenerate = 15;

        Coin coin = coinRepository.findByUserUid(userUid)
                .orElseThrow(() -> new RuntimeException("Coin with UserUID " + userUid + " not found!"));

        if (coin.getCoinBalance() < priceGenerate) {
            throw new InsufficientBalanceException("Insufficient balance for try-on generation. Required: "
                    + priceGenerate + ", Current balance: " + coin.getCoinBalance());
        }
    }

    @Override
    public TransactionDto reduceCoin(String userUid) {
        int priceGenerate = 15;

        Coin coin = coinRepository.findByUserUid(userUid)
                .orElseThrow(() -> new RuntimeException("Coin with UserUID " + userUid + " not found!"));


        coin.setCoinBalance(coin.getCoinBalance() - priceGenerate);
        coinRepository.save(coin);

        Transaction transaction = new Transaction();
        transaction.setAmount(priceGenerate);
        transaction.setTransactionType(TransactionType.SPEND);
        transaction.setCoin(coin);
        transactionRepository.save(transaction);

        return transactionMapper.toDto(transaction);
    }
}
