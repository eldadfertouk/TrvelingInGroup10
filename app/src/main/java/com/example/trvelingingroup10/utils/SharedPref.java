package com.example.trvelingingroup10.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trvelingingroup10.activitys.FirstSignIn;

//todo:make it work with shared prefers class
public class SharedPref extends AppCompatActivity {
    public static SharedPreferences mSharedPref;
    public static final String DISPLAYNAME = "DISPLAYNAME";
    public static final String UID = "UID";
    public static final String EMAIL = "EMAIL";
    public static final String PHONENUMBER = "PHONENUMBER";
    public static final String IS_SELECT = "IS_SELECT";
    public static final String USERTYPE = "USERTYPE";
    public static final String LASTLOCATION = "LASTLOCATION";
    public static final String LIMITS = "LIMITS";

    private SharedPref()
    {

    }

    public static void init(Context context)
    {
        if(mSharedPref == null)
            mSharedPref = context.getSharedPreferences(context.getPackageName(), FirstSignIn.MODE_PRIVATE);
    }

    public static String read(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void write(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }

    public static boolean read(String key, boolean defValue) {
        return mSharedPref.getBoolean(key, defValue);
    }

    public static void write(String key, boolean value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.apply();
    }

    public static Integer read(String key, int defValue) {
        return mSharedPref.getInt(key, defValue);
    }

    public static void write(String key, Integer value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putInt(key, value).apply();
    }
}
