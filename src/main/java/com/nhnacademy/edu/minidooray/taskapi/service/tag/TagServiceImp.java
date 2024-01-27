package com.nhnacademy.edu.minidooray.taskapi.service.tag;

import com.nhnacademy.edu.minidooray.taskapi.domain.Project;
import com.nhnacademy.edu.minidooray.taskapi.domain.Tag;
import com.nhnacademy.edu.minidooray.taskapi.dto.tag.TagRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.tag.TagResponse;
import com.nhnacademy.edu.minidooray.taskapi.dto.tag.TagUpdateRequest;
import com.nhnacademy.edu.minidooray.taskapi.exception.ProjectNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.exception.TagNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.repository.ProjectRepository;
import com.nhnacademy.edu.minidooray.taskapi.repository.TagRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TagServiceImp implements TagService {

     private final TagRepository tagRepository;
     private final ProjectRepository projectRepository;

     @Override
     public void createTag(TagRegisterRequest registerRequest) {
          Project project = projectFindById(registerRequest.getProjectId());

          Tag tag = new Tag();
          tag.setTagName(registerRequest.getTagName());
          tag.setProjectId(project);

          tagRepository.save(tag);
     }

     @Override
     @Transactional(readOnly = true)
     public List<TagResponse> getTagList(Long projectId) {
          projectFindById(projectId);
          return tagRepository.findByProjectId_ProjectId(projectId);
     }

     @Override
     @Transactional
     public void updateTag(Long tagId, TagUpdateRequest updateRequest) {
          Tag storageTag = tagFindById(tagId);

          storageTag.setTagName(updateRequest.getTagName());
          tagRepository.saveAndFlush(storageTag);
     }

     @Override
     @Transactional
     public void deleteTag(Long tagId) {
          Tag storageTag = tagFindById(tagId);

          tagRepository.delete(storageTag);
     }

     private Project projectFindById(Long projectId) {
          return projectRepository.findById(projectId)
                  .orElseThrow(
                          () -> new ProjectNotFoundException("Project Not Found"));
     }

     private Tag tagFindById(Long tagId) {
          return tagRepository.findById(tagId)
                  .orElseThrow(() -> new TagNotFoundException("Tag Not Found"));
     }
}
