package com.nhnacademy.edu.minidooray.taskapi.controller;

import com.nhnacademy.edu.minidooray.taskapi.dto.tasktag.TagIdAndName;
import com.nhnacademy.edu.minidooray.taskapi.service.tasktag.TaskTagService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class TaskTagController {

    private final TaskTagService taskTagService;

    @GetMapping("/api/task/tag/{task_id}")
    public ResponseEntity<List<TagIdAndName>> getTags(@PathVariable("task_id") Long taskId) {
        return ResponseEntity.ok(taskTagService.getTags(taskId));
    }


}
