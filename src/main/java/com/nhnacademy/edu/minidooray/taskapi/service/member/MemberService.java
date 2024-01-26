package com.nhnacademy.edu.minidooray.taskapi.service.member;

import com.nhnacademy.edu.minidooray.taskapi.dto.member.MemberRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.member.MemberResponse;
import java.util.List;

public interface MemberService {
     void createMembers(MemberRegisterRequest registerRequest);
     MemberResponse getMember(String memberId, Long projectId);
     List<MemberResponse> getMembers(Long projectId);
}
