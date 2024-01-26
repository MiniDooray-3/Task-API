package com.nhnacademy.edu.minidooray.taskapi.dto.member;

import lombok.Data;

@Data
public class MemberRegisterRequest {
     private String memberId;
     private Long projectId;
     private String memberRole;
}
