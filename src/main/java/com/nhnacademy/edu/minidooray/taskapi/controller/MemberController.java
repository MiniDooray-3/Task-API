package com.nhnacademy.edu.minidooray.taskapi.controller;

import com.nhnacademy.edu.minidooray.taskapi.dto.member.MemberRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.member.MemberResponse;
import com.nhnacademy.edu.minidooray.taskapi.repository.MemberRepository;
import com.nhnacademy.edu.minidooray.taskapi.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
public class MemberController {

     private final MemberService memberService;


     @PostMapping("/api/members/register")
     @ResponseStatus(HttpStatus.CREATED)
     public void postMembers(@RequestBody MemberRegisterRequest registerRequest){
          memberService.createMembers(registerRequest);
     }

     @GetMapping("/api/members/{member_id}/{project_id}")
     @ResponseStatus(HttpStatus.OK)
     public ResponseEntity<MemberResponse> getMember(
             @PathVariable("member_id") String memberId,
             @PathVariable("project_id") Long projectId
     ){
          return ResponseEntity.ok(memberService.getMember(memberId, projectId));
     }
}
