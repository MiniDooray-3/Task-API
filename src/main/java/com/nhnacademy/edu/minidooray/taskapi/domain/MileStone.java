package com.nhnacademy.edu.minidooray.taskapi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "milestones")
public class MileStone {
     @Id
     @Column(name = "mile_stone_id")
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long mileStoneId;

     @ManyToOne
     @JoinColumn(name = "project_id")
     private Project projectId;

     @Column(name = "mile_stone_status")
     private String mileStoneStatus;

}
