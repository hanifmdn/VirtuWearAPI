package com.virtuwear.rest.entity;

import com.virtuwear.rest.utility.TransactionType;
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
@Table(name = "trasaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long transactionId;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "user_uid", referencedColumnName = "user_uid"),
            @JoinColumn(name = "coin_id", referencedColumnName = "coin_id")
    })
    private Coin coin;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(nullable = false)
    private int totalCoin;

    @Column(nullable = false)
    private Timestamp transactionDate;

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
