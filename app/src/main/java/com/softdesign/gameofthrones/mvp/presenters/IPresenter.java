package com.softdesign.gameofthrones.mvp.presenters;

import android.support.annotation.Nullable;

import com.softdesign.gameofthrones.mvp.views.IView;

// <T extends IView>
public interface IPresenter {

    <T extends IView>
    void takeView(T view);

    @Nullable <T extends IView>
    T getView();

    void initView();

    void dropView();
}
