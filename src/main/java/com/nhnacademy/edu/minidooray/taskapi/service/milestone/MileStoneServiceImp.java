package com.nhnacademy.edu.minidooray.taskapi.service.milestone;

import com.nhnacademy.edu.minidooray.taskapi.domain.MileStone;
import com.nhnacademy.edu.minidooray.taskapi.domain.Project;
import com.nhnacademy.edu.minidooray.taskapi.dto.milestone.MileStoneRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.milestone.MileStoneResponse;
import com.nhnacademy.edu.minidooray.taskapi.dto.milestone.MileStoneUpdateRequest;
import com.nhnacademy.edu.minidooray.taskapi.exception.MileStoneNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.exception.MileStoneStatusAlreadyExistsException;
import com.nhnacademy.edu.minidooray.taskapi.exception.ProjectNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.repository.MileStoneRepository;
import com.nhnacademy.edu.minidooray.taskapi.repository.ProjectRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MileStoneServiceImp implements MileStoneService {

     private final MileStoneRepository mileStoneRepository;
     private final ProjectRepository projectRepository;

     @Override
     public void createMileStone(MileStoneRegisterRequest registerRequest) {

          Project storageProject = projectNotFound(registerRequest.getProjectId());

          Optional<MileStone> optionalMileStone = mileStoneRepository
                  .findByMileStoneStatus(registerRequest.getMilestoneStatus());
          if (optionalMileStone.isPresent()) {
               throw new MileStoneStatusAlreadyExistsException();
          }

          MileStone mileStone = new MileStone();
          mileStone.setMileStoneStatus(registerRequest.getMilestoneStatus());
          mileStone.setProjectId(storageProject);
          mileStoneRepository.save(mileStone);
     }

     @Override
     public List<MileStoneResponse> getMileStones(Long projectId) {
          projectNotFound(projectId);

          return mileStoneRepository.findByProjectId_ProjectId(projectId);
     }

     @Override
     public void updateMileStone(Long mileStoneId, MileStoneUpdateRequest updateRequest) {
          MileStone storageMileStone = mileStoneRepository.findById(mileStoneId)
                  .orElseThrow(MileStoneNotFoundException::new);

          storageMileStone.setMileStoneStatus(updateRequest.getMileStoneStatus());
          mileStoneRepository.saveAndFlush(storageMileStone);
     }

     @Override
     @Transactional
     public void deleteMileStone(Long mileStoneId) {
          MileStone storageMileStone = mileStoneRepository.findById(mileStoneId)
                  .orElseThrow(MileStoneNotFoundException::new);

          mileStoneRepository.delete(storageMileStone);
     }

     private Project projectNotFound(Long projectId) {
          return projectRepository.findById(projectId)
                  .orElseThrow(() ->
                          new ProjectNotFoundException("location : milestoneCreate, Project Not Found Exception"));
     }

}
