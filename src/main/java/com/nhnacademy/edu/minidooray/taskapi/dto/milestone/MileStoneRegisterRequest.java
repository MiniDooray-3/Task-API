package com.nhnacademy.edu.minidooray.taskapi.dto.milestone;

import lombok.Data;

@Data
public class MileStoneRegisterRequest {
     private Long projectId;
     private String milestoneStatus;
}
