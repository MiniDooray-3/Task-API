package com.nhnacademy.edu.minidooray.taskapi.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.nhnacademy.edu.minidooray.taskapi.domain.Task;
import com.nhnacademy.edu.minidooray.taskapi.domain.TaskTag;
import com.nhnacademy.edu.minidooray.taskapi.dto.task.TaskRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.tasktag.TagIdAndName;
import com.nhnacademy.edu.minidooray.taskapi.service.task.TaskService;
import com.nhnacademy.edu.minidooray.taskapi.service.tasktag.TaskTagService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(TaskTagController.class)
class TaskTagControllerTest {

     @Autowired
     private MockMvc mvc;

     @MockBean
     private TaskTagService taskTagService;

     @Test
     @DisplayName("성공 : 업무 태그 조회")
     void  getTaskTag() throws Exception {
          given(taskTagService.getTags(1L))
                  .willReturn(List.of(new TagIdAndName("tagName", 1L)));

          mvc.perform(MockMvcRequestBuilders.get("/api/task/tag/{task_id}", 1L))
                  .andExpect(status().isOk())
                  .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                  .andExpect(jsonPath("$[0].tagName", equalTo("tagName")))
                  .andExpect(jsonPath("$[0].tagId", equalTo(1)));
     }
}