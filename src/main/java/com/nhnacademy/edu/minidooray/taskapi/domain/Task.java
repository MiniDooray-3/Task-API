package com.nhnacademy.edu.minidooray.taskapi.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {

     @Id
     @Column(name = "task_id")
     @GeneratedValue(strategy= GenerationType.IDENTITY)
     private Long taskId;

     @ManyToOne
     @JoinColumn(name = "mile_stone_id")
     private MileStone mileStoneId;

     @ManyToOne
     @JoinColumn(name = "project_id")
     private Project projectId;

     @Column(name = "task_title")
     private String taskTitle;

     @Column(name = "task_content", columnDefinition = "TEXT")
     private String taskContent;
}
