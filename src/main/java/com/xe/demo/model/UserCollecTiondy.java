package com.xe.demo.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "UserCollecTiondy")
public class UserCollecTiondy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userid;

    private Integer dynamicwzid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getDynamicwzid() {
        return dynamicwzid;
    }

    public void setDynamicwzid(Integer dynamicwzid) {
        this.dynamicwzid = dynamicwzid;
    }
}