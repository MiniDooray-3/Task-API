package com.nhnacademy.edu.minidooray.taskapi.dto.task;

import com.nhnacademy.edu.minidooray.taskapi.domain.MileStone;
import com.nhnacademy.edu.minidooray.taskapi.domain.Project;

public interface TaskResponse {
     String getTaskTitle();

     String getTaskContent();

     ProjectDto getProjectId();

     MileStoneDto getMileStoneId();

     interface MileStoneDto{
          Long getMileStoneId();
          String getMileStoneStatus();
     }

     interface ProjectDto{
          Long getProjectId();
          String getProjectName();
     }
}
