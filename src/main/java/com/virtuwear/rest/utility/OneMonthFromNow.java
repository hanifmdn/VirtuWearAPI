package com.virtuwear.rest.utility;

import lombok.Getter;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class OneMonthFromNow {

    public static Timestamp getOneMonthFromNow() {
        LocalDateTime nextMonth = LocalDateTime.now().plusMonths(1);
        return Timestamp.valueOf(nextMonth);
    }

}
