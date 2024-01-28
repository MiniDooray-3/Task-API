package com.nhnacademy.edu.minidooray.taskapi.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.minidooray.taskapi.dto.task.TaskRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.task.TaskResponse;
import com.nhnacademy.edu.minidooray.taskapi.dto.task.TaskUpdateRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.task.TasksResponse;
import com.nhnacademy.edu.minidooray.taskapi.service.task.TaskService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

     @Autowired
     private MockMvc mvc;

     @MockBean
     private TaskService taskService;

     private ObjectMapper objectMapper = new ObjectMapper();

     @Test
     @DisplayName("성공 : 업무 단일 조회")
     void getTask() throws Exception {
          given(taskService.getTask(anyLong()))
                  .willReturn(new TaskResponse() {
                       @Override
                       public Long getTaskId() {
                            return 1L;
                       }

                       @Override
                       public String getTaskTitle() {
                            return "title";
                       }

                       @Override
                       public String getTaskContent() {
                            return "content";
                       }

                       @Override
                       public ProjectDto getProject() {
                            return null;
                       }

                       @Override
                       public MileStoneDto getMileStone() {
                            return null;
                       }
                  });

          mvc.perform(MockMvcRequestBuilders.get("/api/tasks/{task_id}", anyLong()))
                  .andExpect(status().isOk())
                  .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                  .andExpect(jsonPath("$.taskTitle", equalTo("title")));
     }

     @Test
     @DisplayName("성공 : 업무 프로젝트 기준 전체 조회")
     void getTasks() throws Exception {
          given(taskService.getTasks(anyLong()))
                  .willReturn(List.of(new TasksResponse() {
                       @Override
                       public Long getTaskId() {
                            return null;
                       }

                       @Override
                       public String getTaskTitle() {
                            return "title";
                       }

                       @Override
                       public MileStonesDto getMileStone() {
                            return null;
                       }
                  }));

          mvc.perform(MockMvcRequestBuilders.get("/api/projects/tasks/{project_id}", anyLong()))
                  .andExpect(status().isOk())
                  .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                  .andExpect(jsonPath("$[0].taskTitle", equalTo("title")));
     }

     @Test
     @DisplayName("성공 : 업무 등록")
     void postTask() throws Exception {
          TaskRegisterRequest registerRequest = new TaskRegisterRequest();
          registerRequest.setTaskTitle("title");
          registerRequest.setTaskContent("content");

          mvc.perform(post("/api/tasks")
                          .content(objectMapper.writeValueAsString(registerRequest))
                          .contentType(MediaType.APPLICATION_JSON))
                  .andExpect(status().isCreated());
     }

     @Test
     @DisplayName("실패(validation error) : 업무 등록 실패")
     void postTaskFail() throws Exception{
          TaskRegisterRequest registerRequest = new TaskRegisterRequest();

          mvc.perform(post("/api/tasks")
                          .content(objectMapper.writeValueAsString(registerRequest))
                          .contentType(MediaType.APPLICATION_JSON))
                  .andExpect(status().is4xxClientError());

     }

     @Test
     @DisplayName("성공 : 업무 수정")
     void putTask() throws Exception{
          TaskUpdateRequest updateRequest = new TaskUpdateRequest();
          updateRequest.setTaskContent("content");

          mvc.perform(put("/api/tasks/{task_id}", 1L)
                          .content(objectMapper.writeValueAsString(updateRequest))
                          .contentType(MediaType.APPLICATION_JSON))
                  .andExpect(status().isOk());
     }

     @Test
     @DisplayName("실패(validation error) : 업무 수정")
     void putTaskFail() throws Exception{
          TaskUpdateRequest updateRequest = new TaskUpdateRequest();

          mvc.perform(put("/api/tasks/{task_id}", 1L)
                          .content(objectMapper.writeValueAsString(updateRequest))
                          .contentType(MediaType.APPLICATION_JSON))
                  .andExpect(status().is4xxClientError());
     }

     @Test
     @DisplayName("성공 : 업무 삭제")
     void deleteTask() throws Exception{
          mvc.perform(delete("/api/tasks/{task_id}", anyLong()))
                  .andExpect(status().isOk());
     }


}