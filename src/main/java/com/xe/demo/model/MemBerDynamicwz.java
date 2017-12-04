package com.xe.demo.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "MemBerDynamicwz")
public class MemBerDynamicwz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userid;

    private String dynamicwz;

    private String tag_id;

    private String createtime;

    private String state;

    private String industryid;

    private String uname;

    private String headimg;

    private String imgurl;

    private String realname;

    private String mobile;

    private String dynamicname;

    private String corporatename;

    private String profession;

    private  String dynamicid;//行业id

    private String isinterest;

    private String dynamicwzall;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getDynamicwz() {
        return dynamicwz;
    }

    public void setDynamicwz(String dynamicwz) {
        this.dynamicwz = dynamicwz;
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
        this.state = state;
    }

    public String getIndustryid() {
        return industryid;
    }

    public void setIndustryid(String industryid) {
        this.industryid = industryid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDynamicname() {
        return dynamicname;
    }

    public void setDynamicname(String dynamicname) {
        this.dynamicname = dynamicname;
    }

    public String getCorporatename() {
        return corporatename;
    }

    public void setCorporatename(String corporatename) {
        this.corporatename = corporatename;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getDynamicid() {
        return dynamicid;
    }

    public void setDynamicid(String dynamicid) {
        this.dynamicid = dynamicid;
    }

    public String getIsinterest() {
        return isinterest;
    }

    public void setIsinterest(String isinterest) {
        this.isinterest = isinterest;
    }

    public String getDynamicwzall() {
        return dynamicwzall;
    }

    public void setDynamicwzall(String dynamicwzall) {
        this.dynamicwzall = dynamicwzall;
    }

    public String getTag_id() {
        return tag_id;
    }

    public void setTag_id(String tag_id) {
        this.tag_id = tag_id;
    }
}