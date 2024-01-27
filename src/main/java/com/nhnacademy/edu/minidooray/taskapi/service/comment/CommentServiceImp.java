package com.nhnacademy.edu.minidooray.taskapi.service.comment;

import com.nhnacademy.edu.minidooray.taskapi.domain.Comment;
import com.nhnacademy.edu.minidooray.taskapi.domain.Member;
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
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImp implements CommentService {

     private final CommentRepository commentRepository;
     private final MemberRepository memberRepository;
     private final TaskRepository taskRepository;

     @Override
     @Transactional
     public void registerComment(CommentRegisterRequest commentRegisterRequest) {
          Long l = memberRepository.countMemberByUserIdAndTaskId(commentRegisterRequest.getMemberId(),
                  commentRegisterRequest.getTaskId());
          if (l < 1) {
               throw new MemberNotFoundException("Member not found");
          }

          Member member =
                  memberRepository.findMemberByMemberIdAndProjectId_ProjectId(commentRegisterRequest.getMemberId(),
                          commentRegisterRequest.getProjectId());
          Task task = taskRepository.findById(commentRegisterRequest.getTaskId()).orElseThrow(
                  () -> new TaskNotFoundException("Task Not Found")
          );

          Comment comment = new Comment(task, member, commentRegisterRequest.getContent());

          commentRepository.save(comment);
     }

     @Override
     public Long modifyComment(Long commentId, CommentModifyRequest commentModifyRequest) {
          Comment comment = commentRepository.findById(commentId).orElseThrow(
                  () -> new CommentNotFoundException("Comment Not Found")
          );

          comment.modifyContent(commentModifyRequest.getCommentContent());

          commentRepository.save(comment);

          return comment.getTaskId().getTaskId();
     }

     @Override
     public Long deleteComment(Long commentId) {
          Comment comment = commentRepository.findById(commentId).orElseThrow(
                  () -> new CommentNotFoundException("Comment Not Found")
          );

          Long taskId = comment.getTaskId().getTaskId();

          commentRepository.deleteById(commentId);

          return taskId;
     }

     @Override
     public List<CommentResponse> getComments(Long taskId) {
          return commentRepository.findAllByTaskId(taskId);
     }

     @Override
     public CommentIdAndContent getComment(Long commentId) {
          return commentRepository.findCommentByCommentId(commentId).orElseThrow(
                  () -> new CommentNotFoundException("Comment not found")
          );
     }

}
