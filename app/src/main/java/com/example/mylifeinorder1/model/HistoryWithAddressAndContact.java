package com.example.mylifeinorder1.model;

public class HistoryWithAddressAndContact extends HistoryWithAddress {
    protected String number;
    protected String emailAddress;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
