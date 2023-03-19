package com.mlio.mylifeinorder.mylifeinorder1.model.enums;

public enum PaymentPer {
    WEEK("Week"), FORTNIGHT("Fortnight"), MONTH("Month"), QUARTER("Quarter"), SIX_MONTH("6 Month"), ANNUAL("Annual");

    private String value;

    PaymentPer(String value) {
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
