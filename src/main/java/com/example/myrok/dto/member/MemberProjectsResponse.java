package com.example.myrok.dto.member;


import com.example.myrok.domain.MemberProject;

import java.util.List;

public record MemberProjectsResponse(List<MemberProjectResponse> memberProjectResponseList) {

    public static MemberProjectsResponse of(List<MemberProject> allByMemberId) {
        return new MemberProjectsResponse(allByMemberId.stream()
                .map(MemberProjectResponse::of)
                .toList());
    }
}