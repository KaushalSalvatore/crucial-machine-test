package com.crucialtechnologies.task.mapfragment.projectHelper;

import android.graphics.Typeface;

import java.util.HashMap;

public class TypefaceCache {

    private static TypefaceCache instance = new TypefaceCache();

    private final HashMap<String, Typeface> typefaceList = new HashMap<>();

    public static TypefaceCache getInstance() {
        return instance;
    }

    private TypefaceCache() {
    }

    public Typeface getTypeface(String asset) throws Exception {

        synchronized (typefaceList) {
            Typeface typeface;
            if (!typefaceList.isEmpty() && typefaceList.containsKey(asset)) {
                typeface = typefaceList.get(asset);
            } else {
                typeface = Typeface.createFromAsset(MyApplication.getInstance().getAssets(), asset);
                typefaceList.put(asset, typeface);
            }
            return typeface;
        }
    }
}
