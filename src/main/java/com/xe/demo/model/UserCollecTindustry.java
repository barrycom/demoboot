package com.xe.demo.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "usercollectindustry")
public class UserCollecTindustry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userid;

    private Integer dynamicwzid;

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

    public Integer getDynamicwzid() {
        return dynamicwzid;
    }

    public void setDynamicwzid(Integer dynamicwzid) {
        this.dynamicwzid = dynamicwzid;
    }
}