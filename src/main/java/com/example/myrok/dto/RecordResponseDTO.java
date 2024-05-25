package com.example.myrok.dto;

import com.example.myrok.domain.*;
import com.example.myrok.domain.Record;
import com.example.myrok.dto.classtype.MemberDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import jakarta.validation.constraints.NotBlank;
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
