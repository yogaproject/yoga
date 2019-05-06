package com.woniu.yoga.user.pojo;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
public class Course {
    private Integer courseId;

    private String courseName;

    private Integer coachId;

    private String courseImg;

    private String courseDetail;

    private BigDecimal coursePrice;

    private Integer courseFlag;

}