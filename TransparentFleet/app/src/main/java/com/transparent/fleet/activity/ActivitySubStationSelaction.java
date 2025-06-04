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

import com.transparent.fleet.BaseAppClass;
import com.transparent.fleet.R;
import com.transparent.fleet.ResponceModel.FuelStationData;
import com.transparent.fleet.ResponceModel.SubStationData;
import com.transparent.fleet.adapter.SubStationSelactionAdapter;
import com.transparent.fleet.adapter.TrailerAdapter;
import com.transparent.fleet.basecomponent.BaseActivity;
import com.transparent.fleet.callbackClick.OnRecyclerItemClick;
import com.transparent.fleet.databinding.ActivityChooseTrailerBinding;
import com.transparent.fleet.databinding.ActivitySubstationSelactionBinding;
import com.transparent.fleet.model.FuelStationModel;
import com.transparent.fleet.model.SubStationListModel;
import com.transparent.fleet.model.SubStationModel;
import com.transparent.fleet.model.TrailerModel;
import com.transparent.fleet.net.RetrofitClient;
import com.transparent.fleet.util.Utility;

import java.util.ArrayList;

import retrofit.RetrofitError;
import retrofit.client.Response;


public class ActivitySubStationSelaction extends BaseActivity implements View.OnClickListener {
    public static final String TAG = "Download";
    public SubStationListModel model;
    private ActivitySubstationSelactionBinding binding;

    String fuelStationId;
    int selectedPos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    }

    @Override
    public void setModelAndBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_substation_selaction);
        model = new SubStationListModel();
        model.setArrayList(new ArrayList<SubStationModel>());
        binding.setSubStationListModel(model);
    }

    @Override
    public void setToolBar() {

    }

    @Override
    public void initViews() {
        fuelStationId = getIntent().getStringExtra(ActivityVehicleSelaction.FULESTATIONID);

        onClick();
        initRecycler();
        subStationApi();

    }

    private void onClick() {
        binding.txtContinue.setOnClickListener(this);
        binding.imgClose.setOnClickListener(this);

    }

    /*
     *  initialize Reacycler view
     */
    private void initRecycler() {
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        binding.recyclerView.setAdapter(new SubStationSelactionAdapter(ActivitySubStationSelaction.this,
                model.getArrayList(), new OnRecyclerItemClick() {
            @Override
            public void onClick(int position, int type) {
                for (int i = 0; i < model.getArrayList().size(); i++) {
                    model.getArrayList().get(i).setSelected(false);
                }
                model.getArrayList().get(position).setSelected(true);
                btnEnable();
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

    public void subStationApi() {
        model.setApiCallActive(true);
        binding.progressBar.setVisibility(View.VISIBLE);

        if (Utility.checkInternetConnection(ActivitySubStationSelaction.this)) {
            RetrofitClient.getInstance().getRestOkClient().
                    fuelSubStationApi(fuelStationId, callback);
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
            SubStationData subStationData = (SubStationData) object;
            binding.progressBar.setVisibility(View.GONE);
            SubStationModel subStationModel;
            if (subStationData.getStatus().equalsIgnoreCase("success")) {
                for (int i = 0; i < subStationData.getSub_fuel_stations().size(); i++) {
                    subStationModel = new SubStationModel();
                    subStationModel.setName(subStationData.getSub_fuel_stations().get(i).getName());
                    subStationModel.setId(subStationData.getSub_fuel_stations().get(i).getId());
                    model.getArrayList().add(subStationModel);
                }
            } else {
                Toast.makeText(ActivitySubStationSelaction.this, subStationData.getMessage(), Toast.LENGTH_SHORT).show();
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
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtContinue:
                if (model.isBtnEnable()) {
                    if (BaseAppClass.getPreferences().isTruck()) {
                        Intent intent = new Intent(ActivitySubStationSelaction.this, ActivityVehicleSelaction.class);
                        intent.putExtra(ActivityVehicleSelaction.FULESTATIONID, model.getArrayList().get(selectedPos).getId());
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Intent intent = new Intent(ActivitySubStationSelaction.this, ActivityAddFuelInCar.class);
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
