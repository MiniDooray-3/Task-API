package com.nhnacademy.edu.minidooray.taskapi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "projects")
public class Project {
     @Id
     @Column(name = "project_id")
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long projectId;

     @Column(name = "project_name")
     String projectName;

     @Column(name = "project_status")
     String projectStatus;

}
