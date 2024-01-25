package com.nhnacademy.edu.minidooray.taskapi.service.task;

import com.nhnacademy.edu.minidooray.taskapi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImp implements TaskService{

     private final TaskRepository taskRepository;

}
