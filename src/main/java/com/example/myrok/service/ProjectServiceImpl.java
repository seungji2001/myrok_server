package com.example.myrok.service;

import com.example.myrok.domain.Member;
import com.example.myrok.domain.Project;
import com.example.myrok.dto.ProjectDto;
import com.example.myrok.repository.MemberProjectRepository;
import com.example.myrok.repository.MemberRepository;
import com.example.myrok.repository.ProjectRepository;
import com.example.myrok.type.MemberProjectType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor // property에 대한 의존성 주입
@Service
@Log4j2
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;
    private final MemberProjectRepository memberProjectRepository;

    @Override
    public Long register(ProjectDto.RegisterProject projectDto) {
        if(Objects.equals(projectDto.getStartDate(), "") && Objects.equals(projectDto.getEndDate(), "")){
            projectDto.setStartDate("1000-01-01");
            projectDto.setEndDate("3000-01-01");
        }
        Project project = dtoToEntity(projectDto);
        return projectRepository.save(project).getId();
    }

    @Override
    //멤버가 프로젝트 나가기를 할 때마다, 해당 프로젝트 내 소속된 인원이 없는지 확인 필요
    //소속된 멤버의 상태가 모두 NON_PROJECT_MEMBER일 경우
    public Long checkProjectDelete(Long projectId) {
        boolean canDelete = true;
        Project project = projectRepository.findById(projectId).orElseThrow(NoSuchElementException::new);
        int count = (int) memberProjectRepository.findAllByProjectIdAndMemberProjectType(projectId, MemberProjectType.PROJECT_MEMBER).stream().count();
        //한명이라도 소속 멤버인 경우 delete하지 않는다
        if(count == 0)
               project.changeDeleted();
        projectRepository.save(project);
        return project.getId();
    }
}
