package com.virtuwear.rest.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column (name = "uid", nullable = false, unique = true)
    private String uid;

    @Column (name = "email", unique = true)
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "token")
    private int token;

    @Column(name = "total_tryon")
    private int totalTryon;

    @Column(name = "total_generate")
    private int totalGenerate;

    @Column(name = "reedemed_referral")
    private String reedemedReferral;

    // One-to-One dengan Referral (1 user hanya punya 1 referral)
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Referral referral;

    // Relationship One to Many dengan SingleGarment
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SingleGarment> singlegarments = new ArrayList<>();

    // Relationship One to Many dengan DoubleGarment
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DoubleGarment> doublegarments = new ArrayList<>();

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
