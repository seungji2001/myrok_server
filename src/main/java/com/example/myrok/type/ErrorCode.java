package com.example.myrok.type;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode implements EnumModel{
    // COMMON
    RESOURCE_NOT_FOUND(40400, "C001", "존재하지 않습니다."),
    //project
    MEMBER_IN_PROJECT(40600, "P001", "기존 프로젝트에서 나간 후 진행해주세요."),
    LIMITED_MEMBER(40601, "P002", "프로젝트 인원이 초과되었습니다."),
    MEMBER_NOT_ACCEPTABLE(40602, "P003", "해당 프로젝트에 대한 권한이 없습니다."),
    WRONG_INVITE_CODE(40401, "P004", "잘못된 참여코드 입니다.");

    private int status;
    private String code;
    private String message;
    private String detail;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    @Override
    public String getKey() {
        return this.code;
    }

    @Override
    public String getValue() {
        return this.message;
    }
}
