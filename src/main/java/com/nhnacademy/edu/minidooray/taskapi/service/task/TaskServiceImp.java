package com.nhnacademy.edu.minidooray.taskapi.service.task;

import com.nhnacademy.edu.minidooray.taskapi.domain.MileStone;
import com.nhnacademy.edu.minidooray.taskapi.domain.Project;
import com.nhnacademy.edu.minidooray.taskapi.domain.Task;
import com.nhnacademy.edu.minidooray.taskapi.domain.TaskTag;
import com.nhnacademy.edu.minidooray.taskapi.dto.task.TaskRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.task.TaskResponse;
import com.nhnacademy.edu.minidooray.taskapi.dto.task.TaskUpdateRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.task.TasksResponse;
import com.nhnacademy.edu.minidooray.taskapi.exception.MileStoneNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.exception.ProjectNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.exception.TaskNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.repository.MileStoneRepository;
import com.nhnacademy.edu.minidooray.taskapi.repository.ProjectRepository;
import com.nhnacademy.edu.minidooray.taskapi.repository.TagRepository;
import com.nhnacademy.edu.minidooray.taskapi.repository.TaskRepository;
import com.nhnacademy.edu.minidooray.taskapi.repository.TaskTagRepository;
import java.util.List;
import java.util.Objects;
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
          MileStone storageMileStone = null;

          if (Objects.nonNull(registerRequest.getMilestoneId())) {
               storageMileStone = mileStoneFindById(registerRequest.getMilestoneId());
          }

          Task task = new Task(storageMileStone, storageProject,
                  registerRequest.getTaskTitle(), registerRequest.getTaskContent());
          taskRepository.save(task);

          List<TaskTag> taskTagList = null;
          if (Objects.nonNull(registerRequest.getTagId())) {
               taskTagList = tagSetting(registerRequest.getTagId(), task);
          }


          if (Objects.nonNull(taskTagList)) {
               taskTagRepository.saveAll(taskTagList);
          }
     }

     @Override
     @Transactional
     public void updateTask(Long taskId, TaskUpdateRequest updateRequest) {
          Task storageTask = taskFindById(taskId);
          if (storageTask.getTags() != null) {
               taskTagRepository.deleteByTaskId_TaskId(taskId);
          }

          MileStone storageMileStone = null;
          if (Objects.nonNull(updateRequest.getMileStoneId())) {
               storageMileStone = mileStoneFindById(updateRequest.getMileStoneId());
          }

          List<TaskTag> taskTagList = null;
          if (Objects.nonNull(updateRequest.getTagId())) {
               taskTagList = tagSetting(updateRequest.getTagId(), storageTask);
          }

          storageTask.updateTask(updateRequest.getTaskContent(), storageMileStone, taskTagList);

          if (Objects.nonNull(taskTagList)) {
               taskTagRepository.saveAll(taskTagList);
          }
     }

     @Override
     @Transactional(readOnly = true)
     public TaskResponse getTask(Long taskId) {
          return taskRepository.findBy(taskId)
                  .orElseThrow(() -> new TaskNotFoundException("Task Not Found"));
     }

     @Override
     public List<TasksResponse> getTasks(Long projectId) {
          return taskRepository.findByProject(projectFindById(projectId));
     }

     @Override
     @Transactional
     public void deleteTask(Long taskId) {
          Task storageTask = taskFindById(taskId);
          taskRepository.delete(storageTask);
     }

     private Project projectFindById(Long projectId) {
          return projectRepository.findById(projectId)
                  .orElseThrow(() -> new ProjectNotFoundException("Project Not Found"));
     }

     private MileStone mileStoneFindById(Long mileStoneId) {
          return mileStoneRepository.findById(mileStoneId)
                  .orElseThrow(() -> new MileStoneNotFoundException("MileStone Not Found"));
     }

     private Task taskFindById(Long taskId) {
          return taskRepository.findById(taskId)
                  .orElseThrow(() -> new TaskNotFoundException("Task Not Found"));
     }

     private List<TaskTag> tagSetting(List<Long> tagsList, Task task) {
          return tagsList.stream()
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

     }

}
