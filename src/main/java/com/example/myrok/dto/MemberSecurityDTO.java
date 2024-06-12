package com.example.myrok.dto;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.*;

//spring seucrity가 사용하는 DTO Type으로 만들어 주어야함
public class MemberSecurityDTO extends User {

    private String socialId, name, password, memberRole;

    public MemberSecurityDTO(String socialId, String password, String memberRole, String name) {
        super(socialId, password, Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + memberRole)));

        this.socialId = socialId;
        this.password = password;
        this.name = name;
        this.memberRole = memberRole;
    }

    //jwt문자열을 만들어서 주고 받는다
    //jwt의 내용물을 Claims라 한다
    public Map<String, Object> getClaims() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", name);
        dataMap.put("password",password);
        dataMap.put("socialId", socialId);
        dataMap.put("memberRole", memberRole);
        return dataMap;
    }
}
