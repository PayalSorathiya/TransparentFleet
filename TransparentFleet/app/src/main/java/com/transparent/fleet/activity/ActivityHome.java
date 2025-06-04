package com.transparent.fleet.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.tabs.TabLayout;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.squareup.picasso.Picasso;
import com.transparent.fleet.BaseAppClass;
import com.transparent.fleet.R;
import com.transparent.fleet.ResponceModel.HomeData;
import com.transparent.fleet.ResponceModel.SuccessData;
import com.transparent.fleet.adapter.AutoScrollPagerAdapter;
import com.transparent.fleet.adapter.TrailerAdapter;
import com.transparent.fleet.basecomponent.BaseActivity;
import com.transparent.fleet.callbackClick.OnRecyclerItemClick;
import com.transparent.fleet.callbackClick.TwoButtonListener;
import com.transparent.fleet.databinding.ActivityChooseTrailerBinding;
import com.transparent.fleet.databinding.ActivityHomeBinding;
import com.transparent.fleet.fragment.SlideFragment;
import com.transparent.fleet.model.HomeModel;
import com.transparent.fleet.model.TrailerListModel;
import com.transparent.fleet.model.TrailerModel;
import com.transparent.fleet.net.RetrofitClient;
import com.transparent.fleet.util.AlertDialogAndIntents;
import com.transparent.fleet.util.AutoScrollViewPager;
import com.transparent.fleet.util.Constants;
import com.transparent.fleet.util.CustomProgressDialog;
import com.transparent.fleet.util.Utility;

import java.util.ArrayList;

import retrofit.RetrofitError;
import retrofit.client.Response;


public class ActivityHome extends BaseActivity implements View.OnClickListener {
    public static final String TAG = "Download";
    public HomeModel model;
    private ActivityHomeBinding binding;
    CustomProgressDialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setModelAndBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        model = new HomeModel();
        binding.setHomeModel(model);
    }

    @Override
    public void setToolBar() {
        binding.included.imgProfile.setVisibility(View.VISIBLE);
        binding.included.imgMore.setVisibility(View.GONE);
        binding.included.imgProfile.setOnClickListener(this);

    }

    @Override
    public void initViews() {
        dialog = new CustomProgressDialog(Constants.PROGRESS_IMAGE, this).createProgressBar();
        loadImage();
        OnClick();
        setUpPager();
        double flletAvg = Double.parseDouble(BaseAppClass.getPreferences().getFleetAvg());
        double userAvg = Double.parseDouble(BaseAppClass.getPreferences().getUserAvg());
        binding.txtFleetAvg.setText(String.format("%.2f", flletAvg));
        binding.txtYourAvg.setText(String.format("%.2f", userAvg));

    }

    private void OnClick() {
        binding.llAddFuel.setOnClickListener(this);
        binding.llFuelHistory.setOnClickListener(this);
        binding.llIncpection.setOnClickListener(this);
        binding.llService.setOnClickListener(this);
        binding.viewDoc.setOnClickListener(this);

    }

    private void loadImage() {

        Picasso.with(ActivityHome.this)
                .load(BaseAppClass.getPreferences().getUserImage())
                .placeholder(R.drawable.dummyuser)
                .into(binding.included.imgProfile);
    }

    private void setUpPager() {
        AutoScrollPagerAdapter autoScrollPagerAdapter =
                new AutoScrollPagerAdapter(getSupportFragmentManager());
        AutoScrollViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(autoScrollPagerAdapter);
        viewPager.startAutoScroll();
        viewPager.setInterval(2000);
        viewPager.setCycle(true);
    }


    @Override
    public void closeActivity() {
        finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llAddFuel:
                Intent intent = new Intent(ActivityHome.this, ActivityFuelStationSelaction.class);
                startActivity(intent);
                break;
            case R.id.llFuelHistory:
                if (BaseAppClass.getPreferences().isTruck()) {
                    Intent intentHistory = new Intent(ActivityHome.this, HistoryActivity.class);
                    intentHistory.putExtra(getString(R.string.position), 0);
                    startActivity(intentHistory);
                } else {
                    Intent intentHistory = new Intent(ActivityHome.this, CarHistoryActivity.class);
                    intentHistory.putExtra(getString(R.string.position), 0);
                    startActivity(intentHistory);

                }
                break;
            case R.id.llIncpection:
                Intent intentInspection = new Intent(ActivityHome.this, ActivityInspection.class);
                startActivity(intentInspection);
                break;
            case R.id.llService:
                Intent intentService = new Intent(ActivityHome.this, ActivityService.class);
                startActivity(intentService);
                break;
            case R.id.viewDoc:
                if (BaseAppClass.getPreferences().isTruck()) {

                    Intent intentDoc = new Intent(ActivityHome.this, DocumentActivity.class);
                    startActivity(intentDoc);
                } else {
                    Intent intentDoc = new Intent(ActivityHome.this, CarDocumentActivity.class);
                    startActivity(intentDoc);
                }
                break;
            case R.id.imgProfile:
                AlertDialogAndIntents.showLogoutPopUp(ActivityHome.this, new TwoButtonListener() {
                    @Override
                    public void OkClick() {
                        logOutAPi();

                    }

                    @Override
                    public void CanselClick() {

                    }
                });
                break;


        }
    }

    public void logOutAPi() {
        dialog.show();
        dialog.setCancelable(false);
        if (Utility.checkInternetConnection(ActivityHome.this)) {
            RetrofitClient.getInstance().getRestOkClient().
                    logoutApi("",
                            callback);
        } else {
            dialog.hide();
            Toast.makeText(this, getString(R.string.noInternet), Toast.LENGTH_SHORT).show();

        }
    }

    private final retrofit.Callback callback = new retrofit.Callback() {
        @Override
        public void success(Object object, Response response) {
            dialog.hide();

            SuccessData successData = (SuccessData) object;

            if (successData.getStatus().equalsIgnoreCase("success")) {
                BaseAppClass.getPreferences().clearUserPrefs();
                Intent intent = new Intent(ActivityHome.this, ActivityWelcome.class);
                startActivity(intent);
                finish();

            } else {
                dialog.hide();
                Toast.makeText(ActivityHome.this, successData.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void failure(RetrofitError error) {
            dialog.hide();
            Toast.makeText(ActivityHome.this, "Something Wrong Please Try Again", Toast.LENGTH_SHORT).show();
            Log.e("error", error.getMessage());
        }
    };


}
