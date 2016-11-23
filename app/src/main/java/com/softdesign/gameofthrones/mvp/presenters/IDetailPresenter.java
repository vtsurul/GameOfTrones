package com.softdesign.gameofthrones.mvp.presenters;

import com.softdesign.gameofthrones.data.model.Character;
import com.softdesign.gameofthrones.mvp.views.IDetailView;
import com.softdesign.gameofthrones.mvp.views.IView;

// IPresenter<IDetailView>
public interface IDetailPresenter extends IPresenter{

    // TODO: 12.11.2016 Сделать базовый класс для "модели" и перенести get/setModel в IPresenter 
    
    Character getModel();

    void setModel(Character model);

    void LoadModelData();
}
