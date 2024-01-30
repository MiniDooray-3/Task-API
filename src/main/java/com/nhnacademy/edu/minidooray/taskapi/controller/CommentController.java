package com.nhnacademy.edu.minidooray.taskapi.controller;

import com.nhnacademy.edu.minidooray.taskapi.dto.comment.CommentIdAndContent;
import com.nhnacademy.edu.minidooray.taskapi.dto.comment.CommentModifyRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.comment.CommentRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.comment.CommentResponse;
import com.nhnacademy.edu.minidooray.taskapi.exception.ValidationFailedException;
import com.nhnacademy.edu.minidooray.taskapi.service.comment.CommentService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
public class CommentController {

     private final CommentService commentService;

     @PostMapping("/api/comments")
     @ResponseStatus(HttpStatus.CREATED)
     public void registerComment(@Valid @RequestBody CommentRegisterRequest commentRegisterRequest,
                                 BindingResult bindingResult) {
          if (bindingResult.hasErrors()) {
               throw new ValidationFailedException(bindingResult);
          }

          commentService.registerComment(commentRegisterRequest);
     }

     @PutMapping("/api/comments/{comment_id}")
     @ResponseStatus(HttpStatus.OK)
     public ResponseEntity<Long> modifyComment(@PathVariable("comment_id") Long commentId,
                                               @Valid @RequestBody CommentModifyRequest commentModifyRequest,
                                               BindingResult bindingResult) {
          if (bindingResult.hasErrors())
               throw new ValidationFailedException(bindingResult);

          Long taskId = commentService.modifyComment(commentId, commentModifyRequest);

          return ResponseEntity.ok(taskId);
     }

     @DeleteMapping("/api/comments/{comment_id}")
     @ResponseStatus(HttpStatus.OK)
     public ResponseEntity<Long> deleteComment(@PathVariable("comment_id") Long commentId) {
          Long taskId = commentService.deleteComment(commentId);

          return ResponseEntity.ok(taskId);
     }

     @GetMapping("/api/comments/{task_id}")
     public ResponseEntity<List<CommentResponse>> getComments(@PathVariable("task_id") Long taskId) {
          return ResponseEntity.ok(commentService.getComments(taskId));
     }

     @GetMapping("/api/comment/{comment_id}")
     public ResponseEntity<CommentIdAndContent> getComment(@PathVariable("comment_id") Long commentId) {
          CommentIdAndContent comment = commentService.getComment(commentId);
          return ResponseEntity.ok(comment);
     }
}
