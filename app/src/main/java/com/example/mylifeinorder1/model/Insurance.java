package com.example.mylifeinorder1.model;

public class Insurance extends History {
    private int insuranceType;
    private String insuranceCompany;
    private int per; // Week, Month, Year
    private String amount;

    public Insurance() {}

    public Insurance(int insuranceType, String insuranceCompany, int per, String amount) {
        this.insuranceType = insuranceType;
        this.insuranceCompany = insuranceCompany;
        this.per = per;
        this.amount = amount;
    }

    public int getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(int insuranceType) {
        this.insuranceType = insuranceType;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public int getPer() {
        return per;
    }

    public void setPer(int per) {
        this.per = per;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
