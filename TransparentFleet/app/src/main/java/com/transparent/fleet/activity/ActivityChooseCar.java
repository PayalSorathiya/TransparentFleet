package com.transparent.fleet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.squareup.picasso.Picasso;
import com.transparent.fleet.BaseAppClass;
import com.transparent.fleet.R;
import com.transparent.fleet.ResponceModel.HomeData;
import com.transparent.fleet.basecomponent.BaseActivity;
import com.transparent.fleet.databinding.ActivityChooseCarBinding;
import com.transparent.fleet.databinding.ActivityChooseTruckBinding;
import com.transparent.fleet.fragment.SlideFragment;
import com.transparent.fleet.model.CarModel;
import com.transparent.fleet.net.RetrofitClient;
import com.transparent.fleet.util.Constants;
import com.transparent.fleet.util.CustomProgressDialog;
import com.transparent.fleet.util.Utility;

import retrofit.RetrofitError;
import retrofit.client.Response;


public class ActivityChooseCar extends BaseActivity implements View.OnClickListener {
    public static final String TAG = "Download";
    public static CarModel model;
    private ActivityChooseCarBinding binding;
    CustomProgressDialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setModelAndBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_choose_car);
//        model = new CarModel();
        binding.setCarModel(model);
    }

    @Override
    public void setToolBar() {
        binding.included.imgProfile.setVisibility(View.VISIBLE);
        binding.included.imgMore.setVisibility(View.GONE);

    }

    @Override
    public void initViews() {
        dialog = new CustomProgressDialog(Constants.PROGRESS_IMAGE, this).createProgressBar();

        loadImage();

        binding.txtRadioTruck.setOnClickListener(this);
        binding.txtContinue.setOnClickListener(this);
        binding.userName.setText(BaseAppClass.getPreferences().getUserName());


    }

    private void loadImage() {
        Picasso.with(ActivityChooseCar.this)
                .load(BaseAppClass.getPreferences().getUserImage())
                .placeholder(R.drawable.dummyuser)
                .into(binding.included.imgProfile);

    }

    @Override
    public void closeActivity() {
        finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtRadioTruck:
                model.setSelected(model.isSelected() ? false : true);
                break;
            case R.id.txtContinue:
                BaseAppClass.getPreferences().saveCarId(model.getId());
                if (model.isSelected()) {
                    BaseAppClass.getPreferences().saveCarId(model.getId());
                    homeDataAPi();
                }
                break;


        }
    }


    public void homeDataAPi() {
        dialog.show();
        dialog.setCancelable(false);
        if (Utility.checkInternetConnection(ActivityChooseCar.this)) {
            RetrofitClient.getInstance().getRestOkClient().
                    homeDataAPi("",
                            "",
                            BaseAppClass.getPreferences().getCarId(),
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

            HomeData homeData = (HomeData) object;

            if (homeData.getStatus().equalsIgnoreCase("success")) {
                BaseAppClass.getPreferences().saveFleetAvg(homeData.getFleet_average());
                BaseAppClass.getPreferences().saveUserAvg(homeData.getYour_average());
                SlideFragment.PAGE_TITLES = homeData.getNotifications();

                Intent intent = new Intent(ActivityChooseCar.this, ActivityHome.class);
                startActivity(intent);
                finish();

            } else {
                dialog.hide();
                Toast.makeText(ActivityChooseCar.this, homeData.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void failure(RetrofitError error) {
            dialog.hide();
            Toast.makeText(ActivityChooseCar.this, "Something Wrong Please Try Again", Toast.LENGTH_SHORT).show();

            Log.e("error", error.getMessage());
        }
    };


}
