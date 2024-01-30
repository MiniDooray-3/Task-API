package com.nhnacademy.edu.minidooray.taskapi.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.nhnacademy.edu.minidooray.taskapi.domain.Comment;
import com.nhnacademy.edu.minidooray.taskapi.domain.Member;
import com.nhnacademy.edu.minidooray.taskapi.domain.Project;
import com.nhnacademy.edu.minidooray.taskapi.domain.Task;
import com.nhnacademy.edu.minidooray.taskapi.dto.comment.CommentIdAndContent;
import com.nhnacademy.edu.minidooray.taskapi.dto.comment.CommentResponse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CommentRepositoryTest {

     @Autowired
     TestEntityManager entityManager;

     @Autowired
     CommentRepository commentRepository;

     @Test
     void testFindAllByTaskId() {
          Project project = new Project(null, "test", "활성");
          Project mergeProject = entityManager.merge(project);

          Task task = new Task(null, null, mergeProject, "title", "content", null);
          Task mergeTask = entityManager.merge(task);

          Member member = new Member(null, mergeProject, "test", "ADMIN");
          Member mergeMember = entityManager.merge(member);

          Comment comment = new Comment(null, mergeTask, mergeMember, "content");
          Comment merge = entityManager.merge(comment);

          List<CommentResponse> result = commentRepository.findAllByTaskId(mergeTask.getTaskId());

          assertAll(
                  () -> assertThat(result).hasSize(1)
          );
     }


     @Test
     void testFindCommentByCommentId() {
          Project project = new Project(null, "test", "활성");
          Project mergeProject = entityManager.merge(project);

          Task task = new Task(null, null, mergeProject, "title", "content", null);
          Task mergeTask = entityManager.merge(task);

          Member member = new Member(null, mergeProject, "test", "ADMIN");
          Member mergeMember = entityManager.merge(member);

          Comment comment = new Comment(null, mergeTask, mergeMember, "content");
          Comment merge = entityManager.merge(comment);

          CommentIdAndContent result = commentRepository.findCommentByCommentId(merge.getCommentId()).orElse(null);

          assertAll(
                  () -> assertNotNull(result),
                  () -> assertEquals("content", result.getCommentContent())
          );
     }

     @Test
     void testFindByTaskId() {
          Project project = new Project(null, "test", "활성");
          Project mergeProject = entityManager.merge(project);

          Task task = new Task(null, null, mergeProject, "title", "content", null);
          Task mergeTask = entityManager.merge(task);

          Member member = new Member(null, mergeProject, "test", "ADMIN");
          Member mergeMember = entityManager.merge(member);

          Comment comment = new Comment(null, mergeTask, mergeMember, "content");
          entityManager.merge(comment);

          List<Comment> comments = commentRepository.findByTaskId(mergeTask.getTaskId());

          assertAll(
                  () -> assertThat(comments).hasSize(1),
                  () -> assertEquals("content", comments.get(0).getCommentContent())
          );

     }
}