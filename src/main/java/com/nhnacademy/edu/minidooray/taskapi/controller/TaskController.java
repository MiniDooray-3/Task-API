package com.nhnacademy.edu.minidooray.taskapi.controller;

import com.nhnacademy.edu.minidooray.taskapi.dto.task.TaskRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
public class TaskController {

     private final TaskService taskService;

     // TODO : return value 결정.
     @GetMapping("/api/tasks/{task_id}")
     public void getTasks(@PathVariable("task_id") Long taskId){

     }


     // TODO : requestBody content, projectId
     @PostMapping("/api/task")
     @ResponseStatus(HttpStatus.CREATED)
     public void postTasks(@RequestBody TaskRegisterRequest registerRequest){

     }

     // TODO. requestBody : content, milestone, List{tag}
     @PutMapping("/api/tasks/{task_id}")
     public void putTasks(@PathVariable("task_id") Long taskId){

     }

     // TODO.
     @DeleteMapping("/api/tasks/{task_id}")
     public void deleteTasks(@PathVariable("task_id") Long taskId){

     }
}
