package com.nhnacademy.edu.minidooray.taskapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties("spring.edu.dooray.gateway")
public class UrlProperties {
     private String ip;
     private String port;

     public String getAddress(){
          return ip + ":" + port;
     }
}
