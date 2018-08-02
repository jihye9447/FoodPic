package com.example.sinjihye.foodpic.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreference {

    // Avoid magic numbers.
    
    private final static String PREF_NAME = "foodpic.pref";
    



    public void put(Context context, String key, String value) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME,
                MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString(key, value);
        editor.apply();
    }
   
    public void put(Context context,String key, long value) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME,
                MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putLong(key, value);
        editor.apply();
    }
    public void put(Context context, String key, HashSet<String> value){
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putStringSet(key,value);
        editor.apply();
    }
    public void put(Context context,String key, boolean value) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME,
                MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putBoolean(key, value);
        editor.apply();
    }

    public void put(Context context,String key, int value) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME,
                MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putInt(key, value);
        editor.apply();
    }
    public void remove(Context context,String key){
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        editor.apply();
    }
    
    public void removeAll(Context context){
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.apply();
    }
    
    public String getValue(Context context,String key, String dftValue) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME,
                MODE_PRIVATE);

        try {
            return pref.getString(key, dftValue);
        } catch (Exception e) {
            return dftValue;
        }

    }
 
    public Set<String> getValue(Context context, String key, HashSet<String> value){
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        try {
            return pref.getStringSet(key,value);
        }catch (Exception e){
            return value;
        }
    }
    public int getValue(Context context,String key, int dftValue) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME,
                MODE_PRIVATE);

        try {
            return pref.getInt(key, dftValue);
        } catch (Exception e) {
            return dftValue;
        }

    }
    public long getValue(Context context,String key, long dftValue) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME,
                MODE_PRIVATE);

        try {
            return pref.getLong(key, dftValue);
        } catch (Exception e) {
            return dftValue;
        }

    }

    public boolean getValue(Context context ,String key, boolean dftValue) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME,
                MODE_PRIVATE);

        try {
            return pref.getBoolean(key, dftValue);
        } catch (Exception e) {
            return dftValue;
        }
    }

}