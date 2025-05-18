package com.virtuwear.rest.dto;

import com.virtuwear.rest.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoinDto {
    private Long coinId;
    private String userUid;
    private int coinBalance;
    private Timestamp validUntil;
}
