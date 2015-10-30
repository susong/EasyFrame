package com.dream.library.utils;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

public class SimpleTextWatcher implements TextWatcher {

    private View mView;

    public SimpleTextWatcher(View view) {
        this.mView = view;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mView.setVisibility(TextUtils.isEmpty(s) ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

}
