package com.transparent.fleet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.transparent.fleet.BaseAppClass;
import com.transparent.fleet.R;
import com.transparent.fleet.ResponceModel.InspectionData;
import com.transparent.fleet.adapter.InspectionAdapter;
import com.transparent.fleet.basecomponent.BaseActivity;
import com.transparent.fleet.callbackClick.OnRecyclerItemClick;
import com.transparent.fleet.databinding.ActivityInspectionBinding;
import com.transparent.fleet.model.InspectionListModel;
import com.transparent.fleet.model.InspectionModel;
import com.transparent.fleet.net.RetrofitClient;
import com.transparent.fleet.util.Utility;

import java.util.ArrayList;

import retrofit.RetrofitError;
import retrofit.client.Response;


public class ActivityInspection extends BaseActivity implements View.OnClickListener {
    public static final String TAG = "ActivityInspection";
    public InspectionListModel model;
    private ActivityInspectionBinding binding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    }

    @Override
    public void setModelAndBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_inspection);
        model = new InspectionListModel();
        model.setArrayList(new ArrayList<InspectionModel>());
        model.setLatestArrayList(new ArrayList<InspectionModel>());
        binding.setInspectionListModel(model);
    }

    @Override
    public void setToolBar() {

    }

    @Override
    public void initViews() {
        binding.scrollView.scrollTo(0, binding.scrollView.getBottom());

        OnCLick();
        initRecycler();
        initlatestRecycler();
        inspectionApi();

    }

    private void OnCLick() {
        binding.imgClose.setOnClickListener(this);
    }

    /*
     *  initialize Reacycler view
     */
    private void initRecycler() {
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.stopNestedScroll();
        binding.recyclerView.setNestedScrollingEnabled(false);

        binding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        binding.recyclerView.setAdapter(new InspectionAdapter(ActivityInspection.this,
                model.getArrayList(), new OnRecyclerItemClick() {
            @Override
            public void onClick(int position, int type) {

            }
        }));

    } /*
     *  initialize Reacycler view
     */

    private void initlatestRecycler() {
        binding.letestrecyclerView.setHasFixedSize(true);
        binding.letestrecyclerView.stopNestedScroll();
        binding.letestrecyclerView.setNestedScrollingEnabled(false);
        binding.letestrecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        binding.letestrecyclerView.setAdapter(new InspectionAdapter(ActivityInspection.this,
                model.getLatestArrayList(), new OnRecyclerItemClick() {
            @Override
            public void onClick(int position, int type) {

            }
        }));

    }


    public void inspectionApi() {
        model.setApiCallActive(true);
        binding.progressBar.setVisibility(View.VISIBLE);

        if (Utility.checkInternetConnection(ActivityInspection.this)) {
            if (BaseAppClass.getPreferences().isTruck()) {
                RetrofitClient.getInstance().getRestOkClient().
                        inspectionApi(BaseAppClass.getPreferences().getTrailerId(),
                                BaseAppClass.getPreferences().getTruckId(),
                                "",
                                callback);
            } else {
                RetrofitClient.getInstance().getRestOkClient().
                        inspectionApi("",
                                "",
                                BaseAppClass.getPreferences().getCarId(),
                                callback);

            }
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
            InspectionData inspectionData = (InspectionData) object;
            binding.progressBar.setVisibility(View.GONE);
            InspectionModel inspectionModel;
            if (inspectionData.getAll_truck_inspections() != null && !inspectionData.getAll_truck_inspections().isEmpty()) {
                for (int i = 0; i < inspectionData.getAll_truck_inspections().size(); i++) {
                    inspectionModel = new InspectionModel();
                    inspectionModel.setName(inspectionData.getAll_truck_inspections().get(i).getName());
                    inspectionModel.setDescription(inspectionData.getAll_truck_inspections().get(i).getDescription());
                    inspectionModel.setDate(inspectionData.getAll_truck_inspections().get(i).getDate_limit());
                    inspectionModel.setStatus(inspectionData.getAll_truck_inspections().get(i).getStatus());
                    inspectionModel.setType("1");
                    model.getArrayList().add(inspectionModel);
                }
            }
            if (inspectionData.getAll_trailer_inspections() != null && !inspectionData.getAll_trailer_inspections().isEmpty()) {
                for (int i = 0; i < inspectionData.getAll_trailer_inspections().size(); i++) {
                    inspectionModel = new InspectionModel();
                    inspectionModel.setName(inspectionData.getAll_trailer_inspections().get(i).getName());
                    inspectionModel.setDescription(inspectionData.getAll_trailer_inspections().get(i).getDescription());
                    inspectionModel.setDate(inspectionData.getAll_trailer_inspections().get(i).getDate_limit());
                    inspectionModel.setStatus(inspectionData.getAll_trailer_inspections().get(i).getStatus());
                    inspectionModel.setType("2");

                    model.getArrayList().add(inspectionModel);

                }
            }if (inspectionData.getAll_car_inspections() != null && !inspectionData.getAll_car_inspections().isEmpty()) {
                for (int i = 0; i < inspectionData.getAll_car_inspections().size(); i++) {
                    inspectionModel = new InspectionModel();
                    inspectionModel.setName(inspectionData.getAll_car_inspections().get(i).getName());
                    inspectionModel.setDescription(inspectionData.getAll_car_inspections().get(i).getDescription());
                    inspectionModel.setDate(inspectionData.getAll_car_inspections().get(i).getDate_limit());
                    inspectionModel.setStatus(inspectionData.getAll_car_inspections().get(i).getStatus());
                    inspectionModel.setType("3");

                    model.getArrayList().add(inspectionModel);

                }
            }
            if (inspectionData.getTruck_inspections_today() != null && !inspectionData.getTruck_inspections_today().isEmpty()) {
                for (int i = 0; i < inspectionData.getTruck_inspections_today().size(); i++) {
                    inspectionModel = new InspectionModel();
                    inspectionModel.setName(inspectionData.getTruck_inspections_today().get(i).getName());
                    inspectionModel.setDescription(inspectionData.getTruck_inspections_today().get(i).getDescription());
                    inspectionModel.setDate(inspectionData.getTruck_inspections_today().get(i).getDate_limit());
                    inspectionModel.setStatus("4");
                    inspectionModel.setType("1");

                    model.getLatestArrayList().add(inspectionModel);

                }
            }
            if (inspectionData.getTrailer_inspections_today() != null && !inspectionData.getTrailer_inspections_today().isEmpty()) {
                for (int i = 0; i < inspectionData.getTrailer_inspections_today().size(); i++) {
                    inspectionModel = new InspectionModel();
                    inspectionModel.setName(inspectionData.getTrailer_inspections_today().get(i).getName());
                    inspectionModel.setDescription(inspectionData.getTrailer_inspections_today().get(i).getDescription());
                    inspectionModel.setDate(inspectionData.getTrailer_inspections_today().get(i).getDate_limit());
                    inspectionModel.setStatus("4");
                    inspectionModel.setType("2");

                    model.getLatestArrayList().add(inspectionModel);

                }
            }
            if (inspectionData.getCar_inspections_today() != null && !inspectionData.getCar_inspections_today().isEmpty()) {
                for (int i = 0; i < inspectionData.getCar_inspections_today().size(); i++) {
                    inspectionModel = new InspectionModel();
                    inspectionModel.setName(inspectionData.getCar_inspections_today().get(i).getName());
                    inspectionModel.setDescription(inspectionData.getCar_inspections_today().get(i).getDescription());
                    inspectionModel.setDate(inspectionData.getCar_inspections_today().get(i).getDate_limit());
                    inspectionModel.setStatus("4");
                    inspectionModel.setType("3");

                    model.getLatestArrayList().add(inspectionModel);

                }
            }

            binding.recyclerView.getAdapter().notifyDataSetChanged();
            binding.letestrecyclerView.getAdapter().notifyDataSetChanged();
            notyFyDat();
            notyFyLatest();

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
            binding.txtInspection.setVisibility(View.VISIBLE);
            binding.noData.setVisibility(View.GONE);
        } else {
            binding.recyclerView.setVisibility(View.GONE);
            binding.noData.setVisibility(View.VISIBLE);
            binding.noData.setText(getString(R.string.noDataFound));

        }
    }

    private void notyFyLatest() {
        if (model.getLatestArrayList().size() > 0) {
            binding.letestrecyclerView.setVisibility(View.VISIBLE);
            binding.latestData.setVisibility(View.GONE);
        } else {
            binding.letestrecyclerView.setVisibility(View.GONE);
            binding.latestData.setVisibility(View.VISIBLE);
            binding.latestData.setText(getString(R.string.noInspectionFound));

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

        }
    }


}
