package com.nhnacademy.edu.minidooray.taskapi.repository;

import com.nhnacademy.edu.minidooray.taskapi.domain.TaskTag;
import com.nhnacademy.edu.minidooray.taskapi.dto.tasktag.TagIdAndName;
import com.nhnacademy.edu.minidooray.taskapi.dto.tasktag.TaskTagResponse;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskTagRepository extends JpaRepository<TaskTag, TaskTag.TaskTagPk> {

     void deleteByTaskId_TaskId(Long taskId);

     @Query("select tt from TaskTag tt where tt.taskTagPk.tagId = ?1")
     List<TaskTagResponse> findBy(Long taskId);

     @Query("select new com.nhnacademy.edu.minidooray.taskapi.dto.tasktag.TagIdAndName(t.tagName, t.tagId) " +
             "from TaskTag tt " +
             "inner join Tag t on tt.tagId = t " +
             "where tt.taskTagPk.taskId = ?1")
     List<TagIdAndName> findTagByTaskId(Long taskId);
}
