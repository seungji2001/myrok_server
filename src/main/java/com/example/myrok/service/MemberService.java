package com.example.myrok.service;

import com.example.myrok.domain.Project;
import jakarta.transaction.Transactional;

@Transactional
public interface MemberService {
    Boolean checkMemberHaveProject(Long memberId);

    //member에 Project를 새로 등록
    Project registerProjectToMember(Long memberId, Long projectId);

    Project participateProject(Long memberId, String inviteCode);

    Project getOutFromProject(Long memberId, Long projectId);
}
