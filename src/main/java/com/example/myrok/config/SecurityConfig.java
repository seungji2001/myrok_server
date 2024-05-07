package com.example.myrok.config;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@RequiredArgsConstructor
//@Configuration(proxyBeanMethods = false)
//@ConditionalOnDefaultWebSecurity
//@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
//public class SecurityConfig {
//
//    //private final CustomOAuth2UserService customOAuth2UserService;
//
//    @Bean
//    @Order(SecurityProperties.BASIC_AUTH_ORDER)
//    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .oauth2Login();
//
//        return http.build();
//    }
//}