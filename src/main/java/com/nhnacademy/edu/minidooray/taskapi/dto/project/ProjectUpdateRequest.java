package com.nhnacademy.edu.minidooray.taskapi.dto.project;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class ProjectUpdateRequest {

     private Long projectId;

     @NotBlank
     @Size(max = 10)
     private String status;
}
