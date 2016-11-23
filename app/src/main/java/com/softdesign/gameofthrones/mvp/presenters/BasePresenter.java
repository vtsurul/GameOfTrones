package com.softdesign.gameofthrones.mvp.presenters;

import android.support.annotation.Nullable;

import com.softdesign.gameofthrones.mvp.views.IView;


// IPresenter<T>
public abstract class BasePresenter<T extends IView> implements IPresenter {

    private T mView;


    @Nullable
    @Override
    public T getView() {
        return mView;
    }


    @Override
    public <F extends IView> void takeView(F view) {
        mView = (T)view;
    }


    @Override
    public abstract void initView();


    @Override
    public void dropView() {
        mView = null;
    }
}
