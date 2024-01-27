package com.nhnacademy.edu.minidooray.taskapi.dto.member;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberRegisterRequest {
     @NotBlank
     @Size(max = 20)
     private String memberId;

     private Long projectId;

     @NotBlank
     @Size(max = 5)
     private String memberRole;
}
