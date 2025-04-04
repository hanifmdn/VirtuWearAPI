package com.virtuwear.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
public class KlingAiSingleTaskResponseDto {
    private int code;
    private String message;
    private String request_id;
    private DataDto data;

    @Data
    @JsonInclude(JsonInclude.Include.ALWAYS)
    public static class DataDto {
        private String task_id;

        private String task_status;

        private String task_status_msg;

        private Long created_at;

        private Long updated_at;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private TaskResult task_result;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class TaskResult {
        private List<Image> images;
    }

    @Data
    @JsonInclude(JsonInclude.Include.ALWAYS)
    public static class Image {
        private int index;
        private String url;
    }
}
