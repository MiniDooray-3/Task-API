package com.nhnacademy.edu.minidooray.taskapi.service.comment;

import com.nhnacademy.edu.minidooray.taskapi.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImp implements CommentService{

     private final CommentRepository commentRepository;
}
