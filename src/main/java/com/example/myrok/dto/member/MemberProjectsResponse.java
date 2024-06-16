package com.example.myrok.dto.member;


import com.example.myrok.domain.MemberProject;

import java.util.List;
import java.util.Optional;

public record MemberProjectsResponse(List<MemberProjectResponse> memberProjects) {

    public static MemberProjectsResponse of(Optional<MemberProject> allByMemberId) {
        return new MemberProjectsResponse(allByMemberId.stream()
                .map(MemberProjectResponse::of)
                .toList());
    }
}