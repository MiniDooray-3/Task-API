package com.nhnacademy.edu.minidooray.taskapi.dto.task;

public interface TasksResponse {
     Long getTaskId();

     String getTaskTitle();

     MileStonesDto getMileStone();

     interface MileStonesDto {
          Long getMileStoneId();

          String getMileStoneStatus();
     }
}
