package com.virtuwear.rest.service;

import com.virtuwear.rest.entity.User;

public interface RedeemLogService {
    void redeemLog(User inviter, User invited);
}
