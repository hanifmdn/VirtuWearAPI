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
@Table(name = "double_garment")
public class DoubleGarment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_double", nullable = false, unique = true)
    private Long idDouble;

    // Relationships
    @ManyToOne
    @JoinColumn(name = "uid", nullable = false)
    private User user;

    @Column(name = "result_img")
    private String resultImg;

    @Column(name = "model_img")
    private String modelImg;

    @Column(name = "top_img")
    private String topImg;

    @Column(name = "bottom_img")
    private String bottomImg;

    @Column(name = "outfit_name")
    private String outfitName;

    @Column(name = "notes")
    private String notes;

    @Column(name = "isBookmark")
    private boolean isBookmark;




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
