package com.nhnacademy.edu.minidooray.taskapi.dto.tag;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TagRegisterRequest {
     @NotBlank
     @Max(10)
     private String tagName;
     private Long projectId;

}
