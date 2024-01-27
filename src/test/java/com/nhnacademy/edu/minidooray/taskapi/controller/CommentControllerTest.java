//package com.nhnacademy.edu.minidooray.taskapi.controller;
//
//import static org.hamcrest.Matchers.equalTo;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.ArgumentMatchers.matches;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import com.nhnacademy.edu.minidooray.taskapi.domain.Task;
//import com.nhnacademy.edu.minidooray.taskapi.dto.comment.CommentIdAndContent;
//import com.nhnacademy.edu.minidooray.taskapi.dto.comment.CommentRegisterRequest;
//import com.nhnacademy.edu.minidooray.taskapi.dto.comment.CommentResponse;
//import com.nhnacademy.edu.minidooray.taskapi.service.comment.CommentService;
//import java.util.List;
//import org.hamcrest.Matchers;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//@WebMvcTest({CommentController.class, ErrorController.class})
//class CommentControllerTest {
//
//     @Autowired
//     private MockMvc mvc;
//
//     @MockBean
//     private CommentService commentService;
//
//     @Test
//     @DisplayName("단일 댓글 조회 ")
//     void getComment() throws Exception {
//
//          given(commentService.getComment(1L))
//                  .willReturn(new CommentIdAndContent() {
//                       @Override
//                       public Long getCommentId() {
//                            return 1L;
//                       }
//
//                       @Override
//                       public String getCommentContent() {
//                            return "commentContent";
//                       }
//                  });
//
//          mvc.perform(get("/api/comment/1"))
//                  .andExpect(status().isOk())
//                  .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                  .andExpect(jsonPath("$.commentId", equalTo(1)))
//                  .andExpect(jsonPath("$.commentContent", equalTo("commentContent")));
//
//          mvc.perform(get("/api/comment/2"))
//                  .andExpect(status().isNotFound())
//                  .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//     }
//
//     @Test
//     void getComments() throws Exception{
//          given(commentService.getComments(1L))
//                  .willReturn(List.of(new CommentResponse(1L, "content", "memberId")));
//
//          mvc.perform(get("/api/comments/{task_id}", 1L))
//                  .andExpect(status().isOk())
//                  .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                  .andExpect(jsonPath("$[0].commentId", equalTo(1)))
//                  .andExpect(jsonPath("$[0].content", equalTo("content")))
//                  .andExpect(jsonPath("$[0].memberId", equalTo("memberId")));
//     }
//
//     @Test
//     void deleteComment() throws Exception {
//          given(commentService.deleteComment(1L))
//                  .willReturn(1L);
//
//          mvc.perform(delete("/api/comments/{comment_id}", 1L))
//                  .andExpect(status().isOk());
//
//     }
//
//}