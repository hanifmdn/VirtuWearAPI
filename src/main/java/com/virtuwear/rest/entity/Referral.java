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

    // Relationships
    @OneToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    private User user;

    @Column(name = "total_used")
    private Long totalUsed;

    @Column(name = "cooldown")
    private Timestamp cooldown;

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

        if (referralCode == null || referralCode.isEmpty()) {
            referralCode = generateReferralCode();
        }
    }

    private String generateReferralCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder referralCodeBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(characters.length());
            referralCodeBuilder.append(characters.charAt(index));
        }
        return referralCodeBuilder.toString();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = Timestamp.valueOf(LocalDateTime.now());
    }
}
