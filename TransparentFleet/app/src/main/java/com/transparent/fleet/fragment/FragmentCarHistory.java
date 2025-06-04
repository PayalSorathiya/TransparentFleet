package com.transparent.fleet.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.transparent.fleet.BaseAppClass;
import com.transparent.fleet.R;
import com.transparent.fleet.ResponceModel.CarFuelHistory;
import com.transparent.fleet.ResponceModel.FuelHistoryData;
import com.transparent.fleet.adapter.TruckHistoryAdapter;
import com.transparent.fleet.basecomponent.BaseFragment;
import com.transparent.fleet.callbackClick.OnRecyclerItemClick;
import com.transparent.fleet.databinding.FragmentTruckhistoryBinding;
import com.transparent.fleet.model.TruckHistoryListModel;
import com.transparent.fleet.model.TruckHistoryModel;
import com.transparent.fleet.net.RetrofitClient;
import com.transparent.fleet.util.Utility;

import java.util.ArrayList;

import retrofit.RetrofitError;
import retrofit.client.Response;

public class FragmentCarHistory extends BaseFragment implements View.OnClickListener {
    public static final String TAG = "Latest";
    public TruckHistoryListModel model;
    private FragmentTruckhistoryBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        if (null != openFrag)
//            openFrag.setTitleAndToolbar(getString(R.string.latest), false, true, true, "");

            super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_truckhistory, container, false);
        model = new TruckHistoryListModel();
        model.setArrayList(new ArrayList<TruckHistoryModel>());
        setModelDetail();
        setOnClick();
        binding.setTruckHistoryListModel(model);
        initView();
        setHasOptionsMenu(true);
        return binding.getRoot();

    }


    private void setOnClick() {

    }

    private void setModelDetail() {
        model.setNoWifiImg(getResources().getDrawable(R.drawable.app_icon));
        model.setNoData(getResources().getString(R.string.noInternetConnectivity));

    }


    public void initView() {
        initRecycler();
        HistoryApi();


    }


    /*
     *  initialize Reacycler view
     */
    private void initRecycler() {
        binding.recyclerView.setHasFixedSize(true);

        binding.recyclerView.stopNestedScroll();
        binding.recyclerView.setNestedScrollingEnabled(false);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.recyclerView.setAdapter(new TruckHistoryAdapter(getActivity(),
                model.getArrayList(), new OnRecyclerItemClick() {
            @Override
            public void onClick(final int position, int type) {

            }
        }));

    }


    public void HistoryApi() {
        model.setApiCallActive(true);
        binding.progressBar.setVisibility(View.VISIBLE);

        if (Utility.checkInternetConnection(getContext())) {
            RetrofitClient.getInstance().getRestOkClient().
                    FuelHistoryCarApi(BaseAppClass.getPreferences().getCarId(), callback);
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
            CarFuelHistory fuelHistoryData = (CarFuelHistory) object;
            binding.progressBar.setVisibility(View.GONE);
            TruckHistoryModel truckHistoryModel;
            if (fuelHistoryData.getCar_fuel_histories() != null && !fuelHistoryData.getCar_fuel_histories().isEmpty()) {
                for (int i = 0; i < fuelHistoryData.getCar_fuel_histories().size(); i++) {
                    truckHistoryModel = new TruckHistoryModel();
                    truckHistoryModel.setStation(fuelHistoryData.getCar_fuel_histories().get(i).getFuel_location().getName());
                    truckHistoryModel.setKm(fuelHistoryData.getCar_fuel_histories().get(i).getOn_km());
                    truckHistoryModel.setDate(fuelHistoryData.getCar_fuel_histories().get(i).getOn_date());
                    truckHistoryModel.setLtr(fuelHistoryData.getCar_fuel_histories().get(i).getLiters_fueled());
                    model.getArrayList().add(truckHistoryModel);
                    binding.recyclerView.getAdapter().notifyDataSetChanged();

                }

            } else {
                binding.progressBar.setVisibility(View.GONE);
                model.setApiCallActive(false);
            }
            notyFyDat();


        }

        @Override
        public void failure(RetrofitError error) {
            binding.progressBar.setVisibility(View.GONE);
            model.setApiCallActive(false);
            Log.e("error", error.getMessage());
            notyFyDat();
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
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }


}

