package com.virtuwear.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TryonDto {
    private Long id;
    private String resultImage;
    private String outfitName;
    private String note;
    private boolean isBookmark;
    private String garmentImage;
    private String modelImage;
    private String userUid;
}

