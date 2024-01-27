package com.nhnacademy.edu.minidooray.taskapi.dto.tag;

import lombok.Data;

@Data
public class TagRegisterRequest {

     private String tagName;
     private Long projectId;

}
