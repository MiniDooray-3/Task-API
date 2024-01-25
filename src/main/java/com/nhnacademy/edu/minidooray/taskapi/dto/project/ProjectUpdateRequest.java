package com.nhnacademy.edu.minidooray.taskapi.dto.project;

import lombok.Data;

@Data
public class ProjectUpdateRequest {
     private Long projectId;
     private String status;
}
