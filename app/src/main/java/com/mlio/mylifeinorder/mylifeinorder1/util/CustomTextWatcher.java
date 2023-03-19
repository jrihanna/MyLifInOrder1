package com.mlio.mylifeinorder.mylifeinorder1.util;

import android.text.Editable;
import android.text.TextWatcher;

public interface CustomTextWatcher extends TextWatcher {
    public default void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public abstract void onTextChanged(CharSequence charSequence, int i, int i1, int i2);
    @Override
    public default void afterTextChanged(Editable editable) {

    }
}
