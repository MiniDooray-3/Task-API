package com.nhnacademy.edu.minidooray.taskapi.service.task;

import com.nhnacademy.edu.minidooray.taskapi.domain.Task;
import com.nhnacademy.edu.minidooray.taskapi.dto.task.TaskRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.task.TaskResponse;
import com.nhnacademy.edu.minidooray.taskapi.dto.task.TaskUpdateRequest;
import java.util.List;

public interface TaskService {
     void createTask(TaskRegisterRequest registerRequest);
     void updateTask(Long taskId, TaskUpdateRequest updateRequest);
     void deleteTask(Long taskId);
     TaskResponse getTask(Long taskId);
     List<Task> getTastt(Long taskId);
//     List<>

}