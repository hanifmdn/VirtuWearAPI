package com.virtuwear.rest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "referral")

public class Referral {
    @Id
    @Column(name = "referral_code", nullable = false, unique = true)
    private String referralCode;

    @Column(name = "total_used")
    private Long totalUsed;

    @OneToOne
    @JoinColumn(name = "user_uid", referencedColumnName = "uid", nullable = false, unique = true)
    private User user;

    // Date
    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "updated_date")
    private Timestamp updatedDate;

    @PrePersist
    protected void onCreate() {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        createdDate = now;
        updatedDate = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = Timestamp.valueOf(LocalDateTime.now());
    }

}
