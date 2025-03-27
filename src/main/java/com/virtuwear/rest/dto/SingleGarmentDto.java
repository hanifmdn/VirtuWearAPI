package com.virtuwear.rest.dto;

import com.virtuwear.rest.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SingleGarmentDto {
    private Long idSingle;
    private String resultImg;
    private String modelImg;
    private String garmentImg;
    private String outfitName;
    private String notes;
    private boolean isBookmark;
    private String userId; // ID User sebagai foreign key
}

