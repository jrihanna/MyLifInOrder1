package com.example.mylifeinorder1.model;

public abstract class HistoryWithAddress extends History {

    @Deprecated
    public HistoryWithAddress() {}
    protected Address address;

    public HistoryWithAddress(Address address) {
        this.address = address;
    }
}
