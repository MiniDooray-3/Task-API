package com.nhnacademy.edu.minidooray.taskapi.service.project;

import com.nhnacademy.edu.minidooray.taskapi.domain.Project;
import com.nhnacademy.edu.minidooray.taskapi.dto.project.ProjectRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.project.ProjectResponse;
import com.nhnacademy.edu.minidooray.taskapi.dto.project.ProjectUpdateRequest;
import java.util.List;

public interface ProjectService {
     ProjectResponse getProjectById(Long projectId);

     void updateProjectInfo(ProjectUpdateRequest updateRequest);

     List<Project> getProjectList(String memberId);

     void createProject(ProjectRegisterRequest projectSearchRequest);

}
