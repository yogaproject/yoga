package com.woniu.yoga.user.pojo;

import lombok.Data;
import lombok.Value;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "coach")
public class Coach {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer coachId;

    private Integer userId;

    private Date startTime;

    private Date endTime;

    private Integer coachType;

    private Integer coachStyle;

    private Integer coachStatus;

    private String coachDetail;

    private Integer fullClass;

    private BigDecimal expectedSalary;

    private Integer authentication;

    private Integer dealAccount;

    private Integer notCompleted;

    private Integer goodComment;

    private Integer badComment;

    private Integer commonComment;

    @Column(nullable = false,name = "coach_flag",columnDefinition ="int default 0")
    private Integer coachFlag;
}
