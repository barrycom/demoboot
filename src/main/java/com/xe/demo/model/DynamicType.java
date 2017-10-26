package com.xe.demo.model;

import javax.persistence.*;

@Table(name = "dynamic_type")
public class DynamicType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String dynamicname;

    private String creat_time;

    private String state;


    @Transient
    private boolean chosen;
    @Transient
    private String userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDynamicname() {
        return dynamicname;
    }

    public void setDynamicname(String dynamicname) {
        this.dynamicname = dynamicname;
    }

    public String getCreat_time() {
        return creat_time;
    }

    public void setCreat_time(String creat_time) {
        this.creat_time = creat_time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isChosen() {
        return chosen;
    }

    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}