package com.transparent.fleet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.transparent.fleet.BaseAppClass;
import com.transparent.fleet.R;
import com.transparent.fleet.ResponceModel.AddFuelTruckData;
import com.transparent.fleet.ResponceModel.LoginData;
import com.transparent.fleet.basecomponent.BaseActivity;
import com.transparent.fleet.databinding.ActivityAddfuelTruckBinding;
import com.transparent.fleet.databinding.ActivityFuelstationSelactionBinding;
import com.transparent.fleet.databinding.ActivityVehicleSelactionBinding;
import com.transparent.fleet.model.AddFuelInTruckModel;
import com.transparent.fleet.model.AddFuelInTruckModel;
import com.transparent.fleet.net.RetrofitClient;
import com.transparent.fleet.util.Constants;
import com.transparent.fleet.util.CustomEditText;
import com.transparent.fleet.util.Utility;
import com.transparent.fleet.util.Validator;

import retrofit.RetrofitError;
import retrofit.client.Response;


public class ActivityAddFuelInTruck extends BaseActivity implements View.OnClickListener {
    public static final String TAG = "ActivityAddFuelInTruck";
    public AddFuelInTruckModel model;
    private ActivityAddfuelTruckBinding binding;

    String fuelStationId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    }

    @Override
    public void setModelAndBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_addfuel_truck);
        model = new AddFuelInTruckModel();
        binding.setAddFuelInTruckModel(model);
    }

    @Override
    public void setToolBar() {

    }

    @Override
    public void initViews() {
        String dummyKm = String.valueOf(BaseAppClass.getPreferences().getUserKm()).replace('.',',');
        binding.txtUserKm.setText("Last fueled at: " + dummyKm + " KMs");
        fuelStationId = getIntent().getStringExtra(ActivityVehicleSelaction.FULESTATIONID);
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
                    AddFuelInTruck();

                break;
            case R.id.imgBack:
            case R.id.imgClose:
                closeActivity();
                break;

        }
    }


    private boolean validateInput() {
        if (Utility.isNotNull(binding.ltrFuel.getText().toString())) {
            if (Utility.isNotNull(binding.txtKm.getText().toString())) {
                if (Long.parseLong(binding.txtKm.getText().toString()) > BaseAppClass.getPreferences().getUserKm()) {


                    return true;
                } else
                    Toast.makeText(ActivityAddFuelInTruck.this, "Please Enter Correct KM", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(ActivityAddFuelInTruck.this, "Please Enter KM", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(ActivityAddFuelInTruck.this, "Please Enter leter", Toast.LENGTH_SHORT).show();
        return false;
    }


    public void AddFuelInTruck() {

        if (Utility.checkInternetConnection(ActivityAddFuelInTruck.this)) {
            RetrofitClient.getInstance().getRestOkClient().
                    addTruckFuelApi(BaseAppClass.getPreferences().getTruckId(),
                            fuelStationId,
                            binding.ltrFuel.getText().toString(),
                            binding.txtKm.getText().toString(),
                            callback);
        } else {
            Toast.makeText(this, getString(R.string.noInternet), Toast.LENGTH_SHORT).show();

        }
    }

    private final retrofit.Callback callback = new retrofit.Callback() {
        @Override
        public void success(Object object, Response response) {
            AddFuelTruckData addFuelTruckData = (AddFuelTruckData) object;

            if (addFuelTruckData.getStatus().equalsIgnoreCase("success")) {
                Intent intent = new Intent(ActivityAddFuelInTruck.this, ActivityTruckCompleted.class);
                intent.putExtra(ActivityTruckCompleted.LITRES, addFuelTruckData.getLiters_fueled());
                intent.putExtra(ActivityTruckCompleted.DATE, addFuelTruckData.getOn_date());
                intent.putExtra(ActivityTruckCompleted.KM, addFuelTruckData.getTotal_km());
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(ActivityAddFuelInTruck.this, addFuelTruckData.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void failure(RetrofitError error) {

            Log.e("error", error.getMessage());
        }
    };


}
