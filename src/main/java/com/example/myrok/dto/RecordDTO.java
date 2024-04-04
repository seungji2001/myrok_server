package com.example.myrok.dto;

import com.example.myrok.domain.Member;
import com.example.myrok.domain.Project;
import com.example.myrok.domain.Record;
import com.example.myrok.domain.Tag;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import jakarta.validation.constraints.NotBlank;

public record RecordDTO (
    @NotBlank(message = "제목은 필수 입력 사항입니다.")
    String recordName,
    @NotBlank(message = "본문은 필수 입력 사항입니다.")
    String recordContent,
    LocalDate recordDate,
    List<Long> memberList,
    List<String> tagList,
    Long projectId
){
    public Record toEntity(List<Member> memberList, List<Tag> tagList, Project project){
        return Record.builder()
                .recordName(recordName)
                .recordContent(recordContent)
                .recordDate(recordDate)
                .memberList(memberList)
                .tagList(tagList)
                .project(project)
                .build();
    }

}
