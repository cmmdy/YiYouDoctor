package com.example.yiyoudoctor.ui;

/**
 * Created by 夏夜晚凤 on 2017/3/2.
 */

public class Patient {
    private int imageId;

    private String name;

    private String orderTime;

    private String situation;

    public Patient(int imageId, String name, String orderTime, String situation) {
        this.imageId = imageId;
        this.name = name;
        this.orderTime = orderTime;
        this.situation = situation;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public String getSituation() {
        return situation;
    }
}
