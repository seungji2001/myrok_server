package com.example.myrok.service;

import jakarta.transaction.Transactional;

@Transactional
public interface MemberService {
    //멤버 초기화 상태 만들어주기 MemberProject(memberId, null, MemberProject.NON_PROJECT_MEMBER)
    void initMemberProject(Long memberId);

    //멤버가 보유한 프로젝트가 있는지 확가
    void checkMemberHaveProject(Long memberId);

    //member에 Project를 새로 등록
    Long registerProjectToMember(Long memberId, Long projectId);

    //member가 프로젝트에 참여
    Long participateProject(Long memberId, String inviteCode);

    Long getOutFromProject(Long memberId, Long projectId);
}
