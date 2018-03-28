package com.xe.demo.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Table(name = "advert")
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String adUrl;

    private String adType;

    private String adIndex;

    private String adHref;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType;
    }

    public String getAdIndex() {
        return adIndex;
    }

    public void setAdIndex(String adIndex) {
        this.adIndex = adIndex;
    }

    public String getAdHref() {
        return adHref;
    }

    public void setAdHref(String adHref) {
        this.adHref = adHref;
    }
}