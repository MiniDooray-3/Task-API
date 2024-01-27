package com.nhnacademy.edu.minidooray.taskapi.dto.task;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskRegisterRequest {
     @NotBlank @Size(max = 30)
     private String taskTitle;
     @NotBlank
     private String taskContent;
     private Long projectId;
     private Long milestoneId;
     private List<Long> tagId;
}
