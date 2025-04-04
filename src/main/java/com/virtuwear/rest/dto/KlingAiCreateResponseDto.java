package com.virtuwear.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class KlingAiCreateResponseDto {
    private int code;
    private String message;
    private String request_id;
    private DataDto data;

    @Data
    public static class DataDto {
        private String task_id;
        private String task_status;
        private Long created_at;
        private Long updated_at;
    }
}
