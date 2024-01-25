package com.nhnacademy.edu.minidooray.taskapi.service.tasktag;

import com.nhnacademy.edu.minidooray.taskapi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskTagServiceImp implements TaskTagService{

     private final TaskRepository taskRepository;

}
