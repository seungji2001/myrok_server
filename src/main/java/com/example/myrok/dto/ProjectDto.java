package com.example.myrok.dto;

import com.example.myrok.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProjectDto {
    private String projectName;
    private String teamName;
}
