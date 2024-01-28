package com.nhnacademy.edu.minidooray.taskapi.controller;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.minidooray.taskapi.dto.tag.TagRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.tag.TagResponse;
import com.nhnacademy.edu.minidooray.taskapi.dto.tag.TagUpdateRequest;
import com.nhnacademy.edu.minidooray.taskapi.service.tag.TagService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(TagController.class)
class TagControllerTest {

     @Autowired
     private MockMvc mvc;

     @MockBean
     private TagService tagService;

     private final ObjectMapper objectMapper = new ObjectMapper();

     @Test
     @DisplayName("성공 : 태그 전체 조회")
     void getTags() throws Exception {
          given(tagService.getTagList(anyLong()))
                  .willReturn(List.of(new TagResponse() {
                       @Override
                       public Long getTagId() {
                            return 1L;
                       }

                       @Override
                       public String getTagName() {
                            return "업무";
                       }
                  }));

          mvc.perform(MockMvcRequestBuilders.get("/api/tags/{project_id}", anyLong()))
                  .andExpect(status().isOk())
                  .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                  .andExpect(jsonPath("$[0].tagId",equalTo(1)))
                  .andExpect(jsonPath("$[0].tagName", equalTo("업무")));
     }

     @Test
     @DisplayName("성공 : 태그등록")
     void postTag() throws Exception{
          TagRegisterRequest registerRequest = new TagRegisterRequest();
          registerRequest.setTagName("exit");
          registerRequest.setProjectId(1L);

          mvc.perform(post("/api/tags")
                          .content(objectMapper.writeValueAsString(registerRequest))
                          .contentType(MediaType.APPLICATION_JSON))
                  .andExpect(status().isCreated());
     }

     @Test
     @DisplayName("실패(validation error) : 태그등록")
     void postTagFail() throws Exception{
          TagRegisterRequest registerRequest = new TagRegisterRequest();

          mvc.perform(post("/api/tags")
                          .content(objectMapper.writeValueAsString(registerRequest))
                          .contentType(MediaType.APPLICATION_JSON))
                  .andExpect(status().is4xxClientError());
     }

     @Test
     @DisplayName("성공 : 태그 수정")
     void putTag() throws Exception {
          TagRegisterRequest registerRequest = new TagRegisterRequest();
          registerRequest.setTagName("tagName");
          registerRequest.setProjectId(1L);
          tagService.createTag(registerRequest);

          TagUpdateRequest updateRequest = new TagUpdateRequest();
          updateRequest.setTagName("change");

          mvc.perform(put("/api/tags/{tag_id}", 1L)
                          .content(objectMapper.writeValueAsString(updateRequest))
                          .contentType(MediaType.APPLICATION_JSON))
                  .andExpect(status().isOk());
     }

     @Test
     @DisplayName("실패(validation error) : 태그 수정")
     void putTagFail() throws Exception {
          TagRegisterRequest registerRequest = new TagRegisterRequest();
          registerRequest.setTagName("tagName");
          registerRequest.setProjectId(1L);
          tagService.createTag(registerRequest);

          TagUpdateRequest updateRequest = new TagUpdateRequest();

          mvc.perform(put("/api/tags/{tag_id}", 1L)
                          .content(objectMapper.writeValueAsString(updateRequest))
                          .contentType(MediaType.APPLICATION_JSON))
                  .andExpect(status().is4xxClientError());
     }

     @Test
     @DisplayName("성공 : 태그 삭제")
     void deleteTag() throws Exception {

          mvc.perform(delete("/api/tags/{tag_id}", anyLong()))
                  .andExpect(status().isOk());

     }
}