package com.nhnacademy.edu.minidooray.taskapi.dto.member;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberRegisterRequest {
    @NotBlank
    @Size(max = 20)
    private String memberId;

    private Long projectId;

    @NotBlank
    @Size(max = 6)
    private String memberRole;
}
