package com.nhnacademy.edu.minidooray.taskapi.exception;

import org.springframework.validation.BindingResult;

public class ValidationFailedException extends RuntimeException{
     public ValidationFailedException(BindingResult bindingResult) {
          super(bindingResult.getObjectName());
     }
}
