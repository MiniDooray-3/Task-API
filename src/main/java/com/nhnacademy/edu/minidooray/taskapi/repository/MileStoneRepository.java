package com.nhnacademy.edu.minidooray.taskapi.repository;

import com.nhnacademy.edu.minidooray.taskapi.domain.MileStone;
import com.nhnacademy.edu.minidooray.taskapi.dto.milestone.MileStoneResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MileStoneRepository extends JpaRepository<MileStone, Long> {
     List<MileStoneResponse> findByProjectId_ProjectId(Long projectId);
     Optional<MileStone> findByMileStoneStatus(String mileStoneStatus);
}
