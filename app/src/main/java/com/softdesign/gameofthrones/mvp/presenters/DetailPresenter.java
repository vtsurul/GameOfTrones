package com.softdesign.gameofthrones.mvp.presenters;


import android.support.annotation.Nullable;

import com.softdesign.gameofthrones.data.model.Character;
import com.softdesign.gameofthrones.mvp.views.IDetailView;
import com.softdesign.gameofthrones.mvp.views.IView;

public class DetailPresenter extends BasePresenter<IDetailView> implements IDetailPresenter {


    //region Singleton

    private static DetailPresenter sInstance = new DetailPresenter();


    public static DetailPresenter getInstance() {
        return sInstance;
    }

    //endregion


    private Character mModel;


    // region IDetailPresenter implementation


    @Override
    public Character getModel() {
        return mModel;
    }


    @Override
    public void setModel(Character model) {
        mModel = model;
    }


    @Override
    public void LoadModelData() {
        if (getView() != null) {
            getView().LoadModelData();
        }
    }

    @Override
    public void initView() {

    }

    //endregion IDetailPresenter implementation
}
