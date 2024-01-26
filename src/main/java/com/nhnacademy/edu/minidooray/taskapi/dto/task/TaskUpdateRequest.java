package com.nhnacademy.edu.minidooray.taskapi.dto.task;

import com.nhnacademy.edu.minidooray.taskapi.domain.Tag;
import com.nhnacademy.edu.minidooray.taskapi.dto.tag.TagOnlyIdRequest;
import java.util.List;

public class TaskUpdateRequest {
     private String getTaskContent;
     private Long getMilestoneId;
     private List<TagOnlyIdRequest> tag;
}
