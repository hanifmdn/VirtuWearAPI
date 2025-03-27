package com.virtuwear.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoubleGarmentDto {
    private Long idDouble;
    private String resultImg;
    private String modelImg;
    private String topImg;
    private String bottomImg;
    private String outfitName;
    private String notes;
    private boolean isBookmark;
    private String userId; // ID User sebagai foreign key
}
