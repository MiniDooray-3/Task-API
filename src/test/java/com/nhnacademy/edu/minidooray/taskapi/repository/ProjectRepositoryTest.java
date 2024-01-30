package com.nhnacademy.edu.minidooray.taskapi.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.nhnacademy.edu.minidooray.taskapi.domain.Member;
import com.nhnacademy.edu.minidooray.taskapi.domain.Project;
import com.nhnacademy.edu.minidooray.taskapi.dto.project.ProjectResponse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class ProjectRepositoryTest {

     @Autowired
     TestEntityManager entityManager;

     @Autowired
     ProjectRepository projectRepository;

     @Test
     void testGetProjectBy() {
          Project project = new Project(null, "testProject", "활성");
          Project merge = entityManager.merge(project);

          ProjectResponse projectResponse = projectRepository.getProjectBy(merge.getProjectId()).orElse(null);

          assertAll(
                  () -> assertEquals("testProject", projectResponse.getProjectName()),
                  () -> assertEquals("활성", projectResponse.getProjectStatus())
          );
     }


     @Test
     void testGetBy() {

          Project project = new Project(null, "testProject", "활성");
          Project merge = entityManager.merge(project);
          entityManager.merge(new Member(1L, merge, "test", "ADMIN"));
          List<Project> projects = projectRepository.getBy("test").orElse(null);

          assertAll(
                  () -> assertNotNull(projects),
                  () -> assertThat(projects).hasSize(1),
                  () -> assertEquals("testProject", projects.get(0).getProjectName())
          );
     }

}