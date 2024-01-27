package com.nhnacademy.edu.minidooray.taskapi.service.project;

import com.nhnacademy.edu.minidooray.taskapi.domain.Member;
import com.nhnacademy.edu.minidooray.taskapi.domain.Project;
import com.nhnacademy.edu.minidooray.taskapi.dto.project.ProjectRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.project.ProjectResponse;
import com.nhnacademy.edu.minidooray.taskapi.dto.project.ProjectUpdateRequest;
import com.nhnacademy.edu.minidooray.taskapi.exception.ProjectNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.repository.MemberRepository;
import com.nhnacademy.edu.minidooray.taskapi.repository.ProjectRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImp implements ProjectService {

     private final ProjectRepository projectRepository;
     private final MemberRepository memberRepository;

     private static final String PROJECT_NOT_FOUND = "Project Not Found";

     @Override
     @Transactional(readOnly = true)
     public ProjectResponse getProjectById(Long projectId) {
          return projectRepository.getProjectBy(projectId)
                  .orElseThrow(() -> new ProjectNotFoundException(PROJECT_NOT_FOUND));
     }

     @Override
     @Transactional
     public void updateProjectInfo(ProjectUpdateRequest updateRequest) {
          Project storageProject = projectRepository.findById(updateRequest.getProjectId()).orElseThrow(
                  () -> new ProjectNotFoundException(PROJECT_NOT_FOUND)
          );


          storageProject.setProjectStatus(updateRequest.getStatus());
          projectRepository.save(storageProject);
     }

     @Override
     @Transactional(readOnly = true)
     public List<Project> getProjectList(String memberId) {
          return projectRepository.getBy(memberId).orElse(new ArrayList<>());
     }

     @Override
     @Transactional
     public void createProject(ProjectRegisterRequest registerRequest) {

          Project project = new Project();
          project.setProjectName(registerRequest.getProjectName());
          project.setProjectStatus("활성");
          Member member = new Member();
          member.setProjectId(project);
          member.setMemberRole("ADMIN");
          member.setMemberId(registerRequest.getMemberId());

          memberRepository.save(member);
          projectRepository.save(project);
     }
}
