package com.mlio.mylifeinorder.mylifeinorder1.model;

public class Address {

    private String country;
    private String street;
    private String line2;
    private String suburb;
    private String state;
    private String postCode;

    public Address(){}
    public Address(String country, String street, String line2, String suburb, String state, String postCode) {
        this.country = country;
        this.street = street;
        this.line2 = line2;
        this.suburb = suburb;
        this.state = state;
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}
