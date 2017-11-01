package com.xe.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "MemberBuy")
public class MemberBuy implements Serializable {
    private static final long serialVersionUID = -8366929034564774130L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userid;

    private String buytime;

    private String buyprice;

    private String buystate;

    private String orderno;

    private String productid;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public String getBuytime() {
        return buytime;
    }

    public void setBuytime(String buytime) {
        this.buytime = buytime;
    }

    public String getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(String buyprice) {
        this.buyprice = buyprice;
    }

    public String getBuystate() {
        return buystate;
    }

    public void setBuystate(String buystate) {
        this.buystate = buystate;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }
}