package com.nhnacademy.edu.minidooray.taskapi.controller;

import com.nhnacademy.edu.minidooray.taskapi.domain.Project;
import com.nhnacademy.edu.minidooray.taskapi.dto.project.ProjectRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.project.ProjectResponse;
import com.nhnacademy.edu.minidooray.taskapi.dto.project.ProjectUpdateRequest;
import com.nhnacademy.edu.minidooray.taskapi.service.project.ProjectService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
public class ProjectController {

     private final ProjectService projectService;

     @GetMapping("/api/projects/{project_id}")
     @ResponseStatus(HttpStatus.OK)
     public ResponseEntity<ProjectResponse> getProject(@PathVariable("project_id") Long projectId) {
          return ResponseEntity.ok(projectService.getProjectById(projectId));
     }

     @GetMapping("/api/projects/{member_id}/list")
     @ResponseStatus(HttpStatus.OK)
     public ResponseEntity<List<Project>> getMemberOfProject(@PathVariable("member_id") String id) {
          return ResponseEntity.ok(projectService.getProjectList(id));
     }

     @PostMapping("/api/projects")
     @ResponseStatus(HttpStatus.CREATED)
     public void createProject(@RequestBody ProjectRegisterRequest registerRequest) {
          projectService.createProject(registerRequest);
     }

     @PutMapping("/api/projects")
     @ResponseStatus(HttpStatus.OK)
     public void updateProject(@RequestBody ProjectUpdateRequest updateRequest) {
          projectService.updateProjectInfo(updateRequest);
     }
}
