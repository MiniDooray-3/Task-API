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
import com.nhnacademy.edu.minidooray.taskapi.domain.MileStone;
import com.nhnacademy.edu.minidooray.taskapi.dto.milestone.MileStoneRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.milestone.MileStoneResponse;
import com.nhnacademy.edu.minidooray.taskapi.dto.milestone.MileStoneUpdateRequest;
import com.nhnacademy.edu.minidooray.taskapi.service.milestone.MileStoneService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(MileStoneController.class)
class MileStoneControllerTest {

     @Autowired
     private MockMvc mvc;

     @MockBean
     private MileStoneService mileStoneService;

     private final ObjectMapper objectMapper = new ObjectMapper();

     @Test
     @DisplayName("성공 : 마일스톤 조회")
     void getMileStone() throws Exception{
          given(mileStoneService.getMileStones(anyLong()))
                  .willReturn(List.of(new MileStoneResponse() {
                       @Override
                       public Long getMileStoneId() {
                            return 1L;
                       }

                       @Override
                       public String getMileStoneStatus() {
                            return "status";
                       }
                  }));

          mvc.perform(MockMvcRequestBuilders.get("/api/milestones/{project_id}", anyLong()))
                  .andExpect(status().isOk())
                  .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                  .andExpect(jsonPath("$[0].mileStoneId", equalTo(1)))
                  .andExpect(jsonPath("$[0].mileStoneStatus", equalTo("status")));
     }

     @Test
     @DisplayName("성공 : 마일스톤 수정")
     void putMileStone() throws Exception{
          MileStoneUpdateRequest updateRequest = new MileStoneUpdateRequest();
          updateRequest.setMileStoneStatus("종료");

          mvc.perform(put("/api/milestones/{milestone_id}", 1L)
                  .content(objectMapper.writeValueAsString(updateRequest))
                  .contentType(MediaType.APPLICATION_JSON))
                  .andExpect(status().isOk());
     }

     @Test
     @DisplayName("실패(validation error) : 마일스톤 수정")
     void putMileStoneFail() throws Exception{
          MileStoneUpdateRequest updateRequest = new MileStoneUpdateRequest();

          mvc.perform(put("/api/milestones/{milestone_id}", 1L)
                          .content(objectMapper.writeValueAsString(updateRequest))
                          .contentType(MediaType.APPLICATION_JSON))
                  .andExpect(status().is4xxClientError());
     }

     @Test
     @DisplayName("성공 : 마일스톤 등록")
     void postMileStone() throws Exception{
          MileStoneRegisterRequest registerRequest = new MileStoneRegisterRequest();
          registerRequest.setProjectId(1L);
          registerRequest.setMilestoneStatus("시작");

          mvc.perform(post("/api/milestones")
                  .content(objectMapper.writeValueAsString(registerRequest))
                  .contentType(MediaType.APPLICATION_JSON))
                  .andExpect(status().isCreated());
     }

     @Test
     @DisplayName("실패(validation fail) : 마일스톤 등록")
     void postMileStoneFail() throws Exception{
          MileStoneRegisterRequest registerRequest = new MileStoneRegisterRequest();

          mvc.perform(post("/api/milestones")
                          .content(objectMapper.writeValueAsString(registerRequest))
                          .contentType(MediaType.APPLICATION_JSON))
                  .andExpect(status().is4xxClientError());
     }

     @Test
     @DisplayName("성공 : 마일스톤 삭제")
     void deleteMileStone() throws Exception{
          mvc.perform(delete("/api/milestones/{milestone_id}", anyLong()))
                  .andExpect(status().isOk());
     }
}