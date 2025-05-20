package com.virtuwear.rest.service.implementation;

import com.virtuwear.rest.dto.CoinDto;
import com.virtuwear.rest.entity.Coin;
import com.virtuwear.rest.entity.Transaction;
import com.virtuwear.rest.entity.User;
import com.virtuwear.rest.exception.ResourceNotFoundException;
import com.virtuwear.rest.mapper.CoinMapper;
import com.virtuwear.rest.repository.CoinRepository;
import com.virtuwear.rest.repository.TransactionRepository;
import com.virtuwear.rest.repository.UserRepository;
import com.virtuwear.rest.service.CoinService;
import com.virtuwear.rest.utility.OneMonthFromNow;
import com.virtuwear.rest.utility.TransactionType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CoinServiceImpl implements CoinService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final CoinMapper coinMapper;
    @Autowired
    private final CoinRepository coinRepository;
    @Autowired
    private final TransactionRepository transactionRepository;

    @Override
    public CoinDto createCoin (User user) {
        final int coinBalance = 20;


        Coin coin = new Coin();
        coin.setUser(user);
        coin.setCoinBalance(coinBalance);
        coin.setValidUntil(OneMonthFromNow.getOneMonthFromNow());

        return coinMapper.toDto(coinRepository.save(coin));
    }

    @Override
    public void addRewardCoin(User user, Integer reward) {
        Coin coin = coinRepository.findByUserUid(user.getUid())
                .orElseThrow(() -> new ResourceNotFoundException("Coin not found with UID: " + user.getUid()));

        coin.setCoinBalance(coin.getCoinBalance() + reward);
        coin.setValidUntil(OneMonthFromNow.getOneMonthFromNow());

        Transaction transaction = new Transaction();
        transaction.setAmount(reward);
        transaction.setTransactionType(TransactionType.REWARD);
        transaction.setCoin(coin);
        transactionRepository.save(transaction);

        coinMapper.toDto(coinRepository.save(coin));
    }
}
