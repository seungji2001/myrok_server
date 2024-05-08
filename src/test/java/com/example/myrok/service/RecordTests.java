package com.example.myrok.service;

import com.example.myrok.domain.Member;
import com.example.myrok.domain.MemberProject;
import com.example.myrok.domain.Project;
import com.example.myrok.domain.Record;
import com.example.myrok.dto.recordtype.RecordDTO;
import com.example.myrok.exception.CustomException;
import com.example.myrok.repository.MemberProjectRepository;
import com.example.myrok.repository.MemberRepository;
import com.example.myrok.repository.ProjectRepository;
import com.example.myrok.repository.RecordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.example.myrok.type.MemberProjectType.PROJECT_MEMBER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RecordTests {

    @Autowired
    protected MockMvc mockMVC;

    @Autowired
    private RecordServiceImpl recordService;

    @MockBean
    private RecordRepository recordRepository;
    @MockBean
    private ProjectRepository projectRepository;
    @MockBean
    private MemberProjectRepository memberProjectRepository;
    @MockBean
    private MemberRepository memberRepository;
    @MockBean
    private TagServiceImpl tagService;
    @MockBean
    private MemberRecordServiceImpl memberRecordService;
    @MockBean
    private RecordTagServiceImpl recordTagService;


    @Transactional(rollbackFor = Exception.class)
    @DisplayName("회의록 저장 검사")
    @Test
    public void saveRecordSuccess() {
        // Given
        Long projectId = 1L;
        Long memberId1 = 1L;
        Long memberId2 = 2L;

        List<Long> memberList = List.of(memberId1, memberId2);
        List<String> tagList = List.of("tag1", "tag2", "tag3");
        Long recordWriterId = memberId1;

        Project mockProject = Project.builder()
                .id(projectId)
                .projectName("프로젝트")
                .build();
        Member mockMember1 = Member.builder()
                .id(memberId1)
                .name("김민서")
                .build();
        Member mockMember2 = Member.builder()
                .id(memberId1)
                .name("임지연")
                .build();
        MemberProject mockMemberProject1 = new MemberProject(1L, mockMember1, mockProject, PROJECT_MEMBER);
        MemberProject mockMemberProject2 = new MemberProject(2L, mockMember2, mockProject, PROJECT_MEMBER);
        Record mockRecord = new Record();

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(mockProject));
        when(memberRepository.findById(memberId1)).thenReturn(Optional.of(mockMember1));
        when(memberRepository.findById(memberId2)).thenReturn(Optional.of(mockMember2));
        when(memberProjectRepository.findByMemberIdAndProjectId(memberId1,projectId)).thenReturn(Optional.of(mockMemberProject1));
        when(memberProjectRepository.findByMemberIdAndProjectId(memberId2,projectId)).thenReturn(Optional.of(mockMemberProject2));
        when(recordRepository.save(any(Record.class))).thenReturn(mockRecord);

        RecordDTO recordDTO = new RecordDTO(
                "Test Record",
                "This is a test record.",
                LocalDate.now(),
                projectId,
                memberList,
                tagList,
                recordWriterId
        );

        //When
        Record savedRecord=recordService.save(recordDTO);

        //Then
        assertNotNull(savedRecord);
        verify(projectRepository, times(1)).findById(anyLong());
        verify(recordRepository, times(1)).save(any(Record.class));
        verify(tagService, times(recordDTO.tagList().size())).save(anyString());
        verify(memberRecordService, times(1)).save(anyList(), eq(savedRecord), anyLong());
        verify(recordTagService, times(1)).save(anyList(), eq(savedRecord));
    }


    @Test
    @DisplayName("회의록 Soft delete 검사")
    public void deleteRecordSuccess() {
        // Given
        Long recordId = 1L;
        Record mockRecord = new Record();
        mockRecord.setId(recordId);

        when(recordRepository.findById(recordId)).thenReturn(Optional.of(mockRecord));
        doNothing().when(tagService).delete(anyList());
        doNothing().when(memberRecordService).delete(anyLong());
        doNothing().when(recordTagService).delete(anyLong());

        // When
        recordService.deleteUpdate(recordId);

        // Then
        //times() : 메소드가 호출된 횟수
        verify(recordRepository, times(1)).findById(recordId);
        verify(recordRepository, times(1)).save(mockRecord);
        verify(tagService, times(1)).delete(mockRecord.getRecordTagList());
        verify(memberRecordService, times(1)).delete(recordId);
        verify(recordTagService, times(1)).delete(recordId);
        assertTrue(mockRecord.getDeleted());
    }

    @Test
    @DisplayName("이미 삭제된 회의록 삭제 시도 검사")
    public void deleteAlreadyDeletedRecordThrowsException() {
        // Given
        Long recordId = 1L;
        Record mockRecord = new Record();
        mockRecord.delete(); // 이미 삭제된 상태로 설정

        when(recordRepository.findById(recordId)).thenReturn(Optional.of(mockRecord));

        // When
        // CustomException 이 던져지는지 assert
        assertThrows(CustomException.class, () -> recordService.deleteUpdate(recordId));

        //Then
        // never() : 메소드가 실행되지 않았음을 검증. 삭제된 회의록의 삭제 시도니 관련 객체들도 삭제되면 안됨
        verify(recordRepository, times(1)).findById(recordId);
        verify(recordRepository, never()).save(any(Record.class));
        verify(tagService, never()).delete(anyList());
        verify(memberRecordService, never()).delete(anyLong());
        verify(recordTagService, never()).delete(anyLong());
    }

}
