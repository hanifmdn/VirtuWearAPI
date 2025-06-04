package com.virtuwear.rest.service;

import com.virtuwear.rest.dto.CoinDto;
import com.virtuwear.rest.entity.User;

public interface CoinService {
    CoinDto createCoin (User user);

    void addRewardCoin(User user, Integer reward);

    void addPurchaseCoin(String userUid, int coins);

}
