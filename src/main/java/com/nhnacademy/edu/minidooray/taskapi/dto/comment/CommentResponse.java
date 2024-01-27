package com.nhnacademy.edu.minidooray.taskapi.dto.comment;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentResponse {
     private Long commentId;
     private String content;
     private String memberId;
}
