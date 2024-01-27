package com.nhnacademy.edu.minidooray.taskapi.repository;

import com.nhnacademy.edu.minidooray.taskapi.domain.Tag;
import com.nhnacademy.edu.minidooray.taskapi.dto.tag.TagResponse;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
     List<TagResponse> findByProjectId_ProjectId(Long projectId);
}
