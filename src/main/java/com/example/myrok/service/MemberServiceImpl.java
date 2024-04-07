package com.example.myrok.service;

import com.example.myrok.domain.Member;
import com.example.myrok.domain.MemberProject;
import com.example.myrok.domain.Project;
import com.example.myrok.exception.CustomException;
import com.example.myrok.repository.MemberProjectRepository;
import com.example.myrok.repository.MemberRepository;
import com.example.myrok.repository.ProjectRepository;
import com.example.myrok.type.ErrorCode;
import com.example.myrok.type.MemberProjectType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Log4j2
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;
    private final MemberProjectRepository memberProjectRepository;

    @Override
    public void initMemberProject(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(NoSuchElementException::new);
        MemberProject memberProject = MemberProject.builder()
                .member(member)
                .memberProjectType(MemberProjectType.NON_PROJECT_MEMBER)
                .project(null)
                .build();
        memberProjectRepository.save(memberProject);
    }

    @Override
    public void checkMemberHaveProject(Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(NoSuchElementException::new);
        Optional<MemberProject> memberProject = memberProjectRepository.findByMemberAndMemberProjectType(member, MemberProjectType.PROJECT_MEMBER);
        //멤버가 진행중인 프로젝트가 없는 경우, 초기화 상태 만들어준다.
        if(memberProject.isEmpty()){
            this.initMemberProject(memberId);
        }else{
            throw new CustomException(ErrorCode.MEMBER_IN_PROJECT, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @Override
    public Long registerProjectToMember(Long memberId, Long projectId) {
        //MemberProject entity에 등록 후, 상태값 변경 필요
        MemberProject memberProject = memberProjectRepository.findByMemberIdAndProjectId(memberId, null).orElseThrow();
        Project project = projectRepository.findById(projectId).orElseThrow(NoSuchElementException::new);
        memberProject.changeProject(project);
        memberProject.changeMemberProjectType(MemberProjectType.PROJECT_MEMBER);
        return memberProjectRepository.save(memberProject).getId();
    }

    @Override
    public Long participateProject(Long memberId, String inviteCode) {
        Optional<Project> project = projectRepository.findByInviteCode(inviteCode);
        if(project.isEmpty()){
            throw new CustomException(ErrorCode.WRONG_INVITE_CODE, HttpStatus.BAD_REQUEST);
        }
        MemberProject memberProject = memberProjectRepository.findByMemberIdAndProjectId(memberId, null).orElseThrow();
        memberProject.changeProject(project.get());
        memberProject.changeMemberProjectType(MemberProjectType.PROJECT_MEMBER);
        return memberProjectRepository.save(memberProject).getId();
    }

    @Override
    public Long getOutFromProject(Long memberId, Long projectId) {
        MemberProject memberProject = memberProjectRepository.findByMemberIdAndProjectIdAndMemberProjectType(memberId, projectId, MemberProjectType.PROJECT_MEMBER).orElseThrow(new CustomException(ErrorCode.MEMBER_NOT_ACCEPTABLE, HttpStatus.NOT_ACCEPTABLE));
        memberProject.changeMemberProjectType(MemberProjectType.NON_PROJECT_MEMBER);
        return memberProjectRepository.save(memberProject).getId();
    }
}
