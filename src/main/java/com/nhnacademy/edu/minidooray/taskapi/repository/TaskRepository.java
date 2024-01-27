package com.nhnacademy.edu.minidooray.taskapi.repository;

import com.nhnacademy.edu.minidooray.taskapi.domain.Project;
import com.nhnacademy.edu.minidooray.taskapi.domain.Task;
import com.nhnacademy.edu.minidooray.taskapi.dto.task.TaskResponse;
import com.nhnacademy.edu.minidooray.taskapi.dto.task.TasksResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task, Long> {
     @Query("select t from Task t where t.taskId = ?1")
     Optional<TaskResponse> findBy(Long taskId);

     List<TasksResponse> findByProject(Project project);
}
