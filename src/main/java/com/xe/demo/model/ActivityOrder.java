package com.xe.demo.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-10-18.
 */
@Table(name = "activityorder")
public class ActivityOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String activityid;

    private String userid;

    private BigDecimal paymoney;

    private String paymemo;

    private Integer buynum;

    private String buytime;

    private String nickname;

    private String mobile;

    private String realname;

    private BigDecimal ordermoney;

    private String wxno;

    private String status;


    private String createtime;


    @Transient
    private Activity activity;


    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivityid() {
        return activityid;
    }

    public void setActivityid(String activityid) {
        this.activityid = activityid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public BigDecimal getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(BigDecimal paymoney) {
        this.paymoney = paymoney;
    }

    public String getPaymemo() {
        return paymemo;
    }

    public void setPaymemo(String paymemo) {
        this.paymemo = paymemo;
    }

    public Integer getBuynum() {
        return buynum;
    }

    public void setBuynum(Integer buynum) {
        this.buynum = buynum;
    }

    public String getBuytime() {
        return buytime;
    }

    public void setBuytime(String buytime) {
        this.buytime = buytime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public BigDecimal getOrdermoney() {
        return ordermoney;
    }

    public void setOrdermoney(BigDecimal ordermoney) {
        this.ordermoney = ordermoney;
    }

    public String getWxno() {
        return wxno;
    }

    public void setWxno(String wxno) {
        this.wxno = wxno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
