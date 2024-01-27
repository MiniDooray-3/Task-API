package com.nhnacademy.edu.minidooray.taskapi.dto.tag;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class TagUpdateRequest {
     @NotBlank
     @Size(max = 10)
      private String tagName;
}
