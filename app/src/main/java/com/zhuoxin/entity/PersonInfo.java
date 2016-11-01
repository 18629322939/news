package com.zhuoxin.entity;

import java.util.ArrayList;

/**
 * Created by admin on 2016/10/31.
 */

public class PersonInfo {
    String message;
    String status;
    ArrayList<Result> data;


    @Override
    public String toString() {
        return "PersonInfo{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    public PersonInfo(String message, String status, ArrayList<Result> data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Result> getData() {
        return data;
    }

    public void setData(ArrayList<Result> data) {
        this.data = data;
    }
}

