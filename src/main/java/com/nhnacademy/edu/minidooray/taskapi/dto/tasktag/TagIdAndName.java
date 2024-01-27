package com.nhnacademy.edu.minidooray.taskapi.dto.tasktag;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagIdAndName {
     @NotBlank
     @Size(max = 10)
     private String tagName;
     private Long tagId;
}
