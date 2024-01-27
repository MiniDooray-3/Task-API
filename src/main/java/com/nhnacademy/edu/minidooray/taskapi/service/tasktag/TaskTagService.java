package com.nhnacademy.edu.minidooray.taskapi.service.tasktag;

import com.nhnacademy.edu.minidooray.taskapi.dto.tasktag.TagIdAndName;
import java.util.List;

public interface TaskTagService {
     List<TagIdAndName> getTags(Long taskId);
}
