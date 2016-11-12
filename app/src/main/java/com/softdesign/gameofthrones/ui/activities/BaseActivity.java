package com.softdesign.gameofthrones.ui.activities;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.softdesign.gameofthrones.R;
import com.softdesign.gameofthrones.data.managers.DataManager;
import com.softdesign.gameofthrones.mvp.presenters.BasePresenter;
import com.softdesign.gameofthrones.mvp.presenters.IPresenter;
import com.softdesign.gameofthrones.mvp.views.IView;
import com.softdesign.gameofthrones.utils.ConstantManager;

public abstract class BaseActivity extends AppCompatActivity implements IView{

    protected static final String LOG_TAG = ConstantManager.TAG_PREFIX + "BaseActivity";

    private ProgressDialog mProgressDialog;
    protected DataManager mDataManager;

    protected BasePresenter mPresenter;

    @Override
    public abstract IPresenter getPresenter();

    //region Lifecycle


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataManager = DataManager.getInstance();

        if (mPresenter != null) {
            mPresenter.takeView(this);
            mPresenter.initView();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }


    @Override
    protected void onDestroy() {
        if (mPresenter != null) mPresenter.dropView();
        super.onDestroy();
    }


    //endregion


    //region IView implementation


    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


    @Override
    public void showMessage(int message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


    @Override
    public void showError(Throwable e) {
        showMessage(e.getMessage());
    }


    @Override
    public void showSnackBar(CoordinatorLayout coordinator, String message) {
        Snackbar.make(coordinator, message, Snackbar.LENGTH_LONG).show();
    }


    @Override
    public void showLoad() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this, R.style.custom_dialog);
            mProgressDialog.setCancelable(false);
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        mProgressDialog.show();
        mProgressDialog.setContentView(R.layout.progress_splash);
    }


    @Override
    public void hideLoad() {
        if (mProgressDialog != null) {
            if (mProgressDialog.isShowing()) {
                mProgressDialog.hide();
                mProgressDialog = null;
            }
        }
    }

    //endregion
}
