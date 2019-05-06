package com.woniu.yoga.venue.pojo;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "venue")



public class Venue {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer venueId;

    private Integer userId;

    private Long clicks;

    private String img1;

    private String img2;

    private String img3;

    private String venueDetail;

    private Integer venueFlag;


}