package com.nhnacademy.edu.minidooray.taskapi.service.comment;

import com.nhnacademy.edu.minidooray.taskapi.dto.comment.CommentModifyRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.comment.CommentIdAndContent;
import com.nhnacademy.edu.minidooray.taskapi.dto.comment.CommentRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.comment.CommentResponse;
import com.nhnacademy.edu.minidooray.taskapi.dto.task.TaskIdOnly;
import java.util.List;

public interface CommentService {

    void registerComment(CommentRegisterRequest commentRegisterRequest);

    Long modifyComment(Long commentId, CommentModifyRequest commentModifyRequest);

    Long deleteComment(Long commentId);

    List<CommentResponse> getComments(Long taskId);

    CommentIdAndContent getComment(Long commentId);
}
