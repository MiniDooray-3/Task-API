package com.nhnacademy.edu.minidooray.taskapi.dto.comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentRegisterRequest {
     @NotBlank
     private String content;

     private Long taskId;

     @NotBlank
     @Size(max = 20)
     private String memberId;

     private Long projectId;
}
