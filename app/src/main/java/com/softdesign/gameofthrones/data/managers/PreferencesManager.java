package com.softdesign.gameofthrones.data.managers;

import android.content.SharedPreferences;

import com.softdesign.gameofthrones.utils.GameofthronesApplication;

public class PreferencesManager {

    //region General

    private SharedPreferences mSharedPreferences;


    public PreferencesManager() {
        this.mSharedPreferences = GameofthronesApplication.getSharedPreferences();
    }

    //endregion
}
