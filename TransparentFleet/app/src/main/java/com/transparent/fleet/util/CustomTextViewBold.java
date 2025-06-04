package com.transparent.fleet.util;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class CustomTextViewBold extends TextView {

    public CustomTextViewBold(Context context) {
        super(context);
    }

    public CustomTextViewBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomTextViewBold(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setTypeface(Typeface tf) {
        super.setTypeface(FontCache.getFont(getContext(), "Poppins-Bold.otf"));
    }
}

class FontCacheBold {

    private static Map<String, Typeface> fontMap = new HashMap<String, Typeface>();

    public static Typeface getFont(Context context, String fontname){
        if(fontMap.containsKey(fontname)){
            return fontMap.get(fontname);
        }
        else{
            Typeface tf = Typeface.createFromAsset(context.getAssets(),fontname);
            fontMap.put(fontname,tf);
            return tf;
        }
    }
}


