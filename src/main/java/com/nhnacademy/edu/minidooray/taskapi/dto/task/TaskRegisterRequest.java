package com.nhnacademy.edu.minidooray.taskapi.dto.task;

import java.util.List;
import lombok.Data;

@Data
public class TaskRegisterRequest {
     private String taskTitle;
     private String taskContent;
     private Long projectId;
     private Long milestoneId;
     private List<Long> tagId;
}
