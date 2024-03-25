package com.example.myrok.service;

import com.example.myrok.domain.Project;
import com.example.myrok.dto.ProjectDto;
import jakarta.transaction.Transactional;

@Transactional
public interface ProjectService {
    Long register(Long memberId, ProjectDto requestDto);


    default Project dtoToEntity(ProjectDto projectDto){
        return Project.builder()
                .projectName(projectDto.getProjectName())
                .teamName(projectDto.getTeamName())
                .build();
    }

    default ProjectDto entityToDto(Project project){
        return ProjectDto.builder()
                .projectName(project.getProjectName())
                .teamName(project.getTeamName())
                .build();
    }
}
