package com.dcd.finance.bean;

public class Loan {
    private int loanId;
    private String loanName;
    private String loanContent;
    private int loanMoney;
    private String type;
    private int loanTime;

    public int getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(int loanTime) {
        this.loanTime = loanTime;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public String getLoanName() {
        return loanName;
    }

    public void setLoanName(String loanName) {
        this.loanName = loanName;
    }

    public String getLoanContent() {
        return loanContent;
    }

    public void setLoanContent(String loanContent) {
        this.loanContent = loanContent;
    }

    public int getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(int loanMoney) {
        this.loanMoney = loanMoney;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
