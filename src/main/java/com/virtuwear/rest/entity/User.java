package com.virtuwear.rest.entity;

import jakarta.persistence.*;
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

    // Relationship One-To-One dengan Referral
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "referral_code", referencedColumnName = "referral_code")
    private Referral referral;

    // Relasi One-to-Many dengan SingleGarment
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SingleGarment> garments = new ArrayList<>();


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
