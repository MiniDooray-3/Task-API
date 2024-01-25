package com.nhnacademy.edu.minidooray.taskapi.service.milestone;

import com.nhnacademy.edu.minidooray.taskapi.dto.milestone.MileStoneRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.milestone.MileStoneResponse;
import com.nhnacademy.edu.minidooray.taskapi.dto.milestone.MileStoneUpdateRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.task.TaskRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.repository.MileStoneRepository;
import java.util.List;

public interface MileStoneService {

     void createMileStone(MileStoneRegisterRequest registerRequest);
     void updateMileStone(Long mileStoneId, MileStoneUpdateRequest updateRequest);
     void deleteMileStone(Long mileStoneId);
     List<MileStoneResponse> getMileStones(Long projectId);

}
