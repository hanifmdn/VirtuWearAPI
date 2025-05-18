package com.virtuwear.rest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "coin")
public class Coin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coin_id", nullable = false, unique = true)
    private Long coinId;

    @OneToOne
    @JoinColumn(name = "user_uid", referencedColumnName = "uid", nullable = false, unique = true)
    private User user;

    @Column(nullable = false)
    private int coinBalance;

    @Column(nullable = false)
    private Timestamp validUntil;

    // Date
    @Column(name = "created_date", updatable = false)
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
