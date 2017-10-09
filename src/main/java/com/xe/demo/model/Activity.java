package com.xe.demo.model;

import java.math.BigDecimal;
import java.util.Date;

public class Activity {
    private String id;

    private String activityid;

    private String activityname;

    private String activitysdate;

    private String activityedate;

    private String activityaddr;

    private String latitude;

    private String longitude;

    private String activityimg;

    private Date createtime;

    private String state;

    private BigDecimal activityprice;

    private String activityidcontent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getActivityid() {
        return activityid;
    }

    public void setActivityid(String activityid) {
        this.activityid = activityid == null ? null : activityid.trim();
    }

    public String getActivityname() {
        return activityname;
    }

    public void setActivityname(String activityname) {
        this.activityname = activityname == null ? null : activityname.trim();
    }

    public String getActivitysdate() {
        return activitysdate;
    }

    public void setActivitysdate(String activitysdate) {
        this.activitysdate = activitysdate == null ? null : activitysdate.trim();
    }

    public String getActivityedate() {
        return activityedate;
    }

    public void setActivityedate(String activityedate) {
        this.activityedate = activityedate == null ? null : activityedate.trim();
    }

    public String getActivityaddr() {
        return activityaddr;
    }

    public void setActivityaddr(String activityaddr) {
        this.activityaddr = activityaddr == null ? null : activityaddr.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getActivityimg() {
        return activityimg;
    }

    public void setActivityimg(String activityimg) {
        this.activityimg = activityimg == null ? null : activityimg.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public BigDecimal getActivityprice() {
        return activityprice;
    }

    public void setActivityprice(BigDecimal activityprice) {
        this.activityprice = activityprice;
    }

    public String getActivityidcontent() {
        return activityidcontent;
    }

    public void setActivityidcontent(String activityidcontent) {
        this.activityidcontent = activityidcontent == null ? null : activityidcontent.trim();
    }
}