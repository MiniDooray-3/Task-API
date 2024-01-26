package com.nhnacademy.edu.minidooray.taskapi.repository;

import com.nhnacademy.edu.minidooray.taskapi.domain.Task;
import com.nhnacademy.edu.minidooray.taskapi.domain.TaskTag;
import com.nhnacademy.edu.minidooray.taskapi.dto.tasktag.TaskTagResponse;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskTagRepository extends JpaRepository<TaskTag, TaskTag.TaskTagPk> {

     void deleteByTaskId_TaskId(Long taskId);

     @Query("select tt from TaskTag tt where tt.taskTagPk.tagId = ?1")
     List<TaskTagResponse> findBy(Long taskId);

     List<TaskTag> findByTaskTagPk_TagId(Long taskId);
}
