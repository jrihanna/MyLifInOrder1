package com.mlio.mylifeinorder.mylifeinorder1.activity;

import android.widget.LinearLayout;

public abstract class HistoryWithAddressActivity extends HistoryActivity {
    private static int counter = 0;
    protected LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
    );


    /*protected Address getAddress(LinearLayout addressLayout) {
        Address address = new Address();
        address.setCountry(getLayoutEditTextValue(addressLayout, 0));
        address.setStreet(getLayoutEditTextValue(addressLayout, 1));
        address.setLine2(getLayoutEditTextValue(addressLayout, 2));
        address.setSuburb(getLayoutEditTextValue(addressLayout, 3));
        address.setState(getLayoutEditTextValue(addressLayout, 4));

        if(!getLayoutEditTextValue(addressLayout, 5).isEmpty())
            address.setPostCode(getLayoutEditTextValue(addressLayout, 5));

        return address;
    }*/
}
