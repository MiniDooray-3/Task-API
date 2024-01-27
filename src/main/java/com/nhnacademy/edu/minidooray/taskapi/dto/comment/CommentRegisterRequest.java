package com.nhnacademy.edu.minidooray.taskapi.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentRegisterRequest {

    private String content;
    private Long taskId;
    private String memberId;
    private Long projectId;
}
