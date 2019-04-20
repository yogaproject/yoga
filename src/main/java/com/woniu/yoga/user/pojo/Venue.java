package com.woniu.yoga.user.pojo;

public class Venue {
    private Integer venueId;

    private Integer userId;

    private Long clicks;

    private String img1;

    private String img2;

    private String img3;

    private String venueDetail;

    private Integer venueFlag;

    public Integer getVenueId() {
        return venueId;
    }

    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getClicks() {
        return clicks;
    }

    public void setClicks(Long clicks) {
        this.clicks = clicks;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getVenueDetail() {
        return venueDetail;
    }

    public void setVenueDetail(String venueDetail) {
        this.venueDetail = venueDetail;
    }

    public Integer getVenueFlag() {
        return venueFlag;
    }

    public void setVenueFlag(Integer venueFlag) {
        this.venueFlag = venueFlag;
    }
}