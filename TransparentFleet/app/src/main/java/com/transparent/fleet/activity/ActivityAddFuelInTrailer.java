package com.transparent.fleet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.transparent.fleet.BaseAppClass;
import com.transparent.fleet.R;
import com.transparent.fleet.ResponceModel.AddFuelTrailerData;
import com.transparent.fleet.ResponceModel.AddFuelTruckData;
import com.transparent.fleet.basecomponent.BaseActivity;
import com.transparent.fleet.databinding.ActivityAddfuelTrailerBinding;
import com.transparent.fleet.databinding.ActivityAddfuelTruckBinding;
import com.transparent.fleet.model.AddFuelInTrailerModel;
import com.transparent.fleet.model.AddFuelInTrailerModel;
import com.transparent.fleet.net.RetrofitClient;
import com.transparent.fleet.util.Utility;
import com.transparent.fleet.util.Validator;

import retrofit.RetrofitError;
import retrofit.client.Response;


public class ActivityAddFuelInTrailer extends BaseActivity implements View.OnClickListener {
    public static final String TAG = "ActivityAddFuelInTruck";
    public AddFuelInTrailerModel model;
    private ActivityAddfuelTrailerBinding binding;

    public static final String FULESTATIONID = "fuelstationId";

    String fuelStationId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    }

    @Override
    public void setModelAndBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_addfuel_trailer);
        model = new AddFuelInTrailerModel();
        binding.setAddFuelInTrailerModel(model);
    }

    @Override
    public void setToolBar() {

    }

    @Override
    public void initViews() {
        binding.txtUserHour.setText("Last fueled at: "+BaseAppClass.getPreferences().getUserHour() + " Hours");
        fuelStationId = getIntent().getStringExtra(FULESTATIONID);

        OnClick();


    }

    private void OnClick() {
        binding.txtContinue.setOnClickListener(this);
        binding.imgBack.setOnClickListener(this);
        binding.imgClose.setOnClickListener(this);
    }


    @Override
    public void closeActivity() {
        finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.txtContinue:
                if (validateInput())
                    AddFuelInTrailer();


                break;
            case R.id.imgBack:
            case R.id.imgClose:
                closeActivity();
                break;


        }
    }

    private boolean validateInput() {
        if (Utility.isNotNull(binding.ltrFuel.getText().toString())) {
            if (Utility.isNotNull(binding.txtHour.getText().toString())) {
                if (Long.parseLong(binding.txtHour.getText().toString()) > BaseAppClass.getPreferences().getUserHour()) {
                    return true;
                } else
                    Toast.makeText(ActivityAddFuelInTrailer.this, "Please Enter Correct Hour", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(ActivityAddFuelInTrailer.this, "Please Enter Hour", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(ActivityAddFuelInTrailer.this, "Please Enter leter", Toast.LENGTH_SHORT).show();
        return false;
    }


    public void AddFuelInTrailer() {

        if (Utility.checkInternetConnection(ActivityAddFuelInTrailer.this)) {
            RetrofitClient.getInstance().getRestOkClient().
                    addTrailerFuelApi(BaseAppClass.getPreferences().getTruckId(),
                            fuelStationId,
                            binding.ltrFuel.getText().toString(),
                            binding.txtHour.getText().toString(),
                            callback);
        } else {
            Toast.makeText(this, getString(R.string.noInternet), Toast.LENGTH_SHORT).show();

        }
    }

    private final retrofit.Callback callback = new retrofit.Callback() {
        @Override
        public void success(Object object, Response response) {
            AddFuelTrailerData addFuelTrailerData = (AddFuelTrailerData) object;

            if (addFuelTrailerData.getStatus().equalsIgnoreCase("success")) {
                Intent intent = new Intent(ActivityAddFuelInTrailer.this, ActivityTrailerCompleted.class);
                intent.putExtra(ActivityTrailerCompleted.LITRES, addFuelTrailerData.getLiters_fueled());
                intent.putExtra(ActivityTrailerCompleted.DATE, addFuelTrailerData.getOn_date());
                intent.putExtra(ActivityTrailerCompleted.HOUR, addFuelTrailerData.getTotal_hours());
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(ActivityAddFuelInTrailer.this, addFuelTrailerData.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void failure(RetrofitError error) {

            Log.e("error", error.getMessage());
        }
    };

}
