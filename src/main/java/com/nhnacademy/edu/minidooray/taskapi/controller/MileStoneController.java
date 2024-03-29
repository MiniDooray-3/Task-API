package com.nhnacademy.edu.minidooray.taskapi.controller;

import com.nhnacademy.edu.minidooray.taskapi.dto.milestone.MileStoneRegisterRequest;
import com.nhnacademy.edu.minidooray.taskapi.dto.milestone.MileStoneResponse;
import com.nhnacademy.edu.minidooray.taskapi.dto.milestone.MileStoneUpdateRequest;
import com.nhnacademy.edu.minidooray.taskapi.exception.ValidationFailedException;
import com.nhnacademy.edu.minidooray.taskapi.service.milestone.MileStoneService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
public class MileStoneController {

     private final MileStoneService mileStoneService;

     @GetMapping("/api/milestones/{project_id}")
     public ResponseEntity<List<MileStoneResponse>> getMileStones(@PathVariable("project_id") Long projectId) {
          return ResponseEntity.ok(mileStoneService.getMileStones(projectId));
     }

     @PostMapping("/api/milestones")
     @ResponseStatus(HttpStatus.CREATED)
     public void postMileStone(@Valid @RequestBody MileStoneRegisterRequest registerRequest,
                               BindingResult bindingResult) {
          if (bindingResult.hasErrors())
               throw new ValidationFailedException(bindingResult);

          mileStoneService.createMileStone(registerRequest);
     }

     @PutMapping("/api/milestones/{milestone_id}")
     @ResponseStatus(HttpStatus.OK)
     public void putMileStone(@PathVariable("milestone_id") Long mileStoneId,
                              @Valid @RequestBody MileStoneUpdateRequest updateRequest,
                              BindingResult bindingResult) {
          if (bindingResult.hasErrors())
               throw new ValidationFailedException(bindingResult);

          mileStoneService.updateMileStone(mileStoneId, updateRequest);
     }

     @DeleteMapping("/api/milestones/{milestone_id}")
     @ResponseStatus(HttpStatus.OK)
     public void deleteMileStone(@PathVariable("milestone_id") Long mileStoneId) {
          mileStoneService.deleteMileStone(mileStoneId);
     }

}
