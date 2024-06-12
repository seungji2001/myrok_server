package com.example.myrok.dto.classtype;

import lombok.*;

public class MemberDTO {
    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    @ToString
    public static class MemberNameDto{
        private Long memberId;
        private String name;
    }

    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    @ToString
    public static class MemberInformation{
        private String socialId;
        private String email;
        private String name;
        private String postUrl;
    }
}
