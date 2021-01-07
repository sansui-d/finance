package com.dcd.finance.bean;

public class Account {
    private int actId;
    private String actName;
    private String actPass;
    private Integer houseHolder;
    private String surplus;

    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getActPass() {
        return actPass;
    }

    public void setActPass(String actPass) {
        this.actPass = actPass;
    }

    public int getHouseHolder() {
        return houseHolder;
    }

    public void setHouseHolder(Integer houseHolder) {
        this.houseHolder = houseHolder;
    }

    public String getSurplus() {
        return surplus;
    }

    public void setSurplus(String surplus) {
        this.surplus = surplus;
    }
}
