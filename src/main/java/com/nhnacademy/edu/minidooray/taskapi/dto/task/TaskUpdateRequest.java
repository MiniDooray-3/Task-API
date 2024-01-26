package com.nhnacademy.edu.minidooray.taskapi.dto.task;

import com.nhnacademy.edu.minidooray.taskapi.domain.Tag;
import com.nhnacademy.edu.minidooray.taskapi.dto.tag.TagOnlyIdRequest;
import java.util.List;
import lombok.Data;

@Data
public class TaskUpdateRequest {
     private String taskContent;
     private Long milestoneId;
     private List<Long> tagId;
}
