package com.transparent.fleet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.transparent.fleet.R;
import com.transparent.fleet.basecomponent.BaseActivity;
import com.transparent.fleet.databinding.ActivityVehicleSelactionBinding;
import com.transparent.fleet.model.VehicleModel;


public class ActivityVehicleSelaction extends BaseActivity implements View.OnClickListener {
    public static final String TAG = "Download";
    public VehicleModel model;
    private ActivityVehicleSelactionBinding binding;
    public static final String FULESTATIONID = "fuelstationId";

    String fuelStationId;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    }

    @Override
    public void setModelAndBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vehicle_selaction);
        model = new VehicleModel();
        binding.setVehicleModel(model);
    }

    @Override
    public void setToolBar() {

    }

    @Override
    public void initViews() {
        fuelStationId = getIntent().getStringExtra(ActivityVehicleSelaction.FULESTATIONID);

        binding.llTruck.setOnClickListener(this);
        binding.llTrailer.setOnClickListener(this);
        binding.txtContinue.setOnClickListener(this);
        binding.imgClose.setOnClickListener(this);


    }


    @Override
    public void closeActivity() {
        finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llTruck:
                model.setTruck(true);
                model.setTrailer(false);
                model.setBtnEnable(true);
                break;
            case R.id.llTrailer:
                model.setTruck(false);
                model.setTrailer(true);
                model.setBtnEnable(true);
                break;
            case R.id.txtContinue:
                if (model.isBtnEnable()) {
                    if (model.isTruck()) {
                        Intent intent = new Intent(ActivityVehicleSelaction.this, ActivityAddFuelInTruck.class);
                        intent.putExtra(ActivityVehicleSelaction.FULESTATIONID,fuelStationId);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(ActivityVehicleSelaction.this, ActivityAddFuelInTrailer.class);
                        intent.putExtra(ActivityVehicleSelaction.FULESTATIONID,fuelStationId);
                        startActivity(intent);
                        finish();
                    }
                }
                break;
            case R.id.imgClose:
                closeActivity();
                break;


        }
    }


}
