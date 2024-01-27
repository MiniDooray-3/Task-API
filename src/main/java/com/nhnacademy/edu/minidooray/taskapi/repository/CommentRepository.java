package com.nhnacademy.edu.minidooray.taskapi.repository;

import com.nhnacademy.edu.minidooray.taskapi.domain.Comment;
import com.nhnacademy.edu.minidooray.taskapi.dto.comment.CommentIdAndContent;
import com.nhnacademy.edu.minidooray.taskapi.dto.comment.CommentResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    @Query("select new com.nhnacademy.edu.minidooray.taskapi.dto.comment.CommentResponse(c.commentId, c.commentContent, m.memberId) " +
            "from Comment c inner join Member m on c.memberId = m " +
            "where c.taskId.taskId = ?1 order by c.commentId desc")
    List<CommentResponse> findAllByTaskId(Long taskId);

    Optional<CommentIdAndContent> findCommentByCommentId(Long commentId);

    @Query("select c from Comment c where c.taskId.taskId = ?1")
    List<Comment> findByTaskId(Long taskId);
}
