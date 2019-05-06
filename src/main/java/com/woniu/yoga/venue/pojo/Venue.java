package com.woniu.yoga.venue.pojo;

import lombok.Data;

@Data
public class Venue {
    private Integer venueId;

    private Integer userId;

    private Long clicks;

    private String img1;

    private String img2;

    private String img3;

    private String venueDetail;

    private Integer venueFlag;

}