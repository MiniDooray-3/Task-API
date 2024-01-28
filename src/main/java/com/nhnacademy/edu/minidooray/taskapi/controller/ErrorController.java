package com.nhnacademy.edu.minidooray.taskapi.controller;

import com.nhnacademy.edu.minidooray.taskapi.exception.CommentNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.exception.MemberAlreadyExistsException;
import com.nhnacademy.edu.minidooray.taskapi.exception.MemberNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.exception.MileStoneNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.exception.MileStoneStatusAlreadyExistsException;
import com.nhnacademy.edu.minidooray.taskapi.exception.ProjectAlreadyExistsException;
import com.nhnacademy.edu.minidooray.taskapi.exception.ProjectNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.exception.TagNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.exception.TaskNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.exception.ValidationFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorController {

     private static final String ERROR = "errorMessage";

     @ExceptionHandler(value = {
             ProjectAlreadyExistsException.class, MemberAlreadyExistsException.class,
             MileStoneStatusAlreadyExistsException.class
     })
     public ResponseEntity<RuntimeException> handleConflictException(RuntimeException e) {
          log.error("{}", e.getMessage());
          return ResponseEntity
                  .status(HttpStatus.CONFLICT)
                  .header(ERROR, e.getMessage())
                  .build();
     }

     @ExceptionHandler(value = {
             ProjectNotFoundException.class, MemberNotFoundException.class,
             MileStoneNotFoundException.class, TagNotFoundException.class,
             TaskNotFoundException.class, CommentNotFoundException.class
     })
     public ResponseEntity<RuntimeException> handleNotFoundException(RuntimeException e) {
          log.error("{}", e.getMessage());
          return ResponseEntity
                  .status(HttpStatus.NOT_FOUND)
                  .header(ERROR, e.getMessage())
                  .build();
     }

     @ExceptionHandler(value = {
             ValidationFailedException.class
     })
     public ResponseEntity<RuntimeException> handleValidationException(RuntimeException e) {
          log.error("validException : {}", e.getMessage());
          return ResponseEntity
                  .status(HttpStatus.BAD_REQUEST)
                  .header(ERROR, e.getMessage())
                  .build();
     }
}
