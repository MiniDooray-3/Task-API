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
@Table(name = "comments")
public class Comment {

     @Id
     @Column(name = "comment_id")
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long commentId;

     @ManyToOne
     @JoinColumn(name = "task_id")
     private Task taskId;

     @ManyToOne
     @JoinColumn(name = "member_number")
     private Member memberId;

     @Column(name = "comment_content", columnDefinition = "TEXT")
     private String commentContent;




}
