package com.nhnacademy.edu.minidooray.taskapi.dto.task;

import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskUpdateRequest {
     @NotBlank
     private String taskContent;
     private Long mileStoneId;
     private List<Long> tagId;
}
