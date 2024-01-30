package com.nhnacademy.edu.minidooray.taskapi.controller;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.minidooray.taskapi.dto.comment.CommentIdAndContent;
import com.nhnacademy.edu.minidooray.taskapi.dto.comment.CommentModifyRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.comment.CommentRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.comment.CommentResponse;
import com.nhnacademy.edu.minidooray.taskapi.service.comment.CommentService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest({CommentController.class})
class CommentControllerTest {

     @Autowired
     private MockMvc mvc;

     @MockBean
     private CommentService commentService;

     private final ObjectMapper objectMapper = new ObjectMapper();

     @Test
     @DisplayName("단일 댓글 조회 ")
     void getComment() throws Exception {

          given(commentService.getComment(1L))
                  .willReturn(new CommentIdAndContent() {
                       @Override
                       public Long getCommentId() {
                            return 1L;
                       }

                       @Override
                       public String getCommentContent() {
                            return "commentContent";
                       }
                  });

          mvc.perform(get("/api/comment/1"))
                  .andExpect(status().isOk())
                  .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                  .andExpect(jsonPath("$.commentId", equalTo(1)))
                  .andExpect(jsonPath("$.commentContent", equalTo("commentContent")));

     }

     @Test
     @DisplayName("성공 : 댓글 전체 목록")
     void getComments() throws Exception {
          given(commentService.getComments(1L))
                  .willReturn(List.of(new CommentResponse(1L, "content", "memberId")));

          mvc.perform(get("/api/comments/{task_id}", 1L))
                  .andExpect(status().isOk())
                  .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                  .andExpect(jsonPath("$[0].commentId", equalTo(1)))
                  .andExpect(jsonPath("$[0].content", equalTo("content")))
                  .andExpect(jsonPath("$[0].memberId", equalTo("memberId")));
     }

     @Test
     @DisplayName("성공 : 댓글 등록")
     void postComment() throws Exception{
          CommentRegisterRequest registerRequest = new CommentRegisterRequest();
          registerRequest.setMemberId("memberId");
          registerRequest.setContent("content");

          mvc.perform(post("/api/comments")
                  .content(objectMapper.writeValueAsString(registerRequest))
                  .contentType(MediaType.APPLICATION_JSON))
                  .andExpect(status().isCreated());
     }

     @Test
     @DisplayName("실패(validation error) : 댓글 등록")
     void postCommentFail() throws Exception{
          CommentRegisterRequest registerRequest = new CommentRegisterRequest();

          mvc.perform(post("/api/comments")
                          .content(objectMapper.writeValueAsString(registerRequest))
                          .contentType(MediaType.APPLICATION_JSON))
                  .andExpect(status().is4xxClientError());
     }

     @Test
     @DisplayName("성공 : 댓글 삭제")
     void deleteComment() throws Exception{
          mvc.perform(delete("/api/comments/{comment_id}", anyLong()))
                  .andExpect(status().isOk());
     }

     @Test
     @DisplayName("성공 : 댓글 수정")
     void putComment() throws Exception {
          CommentModifyRequest modifyRequest = new CommentModifyRequest();
          modifyRequest.setCommentContent("update Content");

          mvc.perform(put("/api/comments/{comment_id}", 1L)
                  .content(objectMapper.writeValueAsString(modifyRequest))
                  .contentType(MediaType.APPLICATION_JSON))
                  .andExpect(status().isOk());
     }

     @Test
     @DisplayName("실패(validation error) : 댓글 수정")
     void putCommentFail() throws Exception {
          CommentModifyRequest modifyRequest = new CommentModifyRequest();

          mvc.perform(put("/api/comments/{comment_id}", 1L)
                          .content(objectMapper.writeValueAsString(modifyRequest))
                          .contentType(MediaType.APPLICATION_JSON))
                  .andExpect(status().is4xxClientError());
     }


}