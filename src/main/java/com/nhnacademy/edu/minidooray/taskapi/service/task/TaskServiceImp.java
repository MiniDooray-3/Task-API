package com.nhnacademy.edu.minidooray.taskapi.service.task;

import com.nhnacademy.edu.minidooray.taskapi.domain.MileStone;
import com.nhnacademy.edu.minidooray.taskapi.domain.Project;
import com.nhnacademy.edu.minidooray.taskapi.domain.Task;
import com.nhnacademy.edu.minidooray.taskapi.domain.TaskTag;
import com.nhnacademy.edu.minidooray.taskapi.dto.task.TaskRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.task.TaskResponse;
import com.nhnacademy.edu.minidooray.taskapi.dto.task.TaskUpdateRequest;
import com.nhnacademy.edu.minidooray.taskapi.exception.MileStoneNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.exception.ProjectNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.exception.TaskNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.repository.MileStoneRepository;
import com.nhnacademy.edu.minidooray.taskapi.repository.ProjectRepository;
import com.nhnacademy.edu.minidooray.taskapi.repository.TagRepository;
import com.nhnacademy.edu.minidooray.taskapi.repository.TaskRepository;
import com.nhnacademy.edu.minidooray.taskapi.repository.TaskTagRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImp implements TaskService {

     private final TaskRepository taskRepository;
     private final ProjectRepository projectRepository;
     private final MileStoneRepository mileStoneRepository;
     private final TagRepository tagRepository;
     private final TaskTagRepository taskTagRepository;

     @Override
     @Transactional
     public void createTask(TaskRegisterRequest registerRequest) {
          Project storageProject = projectFindById(registerRequest.getProjectId());
          MileStone storageMileStone = mileStoneFindById(registerRequest.getMilestoneId());

          Task task = new Task(storageMileStone, storageProject, registerRequest.getTaskTitle(),
                  registerRequest.getTaskContent());
          taskRepository.save(task);

          List<TaskTag> taskTagList = registerRequest.getTagId().stream()
                  .map(tagId -> {
                       TaskTag taskTag = new TaskTag();
                       taskTag.setTaskTagPk(new TaskTag.TaskTagPk(task.getTaskId(), tagId));
                       taskTag.setTaskId(task);
                       taskTag.setTagId(tagRepository.findById(tagId).orElseThrow(
                               () -> new TaskNotFoundException("Tag Not Found Exception")
                       ));
                       return taskTag;
                  })
                  .collect(Collectors.toList());

          task.setTags(taskTagList);
          taskTagRepository.saveAll(taskTagList);
     }

     // TODO
     @Override
     @Transactional
     public void updateTask(Long taskId, TaskUpdateRequest updateRequest) {
          Task storageTask = taskRepository.findById(taskId)
                  .orElseThrow(() -> new TaskNotFoundException("Task Not Found Exception"));


     }

     @Override
     @Transactional(readOnly = true)
     public TaskResponse getTask(Long taskId) {
          return taskRepository.findByTaskId(taskId)
                  .orElseThrow(() -> new TaskNotFoundException("Task Not Found Exception"));
     }

     @Override
     public List<Task> getTastt(Long taskId) {
          return taskRepository.findAll();
     }

     @Override
     @Transactional
     public void deleteTask(Long taskId) {
          taskRepository.deleteById(taskId);
     }

     private Project projectFindById(Long projectId) {
          return projectRepository.findById(projectId)
                  .orElseThrow(() -> new ProjectNotFoundException("Project Not Found"));
     }

     private MileStone mileStoneFindById(Long mileStoneId) {
          return mileStoneRepository.findById(mileStoneId)
                  .orElseThrow(() -> new MileStoneNotFoundException("MileStone Not Found"));
     }

}
