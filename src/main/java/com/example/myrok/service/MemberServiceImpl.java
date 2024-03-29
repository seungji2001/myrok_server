package com.example.myrok.service;

import com.example.myrok.domain.Member;
import com.example.myrok.domain.Project;
import com.example.myrok.repository.MemberRepository;
import com.example.myrok.repository.ProjectRepository;
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
    public Project registerProjectToMember(Long memberId, Long projectId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        Project project = projectRepository.findById(projectId).orElseThrow();
        if(member.getProject() != null){
            throw new IllegalArgumentException("이미 소속된 프로젝트가 존재합니다. 기존 프로젝트에서 나간 후 진행해주세요.");
        }
        member.changeProject(project);
        memberRepository.save(member);
        return member.getProject();
    }
}
