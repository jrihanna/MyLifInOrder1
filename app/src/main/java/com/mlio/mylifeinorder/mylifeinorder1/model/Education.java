package com.mlio.mylifeinorder.mylifeinorder1.model;

public class Education extends HistoryWithAddressAndContact {

    private String fieldOfStudy;
    private String grade;

    public Education(String name, String fieldOfStudy, String grade, String number, String emailAddress, Address address) {
        this.name = name;
        this.fieldOfStudy = fieldOfStudy;
        this.grade = grade;
        this.number = number;
        this.emailAddress = emailAddress;
        this.address = address;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
