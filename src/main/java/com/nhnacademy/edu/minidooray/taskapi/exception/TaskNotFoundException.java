package com.nhnacademy.edu.minidooray.taskapi.exception;

public class TaskNotFoundException extends RuntimeException{
     public TaskNotFoundException(String message) {
          super(message);
     }
}
