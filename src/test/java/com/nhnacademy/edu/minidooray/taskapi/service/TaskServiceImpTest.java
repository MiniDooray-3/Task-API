package com.nhnacademy.edu.minidooray.taskapi.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.edu.minidooray.taskapi.domain.Comment;
import com.nhnacademy.edu.minidooray.taskapi.domain.MileStone;
import com.nhnacademy.edu.minidooray.taskapi.domain.Project;
import com.nhnacademy.edu.minidooray.taskapi.domain.Tag;
import com.nhnacademy.edu.minidooray.taskapi.domain.Task;
import com.nhnacademy.edu.minidooray.taskapi.domain.TaskTag;
import com.nhnacademy.edu.minidooray.taskapi.dto.task.TaskRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.task.TasksResponse;
import com.nhnacademy.edu.minidooray.taskapi.exception.ProjectNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.exception.TaskNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.repository.CommentRepository;
import com.nhnacademy.edu.minidooray.taskapi.repository.MileStoneRepository;
import com.nhnacademy.edu.minidooray.taskapi.repository.ProjectRepository;
import com.nhnacademy.edu.minidooray.taskapi.repository.TagRepository;
import com.nhnacademy.edu.minidooray.taskapi.repository.TaskRepository;
import com.nhnacademy.edu.minidooray.taskapi.repository.TaskTagRepository;
import com.nhnacademy.edu.minidooray.taskapi.service.task.TaskService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class TaskServiceImpTest {

     @Autowired
     private TaskService taskService;

     @MockBean
     private TaskRepository taskRepository;

     @MockBean
     private ProjectRepository projectRepository;

     @MockBean
     private MileStoneRepository mileStoneRepository;

     @MockBean
     private TagRepository tagRepository;

     @MockBean
     private TaskTagRepository taskTagRepository;

     @MockBean
     private CommentRepository commentRepository;


     @Test
     void createTask() {
          TaskRegisterRequest registerRequest = new TaskRegisterRequest();
          registerRequest.setProjectId(1L);
          registerRequest.setMilestoneId(1L);
          registerRequest.setTaskTitle("Task Title");
          registerRequest.setTaskContent("Task Content");
          registerRequest.setTagId(List.of(1L, 2L));

          Project storageProject = new Project();
          when(projectRepository.findById(1L)).thenReturn(Optional.of(storageProject));

          MileStone storageMileStone = new MileStone();
          when(mileStoneRepository.findById(1L)).thenReturn(Optional.of(storageMileStone));

          Tag tag1 = new Tag();
          when(tagRepository.findById(1L)).thenReturn(Optional.of(tag1));
          Tag tag2 = new Tag();
          when(tagRepository.findById(2L)).thenReturn(Optional.of(tag2));

          Task savedTask = new Task();
          savedTask.setTaskTitle("Task Title");
          savedTask.setTaskContent("Task Content");
          savedTask.setMileStone(storageMileStone);
          savedTask.setProject(storageProject);

          TaskTag taskTag1 = new TaskTag();
          TaskTag taskTag2 = new TaskTag();
          List<TaskTag> expectedTaskTagList = List.of(taskTag1, taskTag2);
          savedTask.setTags(expectedTaskTagList);

          when(taskRepository.save(any(Task.class))).thenReturn(savedTask);
          when(taskTagRepository.saveAll(anyList())).thenReturn(expectedTaskTagList);

          taskService.createTask(registerRequest);

          verify(projectRepository).findById(1L);
          verify(mileStoneRepository).findById(1L);
          verify(tagRepository).findById(1L);
          verify(tagRepository).findById(2L);
          verify(taskRepository).save(any(Task.class));
          verify(taskTagRepository).saveAll(anyList());

          assertThat(savedTask.getMileStone()).isEqualTo(storageMileStone);
          assertThat(savedTask.getProject()).isEqualTo(storageProject);
          assertThat(savedTask.getTaskTitle()).isEqualTo("Task Title");
          assertThat(savedTask.getTaskContent()).isEqualTo("Task Content");
          assertThat(savedTask.getTags()).isEqualTo(expectedTaskTagList);
     }

     @Test
     void getTask_ThrowTaskNotFoundException() {
          Long taskId = 1L;
          when(taskRepository.findBy(taskId)).thenReturn(Optional.empty());

          TaskNotFoundException exception = org.junit.jupiter.api.Assertions.assertThrows(
                  TaskNotFoundException.class,
                  () -> taskService.getTask(taskId)
          );
          assertThat(exception.getMessage()).isEqualTo("Task Not Found");
     }

     @Test
     void getTasks() {
          Long projectId = 1L;
          Project project = new Project();
          when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));

          List<TasksResponse> expectedTasks = new ArrayList<>();
          when(taskRepository.findByProject(project)).thenReturn(expectedTasks);

          List<TasksResponse> tasksResponse = taskService.getTasks(projectId);

          verify(projectRepository).findById(projectId);
          verify(taskRepository).findByProject(project);
          assertThat(tasksResponse).isEqualTo(expectedTasks);
     }

     @Test
     void getTasks_ThrowTaskNotFoundException() {
          Long projectId = 1L;
          when(projectRepository.findById(projectId)).thenReturn(Optional.empty());

          // When & Then
          ProjectNotFoundException exception = org.junit.jupiter.api.Assertions.assertThrows(
                  ProjectNotFoundException.class,
                  () -> taskService.getTasks(projectId)
          );
          assertThat(exception.getMessage()).isEqualTo("Project Not Found");
     }

     @Test
     void deleteTask() {
          Long taskId = 1L;
          Task storageTask = new Task();
          when(taskRepository.findById(taskId)).thenReturn(Optional.of(storageTask));

          List<Comment> comments = new ArrayList<>();
          when(commentRepository.findByTaskId(taskId)).thenReturn(comments);

          taskService.deleteTask(taskId);

          verify(commentRepository).findByTaskId(taskId);
          verify(commentRepository).deleteAll(comments);
          verify(taskRepository).delete(storageTask);
     }
}