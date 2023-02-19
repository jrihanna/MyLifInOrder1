package com.example.mylifeinorder1.model;

public class Employment extends HistoryWithAddressAndContact {

    private String role;

    public Employment(String companyName, String role, String number, String emailAddress, Address address) {
        this.name = companyName;
        this.role = role;
        this.number = number;
        this.emailAddress = emailAddress;
        this.address = address;
    }
}
