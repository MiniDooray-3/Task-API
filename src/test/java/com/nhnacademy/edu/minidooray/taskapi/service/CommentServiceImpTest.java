package com.nhnacademy.edu.minidooray.taskapi.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.nhnacademy.edu.minidooray.taskapi.domain.Comment;
import com.nhnacademy.edu.minidooray.taskapi.domain.Member;
import com.nhnacademy.edu.minidooray.taskapi.domain.MileStone;
import com.nhnacademy.edu.minidooray.taskapi.domain.Project;
import com.nhnacademy.edu.minidooray.taskapi.domain.Task;
import com.nhnacademy.edu.minidooray.taskapi.dto.comment.CommentIdAndContent;
import com.nhnacademy.edu.minidooray.taskapi.dto.comment.CommentModifyRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.comment.CommentRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.comment.CommentResponse;
import com.nhnacademy.edu.minidooray.taskapi.exception.CommentNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.exception.MemberNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.exception.TaskNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.repository.CommentRepository;
import com.nhnacademy.edu.minidooray.taskapi.repository.MemberRepository;
import com.nhnacademy.edu.minidooray.taskapi.repository.TaskRepository;
import com.nhnacademy.edu.minidooray.taskapi.service.comment.CommentService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class CommentServiceImpTest {

     @MockBean
     private CommentRepository commentRepository;

     @MockBean
     private MemberRepository memberRepository;

     @MockBean
     private TaskRepository taskRepository;

     @Autowired
     private CommentService commentService;


     @Test
     @DisplayName("comment 등록 성공")
     void testRegisterCommentSuccess() {
          CommentRegisterRequest commentRegisterRequest = new CommentRegisterRequest("test", 1L, "test", 1L);
          given(memberRepository.countMemberByUserIdAndTaskId(commentRegisterRequest.getMemberId(),
                  commentRegisterRequest.getProjectId())).willReturn(1L);
          given(taskRepository.findById(commentRegisterRequest.getTaskId())).willReturn(Optional.of(new Task()));

          commentService.registerComment(commentRegisterRequest);

          verify(commentRepository, times(1)).save(any(Comment.class));

     }

     @Test
     @DisplayName("Member가 아니라서 comment 등록 실패")
     void testRegisterCommentFailByMemberNotFound() {
          CommentRegisterRequest commentRegisterRequest = new CommentRegisterRequest("test", 1L, "test", 1L);
          given(memberRepository.countMemberByUserIdAndTaskId(commentRegisterRequest.getMemberId(),
                  commentRegisterRequest.getProjectId())).willReturn(0L);

          Assertions.assertThrows(MemberNotFoundException.class,
                  () -> commentService.registerComment(commentRegisterRequest));
     }

     @Test
     @DisplayName("Task를 찾지 못해 comment 등록 실패")
     void testRegisterCommentFailByTaskNotFound() {
          CommentRegisterRequest commentRegisterRequest = new CommentRegisterRequest("test", 1L, "test", 1L);
          given(memberRepository.countMemberByUserIdAndTaskId(commentRegisterRequest.getMemberId(),
                  commentRegisterRequest.getProjectId())).willReturn(1L);
          given(taskRepository.findById(commentRegisterRequest.getTaskId())).willReturn(Optional.empty());

          Assertions.assertThrows(TaskNotFoundException.class,
                  () -> commentService.registerComment(commentRegisterRequest));
     }

     @Test
     @DisplayName("comment 수정 성공")
     void testModifyCommentSuccess() {
          Long commentId = 1L;
          CommentModifyRequest commentModifyRequest = new CommentModifyRequest("test");
          Comment comment = new Comment(new Task(new MileStone(), new Project(), "", ""), new Member(), "");
          comment.getTaskId().setTaskId(1L);
          given(commentRepository.findById(commentId)).willReturn(Optional.of(comment));

          Assertions.assertEquals(commentService.modifyComment(commentId, commentModifyRequest), 1L);

     }

     @Test
     @DisplayName("Comment를 찾지 못해 수정 실패")
     void testModifyCommentFailByCommentNotFound() {
          Long commentId = 1L;
          CommentModifyRequest commentModifyRequest = new CommentModifyRequest("test");
          given(commentRepository.findById(commentId)).willReturn(Optional.empty());

          Assertions.assertThrows(CommentNotFoundException.class,
                  () -> commentService.modifyComment(commentId, commentModifyRequest));
     }


     @Test
     @DisplayName("comment 삭제 성공")
     void testDeleteCommentSuccess() {
          Long commentId = 1L;
          Comment comment = new Comment(new Task(new MileStone(), new Project(), "", ""), new Member(), "");
          comment.getTaskId().setTaskId(5L);
          given(commentRepository.findById(commentId)).willReturn(Optional.of(comment));

          Assertions.assertEquals(5L, commentService.deleteComment(commentId));
     }

     @Test
     @DisplayName("Comment 찾지 못해 삭제 실패")
     void testDeleteCommentFailByCommentNotFound() {
          Long commentId = 1L;
          given(commentRepository.findById(commentId)).willReturn(Optional.empty());

          Assertions.assertThrows(CommentNotFoundException.class, () -> commentService.deleteComment(commentId));
     }

     @Test
     @DisplayName("Comment 목록 조회")
     void testGetComments() {
          Long taskId = 5L;
          CommentResponse commentResponse = new CommentResponse(1L, "", "");

          given(commentRepository.findAllByTaskId(taskId)).willReturn(List.of(commentResponse));

          Assertions.assertEquals(List.of(commentResponse), commentService.getComments(taskId));
          verify(commentRepository, times(1)).findAllByTaskId(taskId);
     }

     @Test
     @DisplayName("comment 조회 성공")
     void testGetCommentSuccess() {
          Long commentId = 1L;
          CommentIdAndContent commentIdAndContent = mock(CommentIdAndContent.class);
          given(commentRepository.findCommentByCommentId(commentId)).willReturn(
                  Optional.of(commentIdAndContent));

          Assertions.assertInstanceOf(CommentIdAndContent.class, commentService.getComment(commentId));
     }

     @Test
     @DisplayName("comment 찾지 못해 조회 실패")
     void testGetCommentFailByCommentNotFound() {
          Long commentId = 1L;
          given(commentRepository.findCommentByCommentId(commentId)).willReturn(
                  Optional.empty());

          Assertions.assertThrows(CommentNotFoundException.class, () -> commentService.getComment(commentId));
     }

}