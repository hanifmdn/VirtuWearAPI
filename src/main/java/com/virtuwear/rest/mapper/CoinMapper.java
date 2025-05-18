package com.virtuwear.rest.mapper;

import com.virtuwear.rest.dto.CoinDto;
import com.virtuwear.rest.dto.ModelDto;
import com.virtuwear.rest.entity.Coin;
import com.virtuwear.rest.entity.Model;
import com.virtuwear.rest.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CoinMapper {
    public CoinDto toDto(Coin coin) {
        return new CoinDto(
                coin.getCoinId(),
                coin.getUser().getUid(),
                coin.getCoinBalance(),
                coin.getValidUntil()
        );
    }

    public Coin toEntity(CoinDto dto, User user) {
        Coin coin = new Coin();
        coin.setCoinId(dto.getCoinId());
        coin.setUser(user);
        coin.setCoinBalance(dto.getCoinBalance());
        coin.setValidUntil(dto.getValidUntil());
        return coin;
    }
}


