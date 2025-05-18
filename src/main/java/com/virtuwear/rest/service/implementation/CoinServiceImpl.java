package com.virtuwear.rest.service.implementation;

import com.virtuwear.rest.dto.CoinDto;
import com.virtuwear.rest.entity.Coin;
import com.virtuwear.rest.entity.Garment;
import com.virtuwear.rest.entity.User;
import com.virtuwear.rest.mapper.CoinMapper;
import com.virtuwear.rest.repository.CoinRepository;
import com.virtuwear.rest.repository.UserRepository;
import com.virtuwear.rest.service.CoinService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CoinServiceImpl implements CoinService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final CoinMapper coinMapper;
    @Autowired
    private final CoinRepository coinRepository;

    @Override
    public CoinDto createCoin (User user) {
        final int coinBalance = 20;
        LocalDateTime nextMonth = LocalDateTime.now().plusMonths(1);
        Timestamp oneMonthValid = Timestamp.valueOf(nextMonth);

        Coin coin = new Coin();
        coin.setUser(user);
        coin.setCoinBalance(coinBalance);
        coin.setValidUntil(oneMonthValid);

        return coinMapper.toDto(coinRepository.save(coin));
    }
}
