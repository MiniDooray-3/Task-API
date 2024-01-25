package com.nhnacademy.edu.minidooray.taskapi.repository;

import com.nhnacademy.edu.minidooray.taskapi.domain.Project;
import com.nhnacademy.edu.minidooray.taskapi.dto.project.ProjectResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectRepository extends JpaRepository<Project, Long> {

     List<ProjectResponse> findByProjectName(String memberId);

     @Query("select p from Project p where p.projectId = ?1 ")
     Optional<ProjectResponse> getProjectBy(Long projectId);

     @Query("SELECT p FROM Member m JOIN m.projectId p WHERE m.memberId = ?1")
     List<ProjectResponse> getProjectsBy(String memberId);

     @Query("SELECT p FROM Member m JOIN m.projectId p WHERE m.memberId = ?1")
     Optional<List<Project>> getBy(String memberId);
}
