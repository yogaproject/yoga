package com.woniu.yoga.user.pojo;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer courseId;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "coach_id")
    private Integer coachId;
    @Column(name = "course_img")
    private String courseImg;
    @Column(name = "course_detail")
    private String courseDetail;
    @Column(name = "course_price")
    private BigDecimal coursePrice;
    @Column(name = "course_flag")
    private Integer courseFlag;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCoachId() {
        return coachId;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }

    public String getCourseImg() {
        return courseImg;
    }

    public void setCourseImg(String courseImg) {
        this.courseImg = courseImg;
    }

    public String getCourseDetail() {
        return courseDetail;
    }

    public void setCourseDetail(String courseDetail) {
        this.courseDetail = courseDetail;
    }

    public BigDecimal getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(BigDecimal coursePrice) {
        this.coursePrice = coursePrice;
    }

    public Integer getCourseFlag() {
        return courseFlag;
    }

    public void setCourseFlag(Integer courseFlag) {
        this.courseFlag = courseFlag;
    }
}