package com.example.myrok.dto.record;

import com.example.myrok.dto.member.MemberDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
public record RecordResponseDTO (
        Long recordId,
        String recordName,
        String recordContent,
        LocalDate recordDate,
        Long recordWriterId,
        List<MemberDTO.MemberNameDto> memberList,
        List<String> tagList,
        Long projectId
){

}
