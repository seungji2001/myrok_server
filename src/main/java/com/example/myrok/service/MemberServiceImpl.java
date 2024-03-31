package com.example.myrok.service;

import com.example.myrok.domain.Member;
import com.example.myrok.domain.Project;
import com.example.myrok.exception.CustomException;
import com.example.myrok.repository.MemberRepository;
import com.example.myrok.repository.ProjectRepository;
import com.example.myrok.type.ErrorCode;
import com.example.myrok.type.MemberProjectType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Log4j2
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;

    @Override
    public void checkMemberHaveProject(Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow();
        //멤버에게 프로젝트가 있으며, 프로젝트 회원인지 확인
        if(member.getMemberProjectType()==MemberProjectType.PROJECT_MEMBER){
            throw new CustomException(ErrorCode.MEMBER_IN_PROJECT, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @Override
    public Project registerProjectToMember(Long memberId, Long projectId) {
        Member member = memberRepository.findById(memberId).orElseThrow(NoSuchElementException::new);
        Project project = projectRepository.findById(projectId).orElseThrow(NoSuchElementException::new);
        member.changeProject(project);
        member.changeMemberProjectType(MemberProjectType.PROJECT_MEMBER);
        memberRepository.save(member);
        return member.getProject();
    }

    @Override
    public Project participateProject(Long memberId, String inviteCode) {
        Member member = memberRepository.findById(memberId).orElseThrow(NoSuchElementException::new);
        Project project = projectRepository.findByInviteCode(inviteCode);
        if(project.getMemberList().size() + 1 > project.getLimitMember()){
            throw new CustomException(ErrorCode.LIMITED_MEMBER, HttpStatus.NOT_ACCEPTABLE);
        }
        member.changeProject(project);
        member.changeMemberProjectType(MemberProjectType.PROJECT_MEMBER);
        memberRepository.save(member);
        return member.getProject();
    }

    @Override
    public Project getOutFromProject(Long memberId, Long projectId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        Project project = projectRepository.findById(projectId).orElseThrow();
        if(member.getProject() != project || member.getMemberProjectType() == MemberProjectType.NON_PROJECT_MEMBER){
            throw new CustomException(ErrorCode.MEMBER_NOT_ACCEPTABLE, HttpStatus.NOT_ACCEPTABLE);
        }
        member.changeMemberProjectType(MemberProjectType.NON_PROJECT_MEMBER);
        memberRepository.save(member);
        return project;
    }
}
