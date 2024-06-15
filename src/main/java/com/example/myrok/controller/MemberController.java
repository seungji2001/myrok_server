package com.example.myrok.controller;


import com.example.myrok.domain.Member;
import com.example.myrok.dto.member.MemberInfoResponse;
import com.example.myrok.dto.member.MemberProjectsResponse;
import com.example.myrok.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/myrok/me/{email}")
    public ResponseEntity<MemberInfoResponse> getMyInformation(@PathVariable String email) {
        final MemberInfoResponse myInformation = memberService.getMemberInformation(email);

        return ResponseEntity.ok(myInformation);
    }

    @GetMapping("/myrok/me/project/{email}")
    public ResponseEntity<MemberProjectsResponse> getMyProject(@PathVariable String email) {
        final MemberProjectsResponse myProject = memberService.getMyProject(email);

        return ResponseEntity.ok(myProject);
    }
}