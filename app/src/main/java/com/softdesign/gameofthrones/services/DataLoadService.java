package com.softdesign.gameofthrones.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.softdesign.gameofthrones.data.managers.DataManager;

public class DataLoadService extends IntentService {

    public static final String LOG_TAG = "DataLoadService";

    private DataLoadBroadcastNotifier mBroadcaster = new DataLoadBroadcastNotifier(this);


    public DataLoadService() {
        super(DataLoadService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        boolean result = false;

        result = DataManager.getInstance().loadDataFromNetwork();

        if (result) {
            mBroadcaster.broadcastIntentWithState(1);
        } else {
            mBroadcaster.broadcastIntentWithState(0);
        }
    }
}
