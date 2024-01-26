package com.nhnacademy.edu.minidooray.taskapi.service.tasktag;

import com.nhnacademy.edu.minidooray.taskapi.domain.TaskTag;
import com.nhnacademy.edu.minidooray.taskapi.dto.tasktag.TaskTagResponse;
import java.util.List;

public interface TaskTagService {
     List<TaskTag> getTags(Long taskId);
}
