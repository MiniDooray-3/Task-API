package com.nhnacademy.edu.minidooray.taskapi.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.minidooray.taskapi.domain.Project;
import com.nhnacademy.edu.minidooray.taskapi.dto.project.ProjectRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.project.ProjectResponse;
import com.nhnacademy.edu.minidooray.taskapi.dto.project.ProjectUpdateRequest;
import com.nhnacademy.edu.minidooray.taskapi.service.project.ProjectService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProjectController.class)
class ProjectControllerTest {

     @Autowired
     private MockMvc mvc;

     @MockBean
     private ProjectService projectService;

     private final ObjectMapper objectMapper = new ObjectMapper();


     @Test
     @DisplayName("성공 : 프로젝트 개별조회")
     void getProject() throws Exception {
          given(projectService.getProjectById(anyLong()))
                  .willReturn(new ProjectResponse() {
                       @Override
                       public Long getProjectId() {
                            return 1L;
                       }

                       @Override
                       public String getProjectName() {
                            return "projectName";
                       }

                       @Override
                       public String getProjectStatus() {
                            return "status";
                       }
                  });

          mvc.perform(get("/api/projects/{project_id}", anyLong()))
                  .andExpect(status().isOk())
                  .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                  .andExpect(jsonPath("$.projectId", equalTo(1)))
                  .andExpect(jsonPath("$.projectName", equalTo("projectName")))
                  .andExpect(jsonPath("$.projectStatus", equalTo("status")));
     }

     @Test
     @DisplayName("업무 전체 조회")
     void getProjects() throws Exception {
          given(projectService.getProjectList("memberId"))
                  .willReturn(List.of(new Project(1L, "name", "status")
                          , new Project(1L, "name1", "status1")));

          mvc.perform(get("/api/projects/{member_id}/list", "memberId"))
                  .andExpect(status().isOk())
                  .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                  .andExpect(jsonPath("$[0].projectId", equalTo(1)))
                  .andExpect(jsonPath("$[0].projectName", equalTo("name")))
                  .andExpect(jsonPath("$[0].projectStatus", equalTo("status")));
     }

     @Test
     @DisplayName("성공 : 프로젝트 생성")
     void postProject() throws Exception {
          ProjectRegisterRequest registerRequest = new ProjectRegisterRequest();
          registerRequest.setProjectName("projectName");
          registerRequest.setMemberId("memberId");

          mvc.perform(post("/api/projects")
                          .content(objectMapper.writeValueAsString(registerRequest))
                          .contentType(MediaType.APPLICATION_JSON)
                  )
                  .andExpect(status().isCreated());
     }

     @Test
     @DisplayName("실패(validation error) : 프로젝트 생성")
     void postProjectFail() throws Exception {
          ProjectRegisterRequest registerRequest = new ProjectRegisterRequest();

          mvc.perform(post("/api/projects")
                          .content(objectMapper.writeValueAsString(registerRequest))
                          .contentType(MediaType.APPLICATION_JSON)
                  )
                  .andExpect(status().is4xxClientError());
     }

     @Test
     @DisplayName("성공 : 프로젝트 상태 수정")
     void putProject() throws Exception{
          ProjectUpdateRequest updateRequest =new ProjectUpdateRequest();
          updateRequest.setProjectId(1L);
          updateRequest.setStatus("활성");

          mvc.perform(put("/api/projects")
                  .content(objectMapper.writeValueAsString(updateRequest))
                  .contentType(MediaType.APPLICATION_JSON))
                  .andExpect(status().isOk());
     }

     @Test
     @DisplayName("성공 : 프로젝트 상태 수정")
     void putProjectFail() throws Exception{
          ProjectUpdateRequest updateRequest =new ProjectUpdateRequest();
          updateRequest.setProjectId(1L);

          mvc.perform(put("/api/projects")
                          .content(objectMapper.writeValueAsString(updateRequest))
                          .contentType(MediaType.APPLICATION_JSON))
                  .andExpect(status().is4xxClientError());
     }
//     @Test
//     @DisplayName("실패 : 프로젝트 개별 조회")
//     void getProjectFail() throws Exception {
//          given(projectService.getProjectById(anyLong()))
//                  .willReturn(new ProjectResponse() {
//                       @Override
//                       public Long getProjectId() {
//                            return null;
//                       }
//
//                       @Override
//                       public String getProjectName() {
//                            return "projectName";
//                       }
//
//                       @Override
//                       public String getProjectStatus() {
//                            return "status";
//                       }
//                  });
//
//          mvc.perform(get("/api/projects/{project_id}", anyLong()))
//                  .andExpect(status().isNotFound());
//
//     }
}