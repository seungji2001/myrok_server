package com.example.myrok.security.filter;


import com.example.myrok.util.JWTUtil;
import com.google.gson.Gson;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

//모든 리케스트에 대해 동작한다
@Log4j2
public class JWTCheckFilter extends OncePerRequestFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        //필터가 필요없는것
        //로그인하는 첫 페이지와 같이 필터링이 필요 없는 것

        //true == not checking
        String path = request.getRequestURI();
        log.info("check uri -------" + path);

        //false == check //
        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        log.info("=============================");
        log.info("=============================");
        log.info("=============================");

        String authHeaderStr = request.getHeader("Authorization");

        //Bearer //7 JWT 문자열
        try {
//Bearer accestoken...
            String accessToken = authHeaderStr.substring(7);
            Map<String, Object> claims = JWTUtil.validateToken(accessToken);
            log.info("JWT claims: " + claims);
            //dest
            //성공하면 다음 목적지로 보낸다
            filterChain.doFilter(request, response);
        }catch(Exception e){
            //만료와 같이 문제가 생길 경우 처리해준다
            log.error("JWT Check Error..............");
            log.error(e.getMessage());
            Gson gson = new Gson();
            String msg = gson.toJson(Map.of("error", "ERROR_ACCESS_TOKEN"));
            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            printWriter.println(msg);
            printWriter.close();
        }
    }
}