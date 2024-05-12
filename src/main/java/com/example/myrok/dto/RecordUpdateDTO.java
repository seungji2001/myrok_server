package com.example.myrok.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Builder
public record RecordUpdateDTO(
        @NotBlank(message = "제목은 필수 입력 사항입니다.")
        String recordName,
        @NotBlank(message = "본문은 필수 입력 사항입니다.")
        String recordContent,
        @NotBlank
        Long recordWriterId,
        List<String> tagList
) {
}
