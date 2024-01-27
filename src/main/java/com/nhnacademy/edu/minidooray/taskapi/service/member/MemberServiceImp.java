package com.nhnacademy.edu.minidooray.taskapi.service.member;


import com.nhnacademy.edu.minidooray.taskapi.domain.Member;
import com.nhnacademy.edu.minidooray.taskapi.domain.Project;
import com.nhnacademy.edu.minidooray.taskapi.dto.member.MemberRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.member.MemberResponse;
import com.nhnacademy.edu.minidooray.taskapi.exception.MemberAlreadyExistsException;
import com.nhnacademy.edu.minidooray.taskapi.exception.MemberNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.exception.ProjectNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.repository.MemberRepository;
import com.nhnacademy.edu.minidooray.taskapi.repository.ProjectRepository;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImp implements MemberService {

     private final MemberRepository memberRepository;
     private final ProjectRepository projectRepository;

     private static final String PROJECT_NOT_FOUND =  "Project Not Found";
     private static final String MEMBER_NOT_FOUND =  "Member Not Found";
     private static final String MEMBER_ALREADY_EXISTS = "Member Already Exists";

     @Override
     @Transactional
     public void createMembers(MemberRegisterRequest registerRequest) {
          Project project = projectRepository.findById(registerRequest.getProjectId())
                  .orElseThrow(() -> new ProjectNotFoundException(PROJECT_NOT_FOUND));

          MemberResponse memberResponse = memberRepository.findByMemberIdAndProjectId_ProjectId(
                  registerRequest.getMemberId(), registerRequest.getProjectId());
          if (Objects.nonNull(memberResponse)) {
               throw new MemberAlreadyExistsException(MEMBER_ALREADY_EXISTS);
          }

          Member member = new Member();
          member.setMemberId(registerRequest.getMemberId());
          member.setMemberRole(registerRequest.getMemberRole());
          member.setProjectId(project);

          memberRepository.save(member);
     }

     @Override
     @Transactional(readOnly = true)
     public MemberResponse getMember(String memberId, Long projectId) {
          MemberResponse memberResponse = memberRepository.findByMemberIdAndProjectId_ProjectId(memberId, projectId);

          if (Objects.isNull(memberResponse)) { // member, project
               throw new MemberNotFoundException(MEMBER_NOT_FOUND);
          }
          return memberResponse;
     }

     @Override
     @Transactional(readOnly = true)
     public List<MemberResponse> getMembers(Long projectId) {
          Project project = projectRepository.findById(projectId)
                  .orElseThrow(() -> new ProjectNotFoundException(PROJECT_NOT_FOUND));
          return memberRepository.findByProjectId(project);
     }
}
