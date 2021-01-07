package com.dcd.finance.bean;

public class Insure {
    private int sureId;
    private String sureTitle;
    private String sureContent;
    private String sureMoney;
    private int sureTime;
    private String type;

    public int getSureId() {
        return sureId;
    }

    public void setSureId(int sureId) {
        this.sureId = sureId;
    }

    public String getSureTitle() {
        return sureTitle;
    }

    public void setSureTitle(String sureTitle) {
        this.sureTitle = sureTitle;
    }

    public String getSureContent() {
        return sureContent;
    }

    public void setSureContent(String sureContent) {
        this.sureContent = sureContent;
    }

    public String getSureMoney() {
        return sureMoney;
    }

    public void setSureMoney(String sureMoney) {
        this.sureMoney = sureMoney;
    }

    public int getSureTime() {
        return sureTime;
    }

    public void setSureTime(int sureTime) {
        this.sureTime = sureTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
