package com.example.myrok.service;

import com.example.myrok.domain.Member;
import com.example.myrok.domain.Project;
import com.example.myrok.repository.MemberRepository;
import com.example.myrok.repository.ProjectRepository;
import com.example.myrok.type.MemberProjectType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Log4j2
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;

    @Override
    public Boolean checkMemberHaveProject(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        return member.getProject() != null;
    }

    @Override
    public Project registerProjectToMember(Long memberId, Long projectId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        Project project = projectRepository.findById(projectId).orElseThrow();
        member.changeProject(project);
        memberRepository.save(member);
        return member.getProject();
    }

    @Override
    public Project participateProject(Long memberId, String inviteCode) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        Project project = projectRepository.findByInviteCode(inviteCode);
        member.changeProject(project);
        memberRepository.save(member);
        return member.getProject();
    }

    @Override
    public Project getOutFromProject(Long memberId, Long projectId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        Project project = projectRepository.findById(projectId).orElseThrow();
        if(member.getProject() != project)
            throw new IllegalArgumentException("소속되지 않은 프로젝트에서 나갈 수 없습니다.");
        if(member.getMemberProjectType() == MemberProjectType.NON_PROJECT_MEMBER){
            throw new IllegalArgumentException("이미 해당 프로젝트에서 탈퇴를 하였습니다.");
        }
        member.changeMemberProjectType(MemberProjectType.NON_PROJECT_MEMBER);
        return project;
    }
}
