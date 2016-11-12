package com.softdesign.gameofthrones.services;


import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.softdesign.gameofthrones.utils.ConstantManager;

public class DataLoadBroadcastNotifier {

    private LocalBroadcastManager mBroadcaster;


    public DataLoadBroadcastNotifier(Context context) {

        mBroadcaster = LocalBroadcastManager.getInstance(context);
    }


    public void broadcastIntentWithState(int status) {

        Intent localIntent = new Intent();

        localIntent.setAction(ConstantManager.BROADCAST_ACTION);
        localIntent.putExtra(ConstantManager.EXTENDED_DATA_STATUS, status);
        localIntent.addCategory(Intent.CATEGORY_DEFAULT);

        mBroadcaster.sendBroadcast(localIntent);
    }


    public void notifyProgress(String logData) {

        Intent localIntent = new Intent();

        localIntent.setAction(ConstantManager.BROADCAST_ACTION);
        localIntent.putExtra(ConstantManager.EXTENDED_DATA_STATUS, -1);
        localIntent.putExtra(ConstantManager.EXTENDED_STATUS_LOG, logData);
        localIntent.addCategory(Intent.CATEGORY_DEFAULT);

        mBroadcaster.sendBroadcast(localIntent);

    }
}
