package com.nhnacademy.edu.minidooray.taskapi.service.tasktag;

import com.nhnacademy.edu.minidooray.taskapi.domain.TaskTag;
import com.nhnacademy.edu.minidooray.taskapi.dto.tag.TagResponse;
import com.nhnacademy.edu.minidooray.taskapi.dto.tasktag.TagIdAndName;
import com.nhnacademy.edu.minidooray.taskapi.exception.TaskNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.repository.TaskRepository;
import com.nhnacademy.edu.minidooray.taskapi.repository.TaskTagRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskTagServiceImp implements TaskTagService{

     private final TaskTagRepository taskTagRepository;
     private final TaskRepository taskRepository;

     @Override
     @Transactional(readOnly = true)
     public List<TagIdAndName> getTags(Long taskId) {
          taskRepository.findById(taskId)
                  .orElseThrow(() -> new TaskNotFoundException("Task Not Found Exception"));

          List<TagIdAndName> byTaskTagPkTaskId = taskTagRepository.findTagByTaskId(taskId);

          log.info("{}", byTaskTagPkTaskId);
          return byTaskTagPkTaskId;
     }
}
