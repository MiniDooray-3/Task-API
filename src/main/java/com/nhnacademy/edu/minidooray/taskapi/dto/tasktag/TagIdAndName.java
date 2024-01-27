package com.nhnacademy.edu.minidooray.taskapi.dto.tasktag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagIdAndName {
    private String tagName;
    private Long tagId;
}
