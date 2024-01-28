package com.nhnacademy.edu.minidooray.taskapi.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.minidooray.taskapi.dto.member.MemberRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.member.MemberResponse;
import com.nhnacademy.edu.minidooray.taskapi.service.member.MemberService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MemberController.class)
class MemberControllerTest {

     @Autowired
     private MockMvc mockMvc;

     @MockBean
     private MemberService memberService;

     private final ObjectMapper objectMapper = new ObjectMapper();

     @Test
     @DisplayName("성공 : 멤버 등록")
     void postMembers() throws Exception{
          MemberRegisterRequest registerRequest = new MemberRegisterRequest();
          registerRequest.setMemberId("memberId ");
          registerRequest.setProjectId(1L);
          registerRequest.setMemberRole("ADMIN");

          mockMvc.perform(post("/api/members/register")
                  .content(objectMapper.writeValueAsString(registerRequest))
                  .contentType(MediaType.APPLICATION_JSON))
                  .andExpect(status().isCreated());
     }

     @Test
     @DisplayName("실패(validation error) : 멤버 등록")
     void postMemberFail() throws Exception{
          MemberRegisterRequest registerRequest = new MemberRegisterRequest();

          mockMvc.perform(post("/api/members/register")
                          .content(objectMapper.writeValueAsString(registerRequest))
                          .contentType(MediaType.APPLICATION_JSON))
                  .andExpect(status().is4xxClientError());
     }

     @Test
     @DisplayName("성공 : 멤버, 프로젝트 아이디로 조회")
     void getMember() throws Exception {
          given(memberService.getMember("memberId", 1L))
                  .willReturn(new MemberResponse() {
                       @Override
                       public Long getMemberNumber() {
                            return 1L;
                       }

                       @Override
                       public String getMemberId() {
                            return "memberId";
                       }

                       @Override
                       public String getMemberRole() {
                            return "ADMIN";
                       }
                  });

          mockMvc.perform(get("/api/members/{member_id}/{project_id}", "memberId", 1L))
                  .andExpect(status().isOk())
                  .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                  .andExpect(jsonPath("$.memberNumber", equalTo(1)))
                  .andExpect(jsonPath("$.memberId", equalTo("memberId")))
                  .andExpect(jsonPath("$.memberRole", equalTo("ADMIN")));
     }

     @Test
     @DisplayName("성공 : 멤버 전체 조회")
     void getMembers() throws Exception{
          given(memberService.getMembers(anyLong()))
                  .willReturn(List.of(new MemberResponse() {
                       @Override
                       public Long getMemberNumber() {
                            return 1L;
                       }

                       @Override
                       public String getMemberId() {
                            return "memberId";
                       }

                       @Override
                       public String getMemberRole() {
                            return "ADMIN";
                       }
                  }));

          mockMvc.perform(get("/api/members/{project_id}", anyLong()))
                  .andExpect(status().isOk())
                  .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                  .andExpect(jsonPath("$[0].memberId", equalTo("memberId")))
                  .andExpect(jsonPath("$[0].memberNumber", equalTo(1)))
                  .andExpect(jsonPath("$[0].memberRole", equalTo("ADMIN")));
     }
}