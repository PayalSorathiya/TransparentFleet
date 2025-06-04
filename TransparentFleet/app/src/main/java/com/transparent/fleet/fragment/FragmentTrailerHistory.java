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
import com.transparent.fleet.ResponceModel.FuelHistoryData;
import com.transparent.fleet.adapter.TrailerHistoryAdapter;
import com.transparent.fleet.adapter.TruckHistoryAdapter;
import com.transparent.fleet.basecomponent.BaseFragment;
import com.transparent.fleet.callbackClick.OnRecyclerItemClick;
import com.transparent.fleet.databinding.FragmentTrailerhistoryBinding;
import com.transparent.fleet.databinding.FragmentTruckhistoryBinding;
import com.transparent.fleet.model.TrailerHistoryListModel;
import com.transparent.fleet.model.TrailerHistoryListModel;
import com.transparent.fleet.model.TrailerHistoryModel;
import com.transparent.fleet.model.TruckHistoryModel;
import com.transparent.fleet.net.RetrofitClient;
import com.transparent.fleet.util.Utility;

import java.util.ArrayList;

import retrofit.RetrofitError;
import retrofit.client.Response;

public class FragmentTrailerHistory extends BaseFragment implements View.OnClickListener {
    public static final String TAG = "Latest";
    public TrailerHistoryListModel model;
    private FragmentTrailerhistoryBinding binding;

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_trailerhistory, container, false);
        model = new TrailerHistoryListModel();
        model.setArrayList(new ArrayList<TrailerHistoryModel>());
        setModelDetail();
        setOnClick();
        binding.setTrailerHistoryListModel(model);
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
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.recyclerView.setAdapter(new TrailerHistoryAdapter(getActivity(),
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
                    FuelHistoryApi(BaseAppClass.getPreferences().getTrailerId(),
                            BaseAppClass.getPreferences().getTruckId(),
                            callback);
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
            FuelHistoryData fuelHistoryData = (FuelHistoryData) object;
            binding.progressBar.setVisibility(View.GONE);
            TrailerHistoryModel trailerHistoryModel;
            if (fuelHistoryData.getTrailer_fuel_histories() != null && !fuelHistoryData.getTrailer_fuel_histories().isEmpty()) {
                for (int i = 0; i < fuelHistoryData.getTrailer_fuel_histories().size(); i++) {
                    trailerHistoryModel = new TrailerHistoryModel();
                    trailerHistoryModel.setStation(fuelHistoryData.getTrailer_fuel_histories().get(i).getFuel_location().getName());
                    trailerHistoryModel.setHour(fuelHistoryData.getTrailer_fuel_histories().get(i).getAt_hours());
                    trailerHistoryModel.setDate(fuelHistoryData.getTrailer_fuel_histories().get(i).getOn_date());
                    trailerHistoryModel.setLtr(fuelHistoryData.getTrailer_fuel_histories().get(i).getLiters_fueled());
                    model.getArrayList().add(trailerHistoryModel);
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

