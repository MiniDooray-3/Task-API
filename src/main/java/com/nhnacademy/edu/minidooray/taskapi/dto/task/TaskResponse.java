package com.nhnacademy.edu.minidooray.taskapi.dto.task;

public interface TaskResponse {
     Long getTaskId();

     String getTaskTitle();

     String getTaskContent();

     ProjectDto getProject();

     MileStoneDto getMileStone();

     interface MileStoneDto {
          Long getMileStoneId();

          String getMileStoneStatus();
     }

     interface ProjectDto {
          Long getProjectId();

          String getProjectName();
     }
}
