package com.mlio.mylifeinorder.mylifeinorder1.model.enums;

public enum LoanType {
    CAR("Car"), HOME("Home"), PERSONAL("Personal");

    private String value;

    LoanType(String value) {
        this.value = value;
    }

    public static int getIndex(String val) {
        if(val == null)
            val = "";
        try {
            return valueOf(val.toUpperCase()).ordinal();
        } catch (Exception e) {
            return 0;
        }
    }

    public static int getIndex(int index) {
        try {
            return values()[index].ordinal();
        } catch (Exception e) {
            return 0;
        }
    }
}
