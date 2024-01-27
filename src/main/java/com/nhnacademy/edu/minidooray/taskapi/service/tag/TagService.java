package com.nhnacademy.edu.minidooray.taskapi.service.tag;

import com.nhnacademy.edu.minidooray.taskapi.dto.tag.TagRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.tag.TagResponse;
import com.nhnacademy.edu.minidooray.taskapi.dto.tag.TagUpdateRequest;
import java.util.List;

public interface TagService {

     void createTag(TagRegisterRequest registerRequest);
     void updateTag(Long tagId, TagUpdateRequest updateRequest);
     void deleteTag(Long tagId);
     List<TagResponse> getTagList(Long projectId);
}
