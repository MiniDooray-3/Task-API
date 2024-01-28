package com.nhnacademy.edu.minidooray.taskapi.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.nhnacademy.edu.minidooray.taskapi.dto.tasktag.TagIdAndName;
import com.nhnacademy.edu.minidooray.taskapi.exception.TaskNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.repository.TaskRepository;
import com.nhnacademy.edu.minidooray.taskapi.repository.TaskTagRepository;
import com.nhnacademy.edu.minidooray.taskapi.service.tasktag.TaskTagService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class TaskTagServiceImpTest {

     @Autowired
     private TaskTagService taskTagService;

     @MockBean
     private TaskTagRepository taskTagRepository;

     @MockBean
     private TaskRepository taskRepository;

     @Test
     @DisplayName("해당 업무 태그들 전체 조회 성공")
     void testGetTags_Success() {
          Long taskId = 1L;

          List<TagIdAndName> tags = List.of(
                  new TagIdAndName("tag1", 1L),
                  new TagIdAndName("tag2", 2L)
          );

          given(taskRepository.existsById(taskId)).willReturn(true);
          given(taskTagRepository.findTagByTaskId(taskId)).willReturn(tags);

          List<TagIdAndName> result = taskTagService.getTags(taskId);

          assertTrue(result.containsAll(tags));
          then(taskRepository).should().existsById(taskId);
          then(taskTagRepository).should().findTagByTaskId(taskId);
     }

     @Test
     @DisplayName("해당 업무 태그들 전체 조회 실패 : throw TaskNotFoundException")
     void testGetTags_TaskNotFoundException_Failure() {
          Long taskId = 1L;

          given(taskRepository.existsById(taskId)).willReturn(false);

          assertThrows(TaskNotFoundException.class, () -> taskTagService.getTags(taskId));
          then(taskRepository).should().existsById(taskId);
          then(taskTagRepository).shouldHaveNoInteractions();
     }
}