package com.example.myrok.controller;

import com.example.myrok.dto.exception.ResponseDto;
import com.example.myrok.service.OAuth2Service;
import com.example.myrok.type.ELoginProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myrok")
public class AuthController {

    private final OAuth2Service oAuth2Service;


    //callback url 정보 담아오기
    @GetMapping("/auth/google")
    public ResponseDto<Map<String,String>> getGoogleRedirectUrl(){
        Map<String, String> map = new HashMap<>();
        map.put("url", oAuth2Service.getRedirectUrl(ELoginProvider.GOOGLE));
        return ResponseDto.ok(map);
    }

    @GetMapping("/reissue/google")
    public void getGoogleAccessToken(String code, HttpServletResponse response) throws IOException {
        String accessToken = oAuth2Service.getAccessToken(code, ELoginProvider.GOOGLE);
        oAuth2Service.login(response, accessToken, ELoginProvider.GOOGLE);
    }
}