package com.example.myrok.type;

public enum MemberRole {
    USER("사용자"),
    CREATOR("프로젝트생성자");

    private String value;

    MemberRole(String status) {
        this.value = status;
    }
}