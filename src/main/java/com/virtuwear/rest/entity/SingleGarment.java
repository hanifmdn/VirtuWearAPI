package com.virtuwear.rest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "single_garment")
public class SingleGarment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_single", nullable = false, unique = true)
    private Long idSingle;

    @Column(name = "result_img")
    private String resultImg;

    @Column(name = "model_img")
    private String modelImg;

    @Column(name = "garment_img")
    private String garmentImg;

    @Column(name = "outfit_name")
    private String outfitName;

    @Column(name = "notes")
    private String notes;

    @Column(name = "status")
    private String status;

    @Column(name = "isBookmark")
    private boolean isBookmark;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
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
