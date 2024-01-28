package com.nhnacademy.edu.minidooray.taskapi.dto.comment;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentModifyRequest {
    @NotBlank
    private String commentContent;
}
