package com.woniu.yoga.user.pojo;

import javax.persistence.*;

//@Entity
//@Table(name = "student_coach")
public class StudentAndCoach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scId;
    @Column(name = "student_id")
    private Integer studentId;
    @Column(name = "coach_id")
    private Integer coachId;

    public Integer getScId() {
        return scId;
    }

    public void setScId(Integer scId) {
        this.scId = scId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCoachId() {
        return coachId;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }
}