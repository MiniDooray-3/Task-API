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

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "members")
public class Member {

     @Id
     @Column(name = "member_number")
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long memberNumber;

     @ManyToOne
     @JoinColumn(name = "project_id")
     private Project projectId;

     @Column(name = "member_id")
     private String memberId;

     @Column(name = "member_role")
     private String memberRole;


}
