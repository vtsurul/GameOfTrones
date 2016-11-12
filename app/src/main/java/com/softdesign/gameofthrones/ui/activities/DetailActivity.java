package com.softdesign.gameofthrones.ui.activities;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.softdesign.gameofthrones.R;
import com.softdesign.gameofthrones.mvp.presenters.BasePresenter;
import com.softdesign.gameofthrones.mvp.presenters.DetailPresenter;
import com.softdesign.gameofthrones.mvp.presenters.IDetailPresenter;
import com.softdesign.gameofthrones.mvp.presenters.IPresenter;
import com.softdesign.gameofthrones.mvp.views.IDetailView;
import com.softdesign.gameofthrones.mvp.views.IView;
import com.softdesign.gameofthrones.utils.ConstantManager;
import com.softdesign.gameofthrones.utils.GameofthronesApplication;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity implements IDetailView{

    //region Binding

    @BindView(R.id.item_name)
    TextView mItemName;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout mCoordinatorLayout;

    @BindView(R.id.appbar_layout)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.toolbar_layout)
    Toolbar mToolbarLayout;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @BindView(R.id.image_layout)
    ImageView mImageLayout;

    @BindView(R.id.item_words)
    TextView mItemWords;

    @BindView(R.id.item_born)
    TextView mItemBorn;

    @BindView(R.id.item_titles)
    TextView mItemTitles;

    @BindView(R.id.item_aliases)
    TextView mItemAliases;

    @BindView(R.id.item_father)
    Button mItemFather;

    @BindView(R.id.item_mother)
    Button mItemMother;

    //endregion Binding


    @Override
    public IDetailPresenter getPresenter() {
        return (IDetailPresenter)mPresenter;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPresenter = DetailPresenter.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);
        setUpToolbar();

        getPresenter().setModel(mDataManager.getDetailData());
        getPresenter().LoadModelData();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    //region IDetailView implementation


    @Override
    public void LoadModelData() {

        int houseIcon = 0;

        switch (getPresenter().getModel().getHouseId()) {
            case ConstantManager.HOUSE_ID_STARK :
                houseIcon = R.drawable.stark;
                break;
            case ConstantManager.HOUSE_ID_LANNISTER :
                houseIcon = R.drawable.lannister;
                break;
            case ConstantManager.HOUSE_ID_TARGARYEN :
                houseIcon = R.drawable.targarien;
                break;
        }

        mImageLayout.setImageDrawable(GameofthronesApplication.getContext().getResources().getDrawable(houseIcon));
        mItemName.setText(getPresenter().getModel().getName());
        mItemWords.setText(getPresenter().getModel().getHouseWords());
        mItemBorn.setText(getPresenter().getModel().getBorn());
        mItemTitles.setText(getPresenter().getModel().getTitles());
        mItemAliases.setText(getPresenter().getModel().getAliases());
        mItemFather.setText(getPresenter().getModel().getFatherName());
        mItemMother.setText(getPresenter().getModel().getMotherName());

    }

    //endregion IDetailView implementation

    private void setUpToolbar() {
        setSupportActionBar(mToolbarLayout);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

}
