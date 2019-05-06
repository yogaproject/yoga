package com.woniu.yoga.user.pojo;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "student")
public class Student {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer studentId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(nullable = false,name = "student_flag",columnDefinition = "int default 0")
    private Integer studentFlag;
}