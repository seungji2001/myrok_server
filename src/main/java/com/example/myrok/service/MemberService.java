package com.example.myrok.service;

import com.example.myrok.domain.Member;
import com.example.myrok.dto.member.MemberProjectResponse;
import jakarta.transaction.Transactional;

@Transactional
public interface MemberService {
    void checkMemberHaveProject(String socialId);
    Long participateProject(String socialId, String inviteCode);
    Long getOutFromProject(Long memberId, Long projectId, String socialId);
    public Member getMemberInformation(String email);
    public MemberProjectResponse getParticipatedMemberProject(String email);
}
