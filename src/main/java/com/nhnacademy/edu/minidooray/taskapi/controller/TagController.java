package com.nhnacademy.edu.minidooray.taskapi.controller;

import com.nhnacademy.edu.minidooray.taskapi.domain.Tag;
import com.nhnacademy.edu.minidooray.taskapi.dto.tag.TagRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.tag.TagResponse;
import com.nhnacademy.edu.minidooray.taskapi.dto.tag.TagUpdateRequest;
import com.nhnacademy.edu.minidooray.taskapi.service.tag.TagService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
public class TagController {

     private final TagService tagService;

     @GetMapping("/api/tags/{project_id}")
     public ResponseEntity<List<TagResponse>> getTags(@PathVariable("project_id") Long projectId){
          return ResponseEntity.ok(tagService.getTagList(projectId));
     }

     @PostMapping("/api/tags")
     @ResponseStatus(HttpStatus.CREATED)
     public void postTags(@RequestBody TagRegisterRequest registerRequest){
          tagService.createTag(registerRequest);
     }

     @PutMapping("/api/tags/{tag_id}")
     @ResponseStatus(HttpStatus.OK)
     public void putTags(@PathVariable("tag_id") Long tagId,
                         @RequestBody TagUpdateRequest updateRequest){
          tagService.updateTag(tagId, updateRequest);
     }

     @DeleteMapping("/api/tags/{tag_id}")
     @ResponseStatus(HttpStatus.OK)
     public void deleteTags(@PathVariable("tag_id") Long tagId){
          tagService.deleteTag(tagId);
     }
}
