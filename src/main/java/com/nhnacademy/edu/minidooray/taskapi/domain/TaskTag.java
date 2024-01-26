package com.nhnacademy.edu.minidooray.taskapi.domain;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "task_tag")
public class TaskTag {

     @EmbeddedId
     private TaskTagPk taskTagPk;

     @MapsId("taskId")
     @ManyToOne
     @JoinColumn(name = "task_id")
     private Task taskId;

     @MapsId("tagId")
     @ManyToOne (cascade = CascadeType.REMOVE)
     @JoinColumn(name = "tag_id")
     private Tag tagId;

     @NoArgsConstructor
     @AllArgsConstructor
     @Embeddable
     public static class TaskTagPk implements Serializable {
          @Column(name = "task_id")
          private Long taskId;

          @Column(name = "tag_id")
          private Long tagId;
     }


}
