package com.nhnacademy.edu.minidooray.taskapi.dto.project;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class ProjectRegisterRequest {
     @NotBlank
     @Size(max = 20)
     private String projectName;

     @NotBlank
     @Size(max = 20)
     private String memberId;
}
