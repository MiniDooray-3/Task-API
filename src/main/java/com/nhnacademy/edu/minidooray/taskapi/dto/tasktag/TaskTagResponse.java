package com.nhnacademy.edu.minidooray.taskapi.dto.tasktag;

public interface TaskTagResponse {

     TagDto getTagId();

     interface TagDto {
          Long getTagId();

          String getTagName();
     }
}
