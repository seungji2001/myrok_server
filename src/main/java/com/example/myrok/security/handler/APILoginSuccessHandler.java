package com.example.myrok.security.handler;

import com.example.myrok.dto.MemberSecurityDTO;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

//인증 성공시 핸들링
@Log4j2
public class APILoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        log.info("----------------------------");
        log.info(authentication);
        log.info("----------------------------");

        MemberSecurityDTO memberDTO = (MemberSecurityDTO) authentication.getPrincipal();
        // 여기에 Access랑 refresh token을 넣는다
        Map<String, Object> claims = memberDTO.getClaims();
        claims.put("accessToken", "");
        claims.put("refreshToken", "");

        Gson gson = new Gson();

        String jsonStr = gson.toJson(claims);

        response.setContentType("application/json; charset=UTF-8");

        PrintWriter printWriter = response.getWriter();
        printWriter.println(jsonStr);
        printWriter.close();
    }
}
