package com.nhnacademy.edu.minidooray.taskapi.service.project;

import com.nhnacademy.edu.minidooray.taskapi.domain.Project;
import com.nhnacademy.edu.minidooray.taskapi.dto.project.ProjectRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.project.ProjectUpdateRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.project.ProjectResponse;
import com.nhnacademy.edu.minidooray.taskapi.exception.ProjectNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.repository.MemberRepository;
import com.nhnacademy.edu.minidooray.taskapi.repository.ProjectRepository;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImp implements ProjectService{

     private final ProjectRepository projectRepository;
     private final MemberRepository memberRepository;

     @Override
     public ProjectResponse getProjectById(Long projectId) {
          return projectRepository.getProjectBy(projectId)
                  .orElseThrow(() -> new ProjectNotFoundException("not pound"));
     }

     @Override
     public void updateProjectInfo(ProjectUpdateRequest updateRequest) {
          Project storageProject = projectRepository.findById(updateRequest.getProjectId()).orElseThrow(
                  () -> new ProjectNotFoundException("Project Not Found Id : " + updateRequest.getProjectId())
          );


          storageProject.setProjectStatus(updateRequest.getStatus());
          projectRepository.saveAndFlush(storageProject);
     }

     // 생각.
     @Override
     public List<ProjectResponse> getProjects(String memberId) {
          return projectRepository.getProjectsBy(memberId);
     }


     @Override
     public List<Project> getProjectList(String memberId) {


          return projectRepository.getBy(memberId)
                  .orElseThrow(() -> new ProjectNotFoundException("Project Not Found"));
     }

     @Override
     public void createProject(ProjectRegisterRequest registerRequest) {

          Project project = new Project();
          project.setProjectName(registerRequest.getProjectName());
          project.setProjectStatus("활성");
          projectRepository.save(project);
     }
}
