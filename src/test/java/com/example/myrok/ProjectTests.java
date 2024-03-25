package com.example.myrok;

import com.example.myrok.dto.ProjectDto;
import com.example.myrok.service.ProjectService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ProjectTests {
    @Autowired
    ProjectService projectService;

    @Test
    public void testRegister(){
        ProjectDto projectDto = ProjectDto.builder()
                .projectName("test name 1")
                .teamName("test team 1")
                .build();

        log.info(projectService.register(1l, projectDto));
    }
}
