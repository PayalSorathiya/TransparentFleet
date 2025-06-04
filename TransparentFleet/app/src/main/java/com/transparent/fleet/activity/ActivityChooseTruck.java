package com.transparent.fleet.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.squareup.picasso.Picasso;
import com.transparent.fleet.BaseAppClass;
import com.transparent.fleet.R;
import com.transparent.fleet.adapter.TrailerAdapter;
import com.transparent.fleet.basecomponent.BaseActivity;
import com.transparent.fleet.callbackClick.OnRecyclerItemClick;
import com.transparent.fleet.databinding.ActivityChooseTrailerBinding;
import com.transparent.fleet.databinding.ActivityChooseTruckBinding;
import com.transparent.fleet.model.TrailerListModel;
import com.transparent.fleet.model.TrailerModel;
import com.transparent.fleet.model.TruckModel;

import java.util.ArrayList;


public class ActivityChooseTruck extends BaseActivity implements View.OnClickListener {
    public static final String TAG = "Download";
    public static TruckModel model;
    private ActivityChooseTruckBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setModelAndBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_choose_truck);
//        model = new TruckModel();
        binding.setTruckModel(model);
    }

    @Override
    public void setToolBar() {
        binding.included.imgProfile.setVisibility(View.VISIBLE);
        binding.included.imgMore.setVisibility(View.GONE);

    }

    @Override
    public void initViews() {

        loadImage();

        binding.txtRadioTruck.setOnClickListener(this);
        binding.txtContinue.setOnClickListener(this);
        binding.userName.setText(BaseAppClass.getPreferences().getUserName());


    }

    private void loadImage() {
        Picasso.with(ActivityChooseTruck.this)
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
                BaseAppClass.getPreferences().saveTruckId(model.getId());
                if (model.isSelected()) {
                    Intent intent = new Intent(ActivityChooseTruck.this, ActivityChooseTrailer.class);
                    startActivity(intent);
                    finish();
                }
                break;


        }
    }


}
