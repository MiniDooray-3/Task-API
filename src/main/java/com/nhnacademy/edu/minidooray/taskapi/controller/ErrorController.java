package com.nhnacademy.edu.minidooray.taskapi.controller;

import com.nhnacademy.edu.minidooray.taskapi.exception.MemberAlreadyExistsException;
import com.nhnacademy.edu.minidooray.taskapi.exception.MemberNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.exception.MileStoneNotFoundException;
import com.nhnacademy.edu.minidooray.taskapi.exception.MileStoneStatusAlreadyExistsException;
import com.nhnacademy.edu.minidooray.taskapi.exception.ProjectAlreadyExistsException;
import com.nhnacademy.edu.minidooray.taskapi.exception.ProjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {
     @ExceptionHandler(value = {
             ProjectAlreadyExistsException.class, MemberAlreadyExistsException.class,
             MileStoneStatusAlreadyExistsException.class
     })
     @ResponseStatus(HttpStatus.CONFLICT)
     public void handleConflictException() {
     }

     @ExceptionHandler(value = {
             ProjectNotFoundException.class, MemberNotFoundException.class,
             MileStoneNotFoundException.class
     })
     @ResponseStatus(HttpStatus.NOT_FOUND)
     public void handleNotFoundException() {
     }
}
