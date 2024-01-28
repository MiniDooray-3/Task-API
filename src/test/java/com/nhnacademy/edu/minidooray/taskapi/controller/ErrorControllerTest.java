package com.nhnacademy.edu.minidooray.taskapi.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ErrorController.class)
class ErrorControllerTest {

     @Autowired
     private MockMvc mvc;



}