package com.masterkpkit.anhtam.kpkit.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.masterkpkit.anhtam.kpkit.model.User;

public class PreferenceManager {

    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    private static PreferenceManager mInstance;
    private boolean isAuthencation;
    private String idUser;

    public static PreferenceManager getmInstance(Context mContext) {
        if (mInstance == null)
            return new PreferenceManager(mContext);
        return mInstance;
    }

    public PreferenceManager(Context mContext) {
        mPref = mContext.getSharedPreferences(Constan.SHAREPREFERENCE_NAME, Context.MODE_PRIVATE);
        mEditor = mPref.edit();
    }

    public boolean isAuthencation() {
        return mPref.getBoolean(Constan.SHAREPREFERENCE_NAME, false);
    }

    public void setAuthencation(boolean authencation) {
        mEditor.putBoolean(Constan.IS_AUTHENCATION, authencation);
        mEditor.commit();
    }

    public User getUser() {
        Gson gson = new Gson();
        String json = mPref.getString(Constan.USER, "");
        return gson.fromJson(json, User.class);
    }

    public void setUser(User user) {
        Gson gson = new Gson();
        String json = gson.toJson(user);
        mEditor.putString(Constan.USER, json);
        mEditor.commit();
    }

    public String getIdUser (){
        return mPref.getString(Constan.ID_USER, "");
    }

    public void setIdUser(String idUser) {
        mEditor.putString(Constan.ID_USER, idUser);
        mEditor.commit();
    }
}
