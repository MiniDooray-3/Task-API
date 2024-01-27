package com.nhnacademy.edu.minidooray.taskapi.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {

     @Id
     @Column(name = "task_id")
     @GeneratedValue(strategy= GenerationType.IDENTITY)
     private Long taskId;

     @ManyToOne
     @JoinColumn(name = "mile_stone_id")
     private MileStone mileStone;

     @ManyToOne
     @JoinColumn(name = "project_id")
     private Project project;

     @Column(name = "task_title")
     private String taskTitle;

     @Column(name = "task_content", columnDefinition = "TEXT")
     private String taskContent;

     @OneToMany(mappedBy = "taskId", cascade = {CascadeType.REMOVE})
     private List<TaskTag> tags;

     public Task(MileStone mileStoneId, Project projectId, String taskTitle, String taskContent){
          this.mileStone = mileStoneId;
          this.project = projectId;
          this.taskTitle = taskTitle;
          this.taskContent = taskContent;
     }

     public void updateTask(String taskContent, MileStone mileStone, List<TaskTag> tags){
          this.taskContent = taskContent;
          this.mileStone = mileStone;
          this.tags = tags;
     }
}
