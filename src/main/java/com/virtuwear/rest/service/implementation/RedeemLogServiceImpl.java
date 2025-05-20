package com.virtuwear.rest.service.implementation;

import com.virtuwear.rest.entity.RedeemLog;
import com.virtuwear.rest.entity.User;
import com.virtuwear.rest.repository.RedeemLogRepository;
import com.virtuwear.rest.service.RedeemLogService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RedeemLogServiceImpl implements RedeemLogService {
    @Autowired
    private RedeemLogRepository redeemLogRepository;

    @Override
    public void redeemLog(User inviter, User invited) {
        RedeemLog redeemLog = new RedeemLog();
        redeemLog.setInviterEmail(inviter);
        redeemLog.setInvitedEmail(invited);
        redeemLogRepository.save(redeemLog);
    }
}
