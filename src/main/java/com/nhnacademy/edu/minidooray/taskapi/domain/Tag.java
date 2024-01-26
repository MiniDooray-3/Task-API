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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tags")
public class Tag {

     @Id
     @Column(name = "tag_id")
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long tagId;

     @Column(name = "tag_name")
     private String tagName;

     @ManyToOne
     @JoinColumn(name = "project_id")
     private Project projectId;

     @OneToMany(mappedBy = "taskId")
     private List<TaskTag> taskTagList;
}
