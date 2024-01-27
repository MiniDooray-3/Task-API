package com.nhnacademy.edu.minidooray.taskapi.dto.milestone;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class MileStoneRegisterRequest {
     private Long projectId;
     @NotBlank
     @Size(max = 10)
     private String milestoneStatus;
}
