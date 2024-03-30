package com.example.myrok.service;

import com.example.myrok.domain.Member;
import com.example.myrok.domain.Project;
import com.example.myrok.dto.ProjectDto;
import com.example.myrok.repository.MemberRepository;
import com.example.myrok.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor // property에 대한 의존성 주입
@Service
@Log4j2
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;

    @Override
    public Long register(ProjectDto projectDto) {
        Project project = dtoToEntity(projectDto);
        return projectRepository.save(project).getId();
    }

    @Override
    //멤버가 프로젝트 나가기를 할 때마다, 해당 프로젝트 내 소속된 인원이 없는지 확인 필요
    //없을경우 해당 프로젝트 삭제 핸들링
    public Long checkProjectDelete(Project project) {
        if(project.getMemberList().isEmpty()){
            project.changeDeleted();
        }
        return project.getId();
    }
}
