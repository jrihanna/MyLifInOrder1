package com.example.mylifeinorder1.model;

import java.math.BigDecimal;

public class Loan extends History {
    private int loanType; // Home, Car, ...
    private String loanInstituteName;
    private BigDecimal initialAmount;

    private BigDecimal repaymentAmount;
    private int per;

    public Loan(){}

    public Loan(int loanType, String loanInstituteName, BigDecimal amount, BigDecimal repaymentAmount, int per) {
        this.loanType = loanType;
        this.loanInstituteName = loanInstituteName;
        this.initialAmount = amount;
        this.repaymentAmount = repaymentAmount;
        this.per = per;
    }

    public int getLoanType() {
        return loanType;
    }

    public void setLoanType(int loanType) {
        this.loanType = loanType;
    }

    public String getLoanInstituteName() {
        return loanInstituteName;
    }

    public void setLoanInstituteName(String loanInstituteName) {
        this.loanInstituteName = loanInstituteName;
    }

    public BigDecimal getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(BigDecimal initialAmount) {
        this.initialAmount = initialAmount;
    }

    public int getPer() {
        return per;
    }

    public void setPer(int per) {
        this.per = per;
    }

    public BigDecimal getRepaymentAmount() {
        return repaymentAmount;
    }

    public void setRepaymentAmount(BigDecimal repaymentAmount) {
        this.repaymentAmount = repaymentAmount;
    }

}
