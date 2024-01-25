package com.nhnacademy.edu.minidooray.taskapi.repository;

import com.nhnacademy.edu.minidooray.taskapi.domain.Member;
import com.nhnacademy.edu.minidooray.taskapi.dto.member.MemberResponse;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
     MemberResponse findByMemberIdAndProjectId_ProjectId(String memberId, Long projectId);

     Optional<Member> findByMemberId(String memberId);
}
