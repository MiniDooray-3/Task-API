package com.nhnacademy.edu.minidooray.taskapi.dto.task;

import java.util.List;
import lombok.Data;

@Data
public class TaskUpdateRequest {
     private String taskContent;
     private Long mileStoneId;
     private List<Long> tagId;
}
