package com.nhnacademy.edu.minidooray.taskapi.exception;

public class ProjectAlreadyExistsException extends RuntimeException{
     public ProjectAlreadyExistsException(String message) {
          super(message);
     }
}
