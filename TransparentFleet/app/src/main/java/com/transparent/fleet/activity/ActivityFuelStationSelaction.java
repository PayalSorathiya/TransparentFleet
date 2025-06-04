package com.transparent.fleet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.transparent.fleet.R;
import com.transparent.fleet.ResponceModel.FuelStationData;
import com.transparent.fleet.ResponceModel.InspectionData;
import com.transparent.fleet.adapter.FuelStationSelactionAdapter;
import com.transparent.fleet.adapter.SubStationSelactionAdapter;
import com.transparent.fleet.basecomponent.BaseActivity;
import com.transparent.fleet.callbackClick.OnRecyclerItemClick;
import com.transparent.fleet.databinding.ActivityChooseTruckBinding;
import com.transparent.fleet.databinding.ActivityFuelstationSelactionBinding;
import com.transparent.fleet.databinding.ActivitySubstationSelactionBinding;
import com.transparent.fleet.model.FuelStationListModel;
import com.transparent.fleet.model.FuelStationModel;
import com.transparent.fleet.model.InspectionModel;
import com.transparent.fleet.model.SubStationModel;
import com.transparent.fleet.net.RetrofitClient;
import com.transparent.fleet.util.Utility;

import java.util.ArrayList;

import retrofit.RetrofitError;
import retrofit.client.Response;


public class ActivityFuelStationSelaction extends BaseActivity implements View.OnClickListener {
    public static final String TAG = "Download";
    public FuelStationListModel model;
    private ActivityFuelstationSelactionBinding binding;
    int selectedPos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    }

    @Override
    public void setModelAndBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fuelstation_selaction);
        model = new FuelStationListModel();
        model.setArrayList(new ArrayList<FuelStationModel>());
        binding.setFuelStationListModel(model);
    }

    @Override
    public void setToolBar() {

    }

    @Override
    public void initViews() {
        binding.txtContinue.setOnClickListener(this);
        binding.imgClose.setOnClickListener(this);

        initRecycler();
        fuelStationApi();
    }

    /*
     *  initialize Reacycler view
     */
    private void initRecycler() {
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        binding.recyclerView.setAdapter(new FuelStationSelactionAdapter(ActivityFuelStationSelaction.this,
                model.getArrayList(), new OnRecyclerItemClick() {
            @Override
            public void onClick(int position, int type) {
                for (int i = 0; i < model.getArrayList().size(); i++) {
                    model.getArrayList().get(i).setSelected(false);
                }
                model.getArrayList().get(position).setSelected(true);
                btnEnable();
                if (position == 0) {
                    model.setDepotSelection(true);
                    model.setOtherSelaction(false);
                } else {
                    model.setOtherSelaction(true);
                    model.setDepotSelection(false);
                }
                selectedPos = position;
            }
        }));

    }

    private void btnEnable() {
        int count = 0;
        if (model.getArrayList().size() > 0) {
            for (int i = 0; i < model.getArrayList().size(); i++) {
                if (model.getArrayList().get(i).isSelected()) {
                    count++;
                }
            }
        }
        if (count >= 1)
            model.setBtnEnable(true);

    }

    public void fuelStationApi() {
        model.setApiCallActive(true);
        binding.progressBar.setVisibility(View.VISIBLE);

        if (Utility.checkInternetConnection(ActivityFuelStationSelaction.this)) {
            RetrofitClient.getInstance().getRestOkClient().
                    fuelStationApi("", callback);
        } else {
            model.setApiCallActive(false);
            binding.progressBar.setVisibility(View.GONE);
            binding.noData.setText("Please Check your internet connection");
            notyFyDat();

        }
    }

    private final retrofit.Callback callback = new retrofit.Callback() {
        @Override
        public void success(Object object, Response response) {
            FuelStationData fuelStationData = (FuelStationData) object;
            binding.progressBar.setVisibility(View.GONE);
            FuelStationModel fuelStationModelel;
            if (fuelStationData.getStatus().equalsIgnoreCase("success")) {
                for (int i = 0; i < fuelStationData.getFuel_stations().size(); i++) {
                    fuelStationModelel = new FuelStationModel();
                    fuelStationModelel.setName(fuelStationData.getFuel_stations().get(i).getName());
                    fuelStationModelel.setId(fuelStationData.getFuel_stations().get(i).getId());
                    model.getArrayList().add(fuelStationModelel);
                }
            } else {
                Toast.makeText(ActivityFuelStationSelaction.this, fuelStationData.getMessage(), Toast.LENGTH_SHORT).show();
            }


            binding.recyclerView.getAdapter().notifyDataSetChanged();
            notyFyDat();

        }

        @Override
        public void failure(RetrofitError error) {
            binding.progressBar.setVisibility(View.GONE);
            model.setApiCallActive(false);
            Log.e("error", error.getMessage());
        }
    };

    private void notyFyDat() {
        if (model.getArrayList().size() > 0) {
            binding.recyclerView.setVisibility(View.VISIBLE);
            binding.noData.setVisibility(View.GONE);
        } else {
            binding.recyclerView.setVisibility(View.GONE);
            binding.noData.setVisibility(View.VISIBLE);
            binding.noData.setText(getString(R.string.noDataFound));

        }
    }

    @Override
    public void closeActivity() {
        finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgClose:
                closeActivity();
                break;

            case R.id.txtContinue:
                if (model.isBtnEnable()) {
                    Intent intent = new Intent(ActivityFuelStationSelaction.this, ActivitySubStationSelaction.class);
                    intent.putExtra(ActivityVehicleSelaction.FULESTATIONID, model.getArrayList().get(selectedPos).getId());
                    startActivity(intent);
                    finish();
                }
                break;


        }
    }


}
