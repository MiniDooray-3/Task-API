package com.nhnacademy.edu.minidooray.taskapi.dto.task;

import com.nhnacademy.edu.minidooray.taskapi.domain.MileStone;
import com.nhnacademy.edu.minidooray.taskapi.domain.Project;

public interface TaskResponse {
     String getTaskTitle();

     String getTaskContent();

     ProjectDto getProject();

     MileStoneDto getMileStone();

     interface MileStoneDto{
          Long getMileStoneId();
          String getMileStoneStatus();
     }

     interface ProjectDto{
          Long getProjectId();
          String getProjectName();
     }
}
