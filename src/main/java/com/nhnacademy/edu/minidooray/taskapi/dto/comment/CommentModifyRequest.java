package com.nhnacademy.edu.minidooray.taskapi.dto.comment;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CommentModifyRequest {
     @NotBlank
     private String commentContent;
}
