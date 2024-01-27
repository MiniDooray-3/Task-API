package com.nhnacademy.edu.minidooray.taskapi.controller;

import com.nhnacademy.edu.minidooray.taskapi.dto.task.TaskRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.task.TaskResponse;
import com.nhnacademy.edu.minidooray.taskapi.dto.task.TaskUpdateRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.task.TasksResponse;
import com.nhnacademy.edu.minidooray.taskapi.service.task.TaskService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TaskController {

     private final TaskService taskService;

     @GetMapping("/api/tasks/{task_id}")
     public ResponseEntity<TaskResponse> getTasks(@PathVariable("task_id") Long taskId) {
          return ResponseEntity.ok(taskService.getTask(taskId));
     }

     @GetMapping("/api/projects/tasks/{project_id}")
     public ResponseEntity<List<TasksResponse>> getTask(@PathVariable("project_id") Long projectId) {
          return ResponseEntity.ok(taskService.getTasks(projectId));
     }


     @PostMapping("/api/tasks")
     @ResponseStatus(HttpStatus.CREATED)
     public void postTasks(@RequestBody TaskRegisterRequest registerRequest) {
          taskService.createTask(registerRequest);
     }

     @PutMapping("/api/tasks/{task_id}")
     @ResponseStatus(HttpStatus.OK)
     public void putTasks(@PathVariable("task_id") Long taskId,
                          @RequestBody TaskUpdateRequest updateRequest
     ) {
          taskService.updateTask(taskId, updateRequest);
     }

     @DeleteMapping("/api/tasks/{task_id}")
     @ResponseStatus(HttpStatus.OK)
     public void deleteTasks(@PathVariable("task_id") Long taskId) {
          taskService.deleteTask(taskId);
     }
}
