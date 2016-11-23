package com.softdesign.gameofthrones.mvp.views;

import android.support.design.widget.CoordinatorLayout;

import com.softdesign.gameofthrones.mvp.presenters.IPresenter;

public interface IView {

    IPresenter getPresenter();

    void showMessage(String message);
    void showMessage(int message);
    void showError(Throwable e);
    void showSnackBar(CoordinatorLayout coordinator, String message);

    void showLoad();
    void hideLoad();
}
