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

    @Column(name = "cooldown")
    private Timestamp cooldown;

    // Relationship One-To-One dengan User (opsional, bisa ditambahkan)
    @OneToOne(mappedBy = "referral")
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

    public Referral(String referralCode, Long totalUsed, Timestamp cooldown, Timestamp createdDate, Timestamp updatedDate) {
        this.referralCode = referralCode;
        this.totalUsed = totalUsed;
        this.cooldown = cooldown;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
