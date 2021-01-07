package com.dcd.finance.bean;

public class Debt {
    private int debtId;
    private int actId;
    private int userId;
    private String type;
    private String debt;
    private String debtMoney;
    private String debtTime;
    private String name;
    private String surplus;

    public int getDebtId() {
        return debtId;
    }

    public void setDebtId(int debtId) {
        this.debtId = debtId;
    }

    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDebt() {
        return debt;
    }

    public void setDebt(String debt) {
        this.debt = debt;
    }

    public String getDebtMoney() {
        return debtMoney;
    }

    public void setDebtMoney(String debtMoney) {
        this.debtMoney = debtMoney;
    }

    public String getDebtTime() {
        return debtTime;
    }

    public void setDebtTime(String debtTime) {
        this.debtTime = debtTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurplus() {
        return surplus;
    }

    public void setSurplus(String surplus) {
        this.surplus = surplus;
    }
}
