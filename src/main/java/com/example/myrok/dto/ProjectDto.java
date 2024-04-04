package com.example.myrok.dto;

import com.example.myrok.domain.Member;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class ProjectDto {
    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class RegisterProject{
        private String projectName;
        @Nullable
        private String endDate;
        @Nullable
        private String startDate;
        @Builder.Default
        private int limitMember = 6;
    }

}
