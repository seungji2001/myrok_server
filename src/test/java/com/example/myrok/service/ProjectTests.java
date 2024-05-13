package com.example.myrok.service;

import com.example.myrok.dto.classtype.ProjectDTO;
import com.example.myrok.dto.classtype.RecordDTO;
import com.example.myrok.dto.pagination.PageRequestDto;
import com.example.myrok.dto.pagination.PageResponseDto;
import com.example.myrok.repository.ProjectRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Log4j2
public class ProjectTests {
    @Autowired
    ProjectService projectService;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    RecordService recordService;

    @Test
    public void testRegister(){
        ProjectDTO.RegisterProject projectDto = ProjectDTO.RegisterProject.builder()
                .projectName("test name 1")
                .startDate("")
                .endDate("")
                .build();
        Long projectId = projectService.register(projectDto, 1L);
    }

    @Test
    public void testGetMembersByProjectId(){
        ProjectDTO.ProjectMembersDto projectMembersDto = projectService.getProjectMembers(4L);
        log.info(projectMembersDto);
    }

    @Test
    public void getRecordListTests(){
        List<RecordDTO.RecordListObject> listObjects = recordService.getRecords(1L);
        System.out.println(Arrays.asList(listObjects));
    }

    @Test
    public void getRecordListPaginationTests(){
        PageRequestDto pageRequestDto = PageRequestDto.builder().build();
        PageResponseDto<RecordDTO.RecordListObject> listObjects = recordService.getRecords(pageRequestDto, 1L);
        System.out.println(Arrays.asList(listObjects));
    }

    @Test
    public void findRecordByTagAndValue(){
        PageRequestDto pageRequestDto = PageRequestDto.builder().build();
        recordService.getRecordsBySearch(pageRequestDto, "뉴", "제주도");
    }
}
