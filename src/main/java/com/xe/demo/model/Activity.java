package com.xe.demo.model;

import io.swagger.models.auth.In;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private String createtime;

    private String state;

    private BigDecimal activityprice;

    private String activityidmemo;

    private String activityidcontent;

    private String activitytype;

    @Transient
    private Integer viewCount;

    @Transient
    private String activitytypename;

    @Transient
    private List<Member> membersList = new ArrayList<Member>();

//浏览过改活动的
    @Transient
    private List<Member> viewmembersList = new ArrayList<Member>();


    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public List<Member> getViewmembersList() {
        return viewmembersList;
    }

    public void setViewmembersList(List<Member> viewmembersList) {
        this.viewmembersList = viewmembersList;
    }

    public List<Member> getMembersList() {
        return membersList;
    }

    public void setMembersList(List<Member> membersList) {
        this.membersList = membersList;
    }

    public String getActivitytypename() {
        return activitytypename;
    }

    public void setActivitytypename(String activitytypename) {
        this.activitytypename = activitytypename;
    }

    public String getActivitytype() {
        return activitytype;
    }

    public void setActivitytype(String activitytype) {
        this.activitytype = activitytype;
    }

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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
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

    public String getActivityidmemo() {
        return activityidmemo;
    }

    public void setActivityidmemo(String activityidmemo) {
        this.activityidmemo = activityidmemo == null ? null : activityidmemo.trim();
    }

    public String getActivityidcontent() {
        return activityidcontent;
    }

    public void setActivityidcontent(String activityidcontent) {
        this.activityidcontent = activityidcontent == null ? null : activityidcontent.trim();
    }
}