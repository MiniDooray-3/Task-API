package com.nhnacademy.edu.minidooray.taskapi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

     public Comment(Task taskId, Member memberId, String commentContent) {
          this.taskId = taskId;
          this.memberId = memberId;
          this.commentContent = commentContent;
     }

     public void modifyContent(String content) {
          this.commentContent = content;
     }
}
