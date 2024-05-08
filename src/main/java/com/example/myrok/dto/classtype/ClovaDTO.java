package com.example.myrok.dto.classtype;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

public class ClovaDTO {
    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class RequestDto{
        @NotEmpty
        private String title;
        @NotEmpty
        private String content;
    }

    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class ResponseDto{
        @NotEmpty
        private String summary;
    }
}
