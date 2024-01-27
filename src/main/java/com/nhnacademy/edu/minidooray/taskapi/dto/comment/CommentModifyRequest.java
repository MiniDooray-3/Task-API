package com.nhnacademy.edu.minidooray.taskapi.dto.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CommentModifyRequest {
    private String commentContent;
}
