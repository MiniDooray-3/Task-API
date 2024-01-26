package com.nhnacademy.edu.minidooray.taskapi.service.tasktag;

import com.nhnacademy.edu.minidooray.taskapi.domain.Task;
import com.nhnacademy.edu.minidooray.taskapi.domain.TaskTag;
import com.nhnacademy.edu.minidooray.taskapi.dto.tasktag.TaskTagResponse;
import com.nhnacademy.edu.minidooray.taskapi.exception.TaskNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.repository.TaskRepository;
import com.nhnacademy.edu.minidooray.taskapi.repository.TaskTagRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskTagServiceImp implements TaskTagService{

     private final TaskTagRepository taskTagRepository;
     private final TaskRepository taskRepository;

     @Override
     @Transactional(readOnly = true)
     public List<TaskTag> getTags(Long taskId) {
          taskRepository.findById(taskId)
                  .orElseThrow(() -> new TaskNotFoundException("Task Not Found Exception"));

          return taskTagRepository.findByTaskTagPk_TagId(taskId);
     }
}
