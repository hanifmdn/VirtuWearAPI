package com.virtuwear.rest.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KlingAiRequestDto {
    private String model_name;
    private String human_image;
    private String cloth_image;
    private String callback_url;
}
