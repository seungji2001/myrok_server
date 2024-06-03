package com.example.myrok.dto;

import lombok.*;

import java.util.List;

public class DashBoardDTO {
    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    @ToString
    public static class TagResponseDTO{
        private List<TagDTO> tags;
        private Long etcPercentage;
    }
    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    @ToString
    public static class TagListDTO{
        private List<TagDTO> tags;
        private Long totalCount;
    }
}
