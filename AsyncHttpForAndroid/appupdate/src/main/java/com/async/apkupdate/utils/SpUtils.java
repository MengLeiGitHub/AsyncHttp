package com.async.apkupdate.utils;

import android.app.Activity;
import android.app.Application;

import android.content.SharedPreferences;



public class SpUtils {
    private static String name = "ApkUpdate";

    public static void save(Application application, String key, String value) {
        SharedPreferences mySharedPreferences = application.getSharedPreferences(name,
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();


    }
    public static String get(Application application,String key) {
         SharedPreferences mySharedPreferences = application.getSharedPreferences(name,
                Activity.MODE_PRIVATE);
        return mySharedPreferences.getString(key, "");
    }
    public static void delAll(Application  application) {
         SharedPreferences mySharedPreferences = application.getSharedPreferences(name,
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
