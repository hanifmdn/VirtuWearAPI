package com.virtuwear.rest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "model")

public class Model {
    @Id
    @Column(name = "model_image",nullable = false,unique = true)
    private String modelImage;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "updated_date")
    private Timestamp updatedDate;

    @PrePersist
    protected void onCreate(){
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        createdDate = now;
        updatedDate = now;
    }

    @PreUpdate
    protected void onUpdate(){
        updatedDate = Timestamp.valueOf(LocalDateTime.now());
    }

    @ManyToOne
    @JoinColumn(name = "user_uid", nullable = false)
    private User user;

    @OneToMany(mappedBy = "model")
    private List<Tryon> tryon = new ArrayList<>();
}
