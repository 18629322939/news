package com.zhuoxin.entity;

/**
 * Created by admin on 2016/10/28.
 */

public class LiftInfo {
    String cname;
    String ename;


    @Override
    public String toString() {
        return "LiftInfo{" +
                "cname='" + cname + '\'' +
                ", ename='" + ename + '\'' +

                '}';
    }

    public LiftInfo(String cname, String ename) {
        this.cname = cname;
        this.ename = ename;

    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

}
