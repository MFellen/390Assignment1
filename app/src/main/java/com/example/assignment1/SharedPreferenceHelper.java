package com.example.assignment1;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceHelper {
    private SharedPreferences sharedPreferences;

    public SharedPreferenceHelper(Context context) {
        sharedPreferences = context.getSharedPreferences("ProfilePreference", Context.MODE_PRIVATE);
    }

    public void saveSettings(Settings s) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("eventA", s.getA());
        editor.commit();
        editor.putString("eventB", s.getB());
        editor.commit();
        editor.putString("eventC", s.getC());
        editor.commit();
        editor.putInt("maxCount", s.getMaxCount());
        editor.commit();
    }

    public void saveCount(int a, int b, int c, int count, String events) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("countA", a);
        editor.commit();
        editor.putInt("countB", b);
        editor.commit();
        editor.putInt("countC", c);
        editor.commit();
        editor.putInt("count", count);
        editor.commit();
        editor.putString("events", events);
        editor.commit();
    }

    public String getEventA() {
        return sharedPreferences.getString("eventA", null);
    }
    public String getEventB() {
        return sharedPreferences.getString("eventB", null);
    }
    public String getEventC() {
        return sharedPreferences.getString("eventC", null);
    }
    public int getMaxCount(){
        return sharedPreferences.getInt("maxCount", 0);
    }

    public int getCountA(){return sharedPreferences.getInt("countA", 0);}

    public int getCountB(){return sharedPreferences.getInt("countB", 0);}

    public int getCountC(){
        return sharedPreferences.getInt("countC", 0);
    }

    public int getCount(){
        return sharedPreferences.getInt("count", 0);
    }

    public String getEvents() {
        return sharedPreferences.getString("events", "");
    }


    public void clearPreferences(){  // to clear the sharedpreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
