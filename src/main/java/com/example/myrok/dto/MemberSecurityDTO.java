package com.example.myrok.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.*;
import java.util.stream.Collectors;

//spring seucrity가 사용하는 DTO Type으로 만들어 주어야함
public class MemberSecurityDTO extends User {

    private String socialId, email, password;
    private List<String> roleNames = new ArrayList<>();

    public MemberSecurityDTO( String socialId, String password, List<String> roleNames, String email) {
        super(socialId, password, roleNames.stream().map(str ->
                        //spring security가 사용하는 권한으로 사용해야한다
                        //권한을 만들어주는 type이 있다
                        new SimpleGrantedAuthority("ROLE_"+str))
                .collect(Collectors.toList()));

        this.socialId = socialId;
        this.password = password;
        this.email = email;
        this.roleNames = roleNames;
    }

    //jwt문자열을 만들어서 주고 받는다
    //jwt의 내용물을 Claims라 한다
    public Map<String, Object> getClaims() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("email", email);
        dataMap.put("password",password);
        dataMap.put("socialId", socialId);
        dataMap.put("roleNames", roleNames);
        return dataMap;
    }
}
