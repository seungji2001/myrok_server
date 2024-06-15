package com.example.myrok.dto.member;

import com.example.myrok.domain.Member;
public record MemberInfoResponse(
        Long id,
        String name,
        String ImgUrl,
        String email
) {
    public static MemberInfoResponse of(final Member member) {
        return new MemberInfoResponse(
                member.getId(),
                member.getName(),
                member.getImgUrl(),
                member.getEmail()
        );
    }
}