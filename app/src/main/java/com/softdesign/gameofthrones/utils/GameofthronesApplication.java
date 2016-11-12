package com.softdesign.gameofthrones.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.softdesign.gameofthrones.data.managers.PreferencesManager;
import com.softdesign.gameofthrones.data.model.House;

import java.util.ArrayList;

public class GameofthronesApplication extends Application {

    private static Context sContext;
    private static SharedPreferences sSharedPreferences;


    @Override
    public void onCreate() {
        super.onCreate();

        sContext = getApplicationContext();
        sSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }


    //region Managers

    public static Context getContext() {
        return sContext;
    }


    public static SharedPreferences getSharedPreferences() {
        return sSharedPreferences;
    }

    //endregion

    //region Model

    //endregion
}
