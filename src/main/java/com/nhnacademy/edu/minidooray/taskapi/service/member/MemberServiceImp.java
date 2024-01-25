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
import java.util.Objects;
import java.util.Optional;
import javax.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImp implements MemberService {

     private final MemberRepository memberRepository;
     private final ProjectRepository projectRepository;

     @Override
     @Transactional
     public void createMembers(MemberRegisterRequest registerRequest) {
          Project project = projectRepository.findById(registerRequest.getProjectId())
                  .orElseThrow(()->new ProjectNotFoundException("Project Not Found Id : " + registerRequest.getProjectId()));
          Optional<Member> storageMember = memberRepository.findByMemberId(registerRequest.getMemberId());
          if(storageMember.isPresent())
               throw new MemberAlreadyExistsException("Member Exists Exception");

          Member member = new Member();
          member.setMemberId(registerRequest.getMemberId());
          member.setMemberRole("관리자");
          member.setProjectId(project);

          memberRepository.save(member);
     }

     @Override
     @Transactional
     public MemberResponse getMember(String memberId, Long projectId) {
          MemberResponse memberResponse = memberRepository.findByMemberIdAndProjectId_ProjectId(memberId, projectId);
//          System.out.println(memberResponse.getMemberId());
//          System.out.println(memberResponse.getMemberRole());
          if(Objects.isNull(memberResponse))
               throw new MemberNotFoundException("Member Not Found Exception");
          return memberResponse;
     }


}
