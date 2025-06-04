package com.transparent.fleet.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.transparent.fleet.R;
import com.transparent.fleet.adapter.CardAdapter;
import com.transparent.fleet.adapter.ViewPagerAdapter;
import com.transparent.fleet.basecomponent.BaseActivity;
import com.transparent.fleet.callbackClick.OnRecyclerItemClick;
import com.transparent.fleet.callbackClick.OpenFragment;
import com.transparent.fleet.databinding.ActivityViewDocumentBinding;
import com.transparent.fleet.fragment.FragmentDriver;
import com.transparent.fleet.fragment.FragmentTrailer;
import com.transparent.fleet.fragment.FragmentTruck;
import com.transparent.fleet.model.CardModel;
import com.transparent.fleet.model.DocumentListModel;
import com.transparent.fleet.model.DocumentModel;
import com.transparent.fleet.util.CustomTextView;

import java.util.ArrayList;


public class DocumentActivity extends BaseActivity implements View.OnClickListener , OpenFragment {
    public static final String TAG = "HistoryActivity";
    public static DocumentListModel model;
    private ActivityViewDocumentBinding binding;
    ViewPagerAdapter mAdapter;

    private FragmentTruck fragmentTruck;
    private FragmentTrailer fragmentTrailer;
    private FragmentDriver fragmentDriver;

    int pos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    }
    @Override
    public void setModelAndBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_document);
//        model = new DocumentListModel();
//        model.setArrayList(new ArrayList<DocumentModel>());
//        model.setCardModelArrayList(new ArrayList<CardModel>());
        binding.setDocumentListModel(model);

    }

    @Override
    public void setToolBar() {


    }

    @Override
    public void initViews() {

        pos = getIntent().getIntExtra(getString(R.string.position), 0);
        onClick();
        setupViewPager();
        initRecycler();
        notyFyDat();


    }
    private void onClick(){
        binding.imgClose.setOnClickListener(this);
    }


    private void createTabIcons() {

        String[] tabName = {getString(R.string.truck),
                getString(R.string.trailer), getString(R.string.driver)};
        for (int i = 0; i < binding.tabs.getTabCount(); i++) {
            //noinspection ConstantConditions
            CustomTextView tv = (CustomTextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
            binding.tabs.getTabAt(i).setCustomView(tv);
            tv.setText(tabName[i]);
            tv.setTextColor(binding.tabs.isSelected()? getResources().getColor(R.color.darkPink):
                    getResources().getColor(R.color.blueBg));

        }

    }

    /*
     *  initialize Reacycler view
     */
    private void initRecycler() {
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(DocumentActivity.this));

        binding.recyclerView.stopNestedScroll();
        binding.recyclerView.setNestedScrollingEnabled(false);
        binding.recyclerView.setAdapter(new CardAdapter(DocumentActivity.this,
                model.getCardModelArrayList(), new OnRecyclerItemClick() {
            @Override
            public void onClick(final int position, int type) {

            }
        }));

    }


    private void notyFyDat() {
        if (model.getCardModelArrayList().size() > 0) {
            binding.recyclerView.setVisibility(View.VISIBLE);
//            binding.noData.setVisibility(View.GONE);
        } else {
            binding.recyclerView.setVisibility(View.GONE);
//            binding.noData.setVisibility(View.VISIBLE);
//            binding.noData.setText(getString(R.string.noDataFound));

        }
    }

    /**
     * Adding fragments to ViewPager
     */
    private void setupViewPager() {

        binding.viewpager.setVisibility(View.VISIBLE);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        fragmentTruck = new FragmentTruck();
        fragmentTrailer = new FragmentTrailer();
        fragmentDriver = new FragmentDriver();

        mAdapter.addFragment(fragmentTruck, getString(R.string.truck));
        mAdapter.addFragment(fragmentTrailer, getString(R.string.trailer));
        mAdapter.addFragment(fragmentDriver, getString(R.string.driver));

        binding.viewpager.setAdapter(mAdapter);
        binding.tabs.setupWithViewPager(binding.viewpager);
        createTabIcons();


        binding.tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
                binding.tabs.getTabAt(position).select();

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
