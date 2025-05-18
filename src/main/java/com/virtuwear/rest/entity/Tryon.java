package com.virtuwear.rest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tryon")
public class Tryon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "result_image")
    private String resultImage;

    @Column(name = "outfit_name")
    private String outfitName;

    @Column(name = "note")
    private String note;

    @Column(name = "is_bookmark")
    private boolean isBookmark;

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

    @ManyToOne
    @JoinColumn(name = "garment",nullable = false)
    private Garment garment;

    @ManyToOne
    @JoinColumn(name = "model",nullable = false)
    private Model model;

    @ManyToOne
    @JoinColumn(name = "user_uid", nullable = false)
    private User user;
}
