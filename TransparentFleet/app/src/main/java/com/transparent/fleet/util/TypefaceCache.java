package com.transparent.fleet.util;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import java.util.Hashtable;

public class TypefaceCache {

    private static final Hashtable<String, Typeface> CACHE = new Hashtable<>();

    private static final String BOLD = "Poppins-Bold.otf";
    private static final String ITALIC= "Poppins-Italic.otf";
    private static final String REGULAR = "Poppins-Regular.otf";

    public static Typeface get(AssetManager manager, int typefaceCode) {
        synchronized (CACHE) {

            String typefaceName = getTypefaceName(typefaceCode);


            if (!CACHE.containsKey(typefaceName)) {
                Typeface t = Typeface.createFromAsset(manager, typefaceName);

                CACHE.put(typefaceName, t);
            }
            return CACHE.get(typefaceName);
        }
    }

    private static String getTypefaceName(int typefaceCode) {
        switch (typefaceCode) {
            case 0:
                return BOLD;
            case 1:
                return ITALIC;
            case 2:
                return REGULAR;
            default:
                return null;
        }
    }

}