package com.example.myrok.dto.member;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberProjectResponse{
    private Long projectId;
    private String projectName;
    private String startDate;
    private String endDate;
}