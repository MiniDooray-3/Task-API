package com.nhnacademy.edu.minidooray.taskapi.dto.task;

import lombok.Data;

@Data
public class TaskRegisterRequest {
     private String content;
     private Long projectId;
}
