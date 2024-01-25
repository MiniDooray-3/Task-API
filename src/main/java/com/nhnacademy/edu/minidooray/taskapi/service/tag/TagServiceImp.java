package com.nhnacademy.edu.minidooray.taskapi.service.tag;

import com.nhnacademy.edu.minidooray.taskapi.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagServiceImp implements TagService{

     private final TagRepository tagRepository;

}
