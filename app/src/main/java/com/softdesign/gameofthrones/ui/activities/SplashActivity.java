package com.softdesign.gameofthrones.ui.activities;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.softdesign.gameofthrones.R;
import com.softdesign.gameofthrones.data.managers.DataManager;
import com.softdesign.gameofthrones.mvp.presenters.IPresenter;
import com.softdesign.gameofthrones.services.DataLoadService;
import com.softdesign.gameofthrones.utils.ConstantManager;
import com.softdesign.gameofthrones.utils.NetworkStatusChecker;

public class SplashActivity extends BaseActivity {

    private Intent mServiceIntent;
    private DataLoadStateReceiver mDataLoadStateReceiver;


    @Override
    public IPresenter getPresenter() {
        return null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        IntentFilter statusFilter = new IntentFilter(ConstantManager.BROADCAST_ACTION);
        statusFilter.addCategory(Intent.CATEGORY_DEFAULT);

        mDataLoadStateReceiver = new DataLoadStateReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(mDataLoadStateReceiver, statusFilter);

        showLoad();

        if (savedInstanceState == null) {
            if (mDataManager.checkHousesData()) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startMainActivity();
                    }
                }, ConstantManager.SPLASH_TIME_OUT);
            } else {
                // загрузка из сети
                if (NetworkStatusChecker.isNetworkAvailable(this)) {
                    mServiceIntent = new Intent(this, DataLoadService.class);
                    startService(mServiceIntent);
                } else {
                    showMessage(R.string.error_network_unavailable);
                    finish();
                }
            }
        }
    }


    @Override
    public void onDestroy() {

        if (mDataLoadStateReceiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(mDataLoadStateReceiver);
            mDataLoadStateReceiver = null;
        }
        super.onDestroy();
    }


    private void startMainActivity() {

        Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }


    private void exitApplication() {
        finish();
    }


    private class DataLoadStateReceiver extends BroadcastReceiver {

        private DataLoadStateReceiver() {}


        @Override
        public void onReceive(Context context, Intent intent) {
            int status = intent.getIntExtra(ConstantManager.EXTENDED_DATA_STATUS, 0);
            if( status == 1) {
                startMainActivity();
            } else {
                exitApplication();
            }
        }
    }
}
