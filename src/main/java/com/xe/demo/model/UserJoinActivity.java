package com.xe.demo.model;

import javax.persistence.Table;

/**
 * Created by Administrator on 2017-10-17.
 */
@Table(name = "userjoinactivity")
public class UserJoinActivity {
    private String id;
    private String userid;
    private String activityid;
    private String jointime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getActivityid() {
        return activityid;
    }

    public void setActivityid(String activityid) {
        this.activityid = activityid;
    }

    public String getJointime() {
        return jointime;
    }

    public void setJointime(String jointime) {
        this.jointime = jointime;
    }
}
