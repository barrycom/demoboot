package com.xe.demo.common.pojo;

import com.xe.demo.model.Member;

import java.util.List;

/**
 * Created by Administrator on 2017-10-31.
 */
public class SzmData {
    public  String alphabet;
    public List<Member> members;
    public  boolean ishy;
    public SzmData(String alphabet, List<Member> members) {
        this.alphabet = alphabet;
        this.members = members;
    }

    public String getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(String alphabet) {
        this.alphabet = alphabet;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public boolean getIshy() {
        return ishy;
    }

    public void setIshy(boolean ishy) {
        this.ishy = ishy;
    }
}
