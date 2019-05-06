package com.woniu.yoga.venue.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Coach {
    private Integer coachId;

    private Integer userId;

    private Date startTime;

    private Date endTime;

    private String coachType;

    private String coachStyle;

    private Integer coachStatus;

    private Integer fullClass;

    private BigDecimal expectedSalary;

    private String authentication;

    private Integer dealAccount;

    private Integer notCompleted;

    private Integer goodComment;

    private Integer badComment;

    private Integer commonComment;

    private Integer coachFlag;
}
