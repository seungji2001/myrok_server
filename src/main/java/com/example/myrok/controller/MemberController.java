package com.example.myrok.controller;

import com.example.myrok.dto.member.MemberInfoResponse;
import com.example.myrok.dto.member.MemberProjectsResponse;
import com.example.myrok.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myrok/me")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<MemberInfoResponse> getMyInformation(Principal principal) {
        final MemberInfoResponse myInformation = memberService.getMemberInformation(principal.getName());

        return ResponseEntity.ok(myInformation);
    }

    @GetMapping("/project")
    public ResponseEntity<MemberProjectsResponse> getMyProject(@PathVariable String email) {
        final MemberProjectsResponse myProject = memberService.getMyProject(email);

        return ResponseEntity.ok(myProject);
    }
}