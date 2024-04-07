package com.example.myrok.dto;

import com.example.myrok.domain.Member;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jdk.jfr.Description;
import lombok.*;

import java.time.LocalDate;

public class ProjectDto {
    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class RegisterProject{
        private String projectName;
        @NonNull
        private String endDate;
        @NonNull
        private String startDate;
    }

    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class ParticipateProject{
        @NonNull
        private String inviteCode;
    }

    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class ProjectMemberDto{
        @NonNull
        private Long projectId;
        @NonNull
        private Long memberId;
    }

}
