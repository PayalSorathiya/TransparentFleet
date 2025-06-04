package com.transparent.fleet.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.transparent.fleet.R;
import com.transparent.fleet.adapter.ViewPagerAdapter;
import com.transparent.fleet.basecomponent.BaseActivity;
import com.transparent.fleet.callbackClick.OpenFragment;
import com.transparent.fleet.databinding.ActivityCarhistoryBinding;
import com.transparent.fleet.databinding.ActivityHistoryBinding;
import com.transparent.fleet.fragment.FragmentCarHistory;
import com.transparent.fleet.fragment.FragmentTrailerHistory;
import com.transparent.fleet.fragment.FragmentTruckHistory;
import com.transparent.fleet.model.HistoryModel;
import com.transparent.fleet.util.CustomTextView;


public class CarHistoryActivity extends BaseActivity implements View.OnClickListener, OpenFragment {
    public static final String TAG = "HistoryActivity";
    public HistoryModel model;
    private ActivityCarhistoryBinding binding;
    ViewPagerAdapter mAdapter;


    private FragmentCarHistory fragmentCarHistory;

    int pos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    }

    @Override
    public void setModelAndBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_carhistory);
        model = new HistoryModel();
        binding.setHistoryModel(model);

    }

    @Override
    public void setToolBar() {


    }

    @Override
    public void initViews() {
        binding.imgClose.setOnClickListener(this);
        setupViewPager();


    }


    private void createTabIconsCar() {

        String[] tabName = {getString(R.string.car)};
        for (int i = 0; i < binding.carTab.getTabCount(); i++) {
            //noinspection ConstantConditions
            CustomTextView tv = (CustomTextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
            binding.carTab.getTabAt(i).setCustomView(tv);
//            binding.tabTruck.setVisibility(View.GONE);
//            binding.tabTrailer.setVisibility(View.GONE);
            tv.setText(tabName[i]);
            tv.setTextColor(binding.carTab.isSelected() ? getResources().getColor(R.color.darkPink) :
                    getResources().getColor(R.color.blueBg));

        }

    }

    /**
     * Adding fragments to ViewPager
     */
    private void setupViewPager() {

        binding.viewpager.setVisibility(View.VISIBLE);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        fragmentCarHistory = new FragmentCarHistory();
        mAdapter.addFragment(fragmentCarHistory, getString(R.string.car));
        binding.carTab.setupWithViewPager(binding.viewpager);
        binding.viewpager.setAdapter(mAdapter);
        binding.carTab.setupWithViewPager(binding.viewpager);
        createTabIconsCar();

        binding.carTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewpager.setCurrentItem(tab.getPosition(), true);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {
                binding.carTab.getTabAt(position).select();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    @Override
    public void closeActivity() {
        finish();
//        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgClose:
                closeActivity();
                break;


        }
    }


    @Override
    public void addNewFragment(Fragment fragToShow, Fragment fragToHide, String TAG) {

    }

    @Override
    public void setTitleAndToolbar(String title, boolean isBack, boolean isSearch, boolean isSetting, boolean isLocation, boolean isCart, String otherText) {

    }

    @Override
    public void setToolbarHeight(int height) {

    }

    @Override
    public void setToolbarMenuIcon(int icon) {

    }

    @Override
    public void selectedData() {

    }
}
