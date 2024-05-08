package com.example.myrok.dto.recordtype;

import com.example.myrok.domain.*;
import com.example.myrok.domain.Record;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import jakarta.validation.constraints.NotBlank;

@Builder
public record RecordDTO (
    @NotBlank(message = "제목은 필수 입력 사항입니다.")
    String recordName,
    @NotBlank(message = "본문은 필수 입력 사항입니다.")
    String recordContent,
    LocalDate recordDate,
    @NotBlank
    Long recordWriterId,
    @NotBlank
    List<Long> memberList,
    List<String> tagList,
    @NotBlank
    Long projectId
){

    public Record toEntity(Project project){
        return Record.builder()
                .recordName(recordName)
                .recordContent(recordContent)
                .recordDate(recordDate)
                .recordWriterId(recordWriterId)
                .project(project)
                .build();
    }

}
