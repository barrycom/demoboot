package com.xe.demo.model;

import javax.persistence.*;

@Table(name = "memberInfo")
public class MemberInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String realname;
    private String memberid;
    private String cardno;
    private String cardfront;
    private String cardback;
    private String ispass;  //审核状态  0 待审核 1已通过 2已拒绝
    private String remark;
    private String createtime;
    private String regtime;  //审核时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getCardfront() {
        return cardfront;
    }

    public void setCardfront(String cardfront) {
        this.cardfront = cardfront;
    }

    public String getCardback() {
        return cardback;
    }

    public void setCardback(String cardback) {
        this.cardback = cardback;
    }

    public String getIspass() {
        return ispass;
    }

    public void setIspass(String ispass) {
        this.ispass = ispass;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getRegtime() {
        return regtime;
    }

    public void setRegtime(String regtime) {
        this.regtime = regtime;
    }
}