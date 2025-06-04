package com.transparent.fleet.util;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class CustomEditText extends EditText {

    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr)   {
        super(context, attrs, defStyleAttr);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setTypeface(Typeface tf) {
        super.setTypeface(FontCache.getFont(getContext(), "Poppins-Regular.otf"));
    }
}

