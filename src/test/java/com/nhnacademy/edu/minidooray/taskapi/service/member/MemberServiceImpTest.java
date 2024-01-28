package com.nhnacademy.edu.minidooray.taskapi.service.member;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.nhnacademy.edu.minidooray.taskapi.domain.Member;
import com.nhnacademy.edu.minidooray.taskapi.domain.Project;
import com.nhnacademy.edu.minidooray.taskapi.dto.member.MemberRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.member.MemberResponse;
import com.nhnacademy.edu.minidooray.taskapi.exception.MemberAlreadyExistsException;
import com.nhnacademy.edu.minidooray.taskapi.exception.ProjectNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.repository.MemberRepository;
import com.nhnacademy.edu.minidooray.taskapi.repository.ProjectRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class MemberServiceImpTest {

    @MockBean
    private MemberRepository memberRepository;

    @MockBean
    private ProjectRepository projectRepository;

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("멤버 등록 성공")
    void testCreateMembersSuccess() {
        MemberRegisterRequest memberRegisterRequest = new MemberRegisterRequest("test", 1L, "member");
        given(projectRepository.findById(memberRegisterRequest.getProjectId())).willReturn(
                Optional.of(new Project()));
        given(memberRepository.findByMemberIdAndProjectId_ProjectId(memberRegisterRequest.getMemberId(),
                memberRegisterRequest.getProjectId())).willReturn(null);

        memberService.createMembers(memberRegisterRequest);

        verify(memberRepository, times(1)).save(any(Member.class));
    }

    @Test
    @DisplayName("프로젝트를 찾지 못해 멤버 등록 실패")
    void testCreateMembersFailByProjectNotFound() {
        MemberRegisterRequest memberRegisterRequest = new MemberRegisterRequest("test", 1L, "member");
        given(projectRepository.findById(memberRegisterRequest.getProjectId())).willReturn(
                Optional.empty());

        Assertions.assertThrows(ProjectNotFoundException.class,
                () -> memberService.createMembers(memberRegisterRequest));
    }

    @Test
    @DisplayName("멤버를 찾지 못해 등록 실패")
    void testCreateMembersFailByMemberNotFound() {
        MemberRegisterRequest memberRegisterRequest = new MemberRegisterRequest("test", 1L, "member");
        given(projectRepository.findById(memberRegisterRequest.getProjectId())).willReturn(
                Optional.of(new Project()));
        given(memberRepository.findByMemberIdAndProjectId_ProjectId(memberRegisterRequest.getMemberId(),
                memberRegisterRequest.getProjectId())).willReturn(mock(MemberResponse.class));

        Assertions.assertThrows(MemberAlreadyExistsException.class,
                () -> memberService.createMembers(memberRegisterRequest));
    }
}