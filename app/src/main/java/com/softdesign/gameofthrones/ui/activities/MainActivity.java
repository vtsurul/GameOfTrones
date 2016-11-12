package com.softdesign.gameofthrones.ui.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

import com.softdesign.gameofthrones.R;
import com.softdesign.gameofthrones.data.managers.DataManager;
import com.softdesign.gameofthrones.data.model.Character;
import com.softdesign.gameofthrones.ui.adapters.CharactersAdapter;
import com.softdesign.gameofthrones.utils.ConstantManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, CharactersAdapter.CharacterViewHolder.CustomClickListener {

    @BindView(R.id.navigation_drawer)
    DrawerLayout mNavigationDrawer;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout mCoordinatorLayout;

    @BindView(R.id.appbar_layout)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.toolbar_layout)
    Toolbar mToolbarLayout;

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setUpToolbar();
        setupNavigationDrawer();

        mTabLayout.addTab(mTabLayout.newTab().setText("Starks").setContentDescription(String.valueOf(ConstantManager.HOUSE_ID_STARK)));
        mTabLayout.addTab(mTabLayout.newTab().setText("Lannister").setContentDescription(String.valueOf(ConstantManager.HOUSE_ID_LANNISTER)));
        mTabLayout.addTab(mTabLayout.newTab().setText("Targaryen").setContentDescription(String.valueOf(ConstantManager.HOUSE_ID_TARGARYEN)));
        mTabLayout.setOnTabSelectedListener(this);

        DataManager.getInstance().loadDataFromDb();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.swapAdapter(getCharactersAdapter(ConstantManager.HOUSE_ID_STARK), false);
    }


    @Override
    public void onBackPressed() {

        if (mNavigationDrawer.isDrawerOpen(GravityCompat.START)) {
            mNavigationDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            mNavigationDrawer.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        switch (Integer.parseInt(String.valueOf(tab.getContentDescription()))) {
            case ConstantManager.HOUSE_ID_STARK:
                mRecyclerView.swapAdapter(getCharactersAdapter(ConstantManager.HOUSE_ID_STARK), false);
                break;

            case ConstantManager.HOUSE_ID_LANNISTER:
                mRecyclerView.swapAdapter(getCharactersAdapter(ConstantManager.HOUSE_ID_LANNISTER), false);
                break;

            case ConstantManager.HOUSE_ID_TARGARYEN:
                mRecyclerView.swapAdapter(getCharactersAdapter(ConstantManager.HOUSE_ID_TARGARYEN), false);
                break;
        }
    }


    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }


    @Override
    public void onUserItemClickListener(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        DataManager.getInstance().setDetailData(((CharactersAdapter)mRecyclerView.getAdapter()).getList().get(position));
        startActivity(detailIntent);
    }


    private CharactersAdapter getCharactersAdapter(int houseId) {

        CharactersAdapter charactersAdapter = null;

        switch (houseId) {
            case ConstantManager.HOUSE_ID_STARK:
                charactersAdapter = new CharactersAdapter(R.drawable.stark_icon, (ArrayList<Character>) DataManager.getInstance().getHouses().get(1).getSwornMembers(), this);
                break;

            case ConstantManager.HOUSE_ID_LANNISTER:
                charactersAdapter = new CharactersAdapter(R.drawable.lannister_icon, (ArrayList<Character>) DataManager.getInstance().getHouses().get(0).getSwornMembers(), this);
                break;

            case ConstantManager.HOUSE_ID_TARGARYEN:
                charactersAdapter = new CharactersAdapter(R.drawable.targaryen_icon, (ArrayList<Character>) DataManager.getInstance().getHouses().get(2).getSwornMembers(), this);
                break;
        }
        return charactersAdapter;
    }

    private void setupNavigationDrawer() {

        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener()
                {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        item.setChecked(true);
                        mNavigationDrawer.closeDrawer(GravityCompat.START);

                        switch (item.getItemId()) {
                            case R.id.stark_item :
                                mTabLayout.getTabAt(0).select();
                                break;
                            case R.id.lannister_item :
                                mTabLayout.getTabAt(1).select();
                                break;
                            case R.id.targaryen_item :
                                mTabLayout.getTabAt(2).select();
                                break;
                        }
                        return false;
                    }
                }
        );
    }


    private void setUpToolbar() {
        setSupportActionBar(mToolbarLayout);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
