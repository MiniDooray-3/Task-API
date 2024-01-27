package com.nhnacademy.edu.minidooray.taskapi.dto.milestone;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class MileStoneUpdateRequest {
     @NotBlank
     @Size(max = 10)
     String mileStoneStatus;
}
