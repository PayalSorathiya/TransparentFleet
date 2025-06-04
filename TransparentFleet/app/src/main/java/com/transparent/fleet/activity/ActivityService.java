package com.transparent.fleet.activity;

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
import com.transparent.fleet.ResponceModel.ServiceData;
import com.transparent.fleet.adapter.InspectionAdapter;
import com.transparent.fleet.adapter.ServiceAdapter;
import com.transparent.fleet.basecomponent.BaseActivity;
import com.transparent.fleet.callbackClick.OnRecyclerItemClick;
import com.transparent.fleet.databinding.ActivityInspectionBinding;
import com.transparent.fleet.databinding.ActivityServiceBinding;
import com.transparent.fleet.databinding.ActivityServiceBindingImpl;
import com.transparent.fleet.model.ServiceListModel;
import com.transparent.fleet.model.ServiceModel;
import com.transparent.fleet.model.ServiceListModel;
import com.transparent.fleet.model.ServiceModel;
import com.transparent.fleet.net.RetrofitClient;
import com.transparent.fleet.util.Utility;

import java.util.ArrayList;

import retrofit.RetrofitError;
import retrofit.client.Response;


public class ActivityService extends BaseActivity implements View.OnClickListener {
    public static final String TAG = "ActivityInspection";
    public ServiceListModel model;
    private ActivityServiceBinding binding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    }

    @Override
    public void setModelAndBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_service);
        model = new ServiceListModel();
        model.setArrayList(new ArrayList<ServiceModel>());
        model.setLatestArrayList(new ArrayList<ServiceModel>());
        binding.setServiceListModel(model);
    }

    @Override
    public void setToolBar() {

    }

    @Override
    public void initViews() {

        OnCLick();
        initRecycler();
        initlatestRecycler();
        serviceApi();

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
        binding.recyclerView.setAdapter(new ServiceAdapter(ActivityService.this,
                model.getArrayList(), new OnRecyclerItemClick() {
            @Override
            public void onClick(int position, int type) {

            }
        }));

    }

    /*
     *  initialize Reacycler view
     */
    private void initlatestRecycler() {
        binding.letestrecyclerView.setHasFixedSize(true);
        binding.letestrecyclerView.stopNestedScroll();
        binding.letestrecyclerView.setNestedScrollingEnabled(false);

        binding.letestrecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        binding.letestrecyclerView.setAdapter(new ServiceAdapter(ActivityService.this,
                model.getLatestArrayList(), new OnRecyclerItemClick() {
            @Override
            public void onClick(int position, int type) {

            }
        }));

    }


    public void serviceApi() {
        model.setApiCallActive(true);
        binding.progressBar.setVisibility(View.VISIBLE);

        if (Utility.checkInternetConnection(ActivityService.this)) {
            if (BaseAppClass.getPreferences().isTruck()) {
                RetrofitClient.getInstance().getRestOkClient().
                        serviceApi(BaseAppClass.getPreferences().getTrailerId(),
                                BaseAppClass.getPreferences().getTruckId(),
                                "",
                                callback);
            } else {
                RetrofitClient.getInstance().getRestOkClient().
                        serviceApi("",
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
            ServiceData serviceData = (ServiceData) object;
            binding.progressBar.setVisibility(View.GONE);
            ServiceModel serviceModel;
            if (serviceData.getAll_truck_services() != null && !serviceData.getAll_truck_services().isEmpty()) {
                for (int i = 0; i < serviceData.getAll_truck_services().size(); i++) {
                    serviceModel = new ServiceModel();
                    serviceModel.setName(serviceData.getAll_truck_services().get(i).getName());
                    serviceModel.setDescription(serviceData.getAll_truck_services().get(i).getDescription());
                    serviceModel.setDate(serviceData.getAll_truck_services().get(i).getDate_limit());
                    serviceModel.setStatus(serviceData.getAll_truck_services().get(i).getStatus());
                    serviceModel.setType("1");

                    model.getArrayList().add(serviceModel);


                }
            }
            if (serviceData.getAll_trailer_services() != null && !serviceData.getAll_trailer_services().isEmpty()) {
                for (int i = 0; i < serviceData.getAll_trailer_services().size(); i++) {
                    serviceModel = new ServiceModel();
                    serviceModel.setName(serviceData.getAll_trailer_services().get(i).getName());
                    serviceModel.setDescription(serviceData.getAll_trailer_services().get(i).getDescription());
                    serviceModel.setDate(serviceData.getAll_trailer_services().get(i).getDate_limit());
                    serviceModel.setStatus(serviceData.getAll_trailer_services().get(i).getStatus());
                    serviceModel.setType("2");

                    model.getArrayList().add(serviceModel);
                }
            }
            if (serviceData.getAll_car_services() != null && !serviceData.getAll_car_services().isEmpty()) {
                for (int i = 0; i < serviceData.getAll_car_services().size(); i++) {
                    serviceModel = new ServiceModel();
                    serviceModel.setName(serviceData.getAll_car_services().get(i).getName());
                    serviceModel.setDescription(serviceData.getAll_car_services().get(i).getDescription());
                    serviceModel.setDate(serviceData.getAll_car_services().get(i).getDate_limit());
                    serviceModel.setStatus(serviceData.getAll_car_services().get(i).getStatus());
                    serviceModel.setType("3");

                    model.getArrayList().add(serviceModel);
                }
            }
            if (serviceData.getTruck_services_today() != null && !serviceData.getTruck_services_today().isEmpty()) {
                for (int i = 0; i < serviceData.getTruck_services_today().size(); i++) {
                    serviceModel = new ServiceModel();
                    serviceModel.setName(serviceData.getTruck_services_today().get(i).getName());
                    serviceModel.setDescription(serviceData.getTruck_services_today().get(i).getDescription());
                    serviceModel.setDate(serviceData.getTruck_services_today().get(i).getDate_limit());
                    serviceModel.setStatus("4");
                    serviceModel.setType("1");

                    model.getLatestArrayList().add(serviceModel);

                }
            }
            if (serviceData.getTrailer_services_today() != null && !serviceData.getTrailer_services_today().isEmpty()) {
                for (int i = 0; i < serviceData.getTrailer_services_today().size(); i++) {
                    serviceModel = new ServiceModel();
                    serviceModel.setName(serviceData.getTrailer_services_today().get(i).getName());
                    serviceModel.setDescription(serviceData.getTrailer_services_today().get(i).getDescription());
                    serviceModel.setDate(serviceData.getTrailer_services_today().get(i).getDate_limit());
                    serviceModel.setStatus("4");
                    serviceModel.setType("2");

                    model.getLatestArrayList().add(serviceModel);

                }
            }
            if (serviceData.getCar_services_today() != null && !serviceData.getCar_services_today().isEmpty()) {
                for (int i = 0; i < serviceData.getCar_services_today().size(); i++) {
                    serviceModel = new ServiceModel();
                    serviceModel.setName(serviceData.getCar_services_today().get(i).getName());
                    serviceModel.setDescription(serviceData.getCar_services_today().get(i).getDescription());
                    serviceModel.setDate(serviceData.getCar_services_today().get(i).getDate_limit());
                    serviceModel.setStatus("4");
                    serviceModel.setType("3");

                    model.getLatestArrayList().add(serviceModel);

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
            binding.txtService.setVisibility(View.VISIBLE);
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
            binding.latestData.setText(getString(R.string.noServiceFound));

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
