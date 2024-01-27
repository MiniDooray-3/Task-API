package com.nhnacademy.edu.minidooray.taskapi.repository;

import com.nhnacademy.edu.minidooray.taskapi.domain.Member;
import com.nhnacademy.edu.minidooray.taskapi.domain.Project;
import com.nhnacademy.edu.minidooray.taskapi.dto.member.MemberResponse;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {
     MemberResponse findByMemberIdAndProjectId_ProjectId(String memberId, Long projectId);
     Member findMemberByMemberIdAndProjectId_ProjectId(String memberId, Long projectId);
     List<MemberResponse> findByProjectId(Project project);

     @Query("select count(m.memberNumber) from Member m inner join Project p on m.projectId.projectId = p.projectId " +
             "left join Task t on t.project.projectId = p.projectId " +
             "where m.memberId = ?1 and t.taskId = ?2")
     Long countMemberByUserIdAndTaskId(String userId, Long taskId);

}
