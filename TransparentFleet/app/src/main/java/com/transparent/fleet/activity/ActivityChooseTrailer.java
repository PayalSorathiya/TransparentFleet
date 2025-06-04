package com.transparent.fleet.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

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
import com.transparent.fleet.ResponceModel.HomeData;
import com.transparent.fleet.ResponceModel.LoginData;
import com.transparent.fleet.adapter.TrailerAdapter;
import com.transparent.fleet.basecomponent.BaseActivity;
import com.transparent.fleet.callbackClick.OnRecyclerItemClick;
import com.transparent.fleet.databinding.ActivityChooseTrailerBinding;
import com.transparent.fleet.fragment.SlideFragment;
import com.transparent.fleet.model.TrailerListModel;
import com.transparent.fleet.model.TrailerModel;
import com.transparent.fleet.net.RetrofitClient;
import com.transparent.fleet.util.Constants;
import com.transparent.fleet.util.CustomProgressDialog;
import com.transparent.fleet.util.Utility;

import java.util.ArrayList;

import retrofit.RetrofitError;
import retrofit.client.Response;


public class ActivityChooseTrailer extends BaseActivity implements View.OnClickListener {
    public static final String TAG = "Download";
    public static TrailerListModel model;
    private ActivityChooseTrailerBinding binding;
    int selectedPos;
    CustomProgressDialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setModelAndBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_choose_trailer);
//        model = new TrailerListModel();
//        model.setArrayList(new ArrayList<TrailerModel>());
        binding.setTrailerListModel(model);
    }

    @Override
    public void setToolBar() {
        binding.included.imgProfile.setVisibility(View.VISIBLE);
        binding.included.imgMore.setVisibility(View.GONE);

    }

    @Override
    public void initViews() {

        loadImage();
        dialog = new CustomProgressDialog(Constants.PROGRESS_IMAGE, this).createProgressBar();

        binding.txtContinue.setOnClickListener(this);
        binding.userName.setText(BaseAppClass.getPreferences().getUserName());

        initRecycler();
//        fillArrayList();

    }
    private void loadImage() {
        Picasso.with(ActivityChooseTrailer.this)
                .load(BaseAppClass.getPreferences().getUserImage())
                .placeholder(R.drawable.dummyuser)
                .into(binding.included.imgProfile);

    }

    /*
     *  initialize Reacycler view
     */
    private void initRecycler() {
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        binding.recyclerView.setAdapter(new TrailerAdapter(ActivityChooseTrailer.this,
                model.getArrayList(), new OnRecyclerItemClick() {
            @Override
            public void onClick(int position, int type) {
                for (int i = 0; i < model.getArrayList().size(); i++) {
                    model.getArrayList().get(i).setSelected(false);
                }
                model.getArrayList().get(position).setSelected(true);
                selectedPos = position;
                btnEnable();
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
    /*-----------------------------------Fill Dummy ArrayList -----------------------------------------------*/

    private void fillArrayList() {
        model.getArrayList().clear();
        TrailerModel trailerModel;
        for (int i = 0; i < 4; i++) {
            trailerModel = new TrailerModel();
            trailerModel.setName("TR-12");
            trailerModel.setSelected(false);
            model.getArrayList().add(trailerModel);

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
                BaseAppClass.getPreferences().saveTrailerId(model.getArrayList().get(selectedPos).getId());
                BaseAppClass.getPreferences().saveUserHour(model.getArrayList().get(selectedPos).getHours_driven());
                BaseAppClass.getPreferences().saveTrailerPos(selectedPos);

                if (model.isBtnEnable()) {
                    homeDataAPi();

                }
                break;


        }
    }

    public void homeDataAPi() {
        dialog.show();
        dialog.setCancelable(false);
        if (Utility.checkInternetConnection(ActivityChooseTrailer.this)) {
            RetrofitClient.getInstance().getRestOkClient().
                    homeDataAPi(BaseAppClass.getPreferences().getTruckId(),
                            BaseAppClass.getPreferences().getTrailerId(),
                            "",
                            callback);
        } else {
            dialog.hide();
            Toast.makeText(this, getString(R.string.noInternet), Toast.LENGTH_SHORT).show();

        }
    }

    private final retrofit.Callback callback = new retrofit.Callback() {
        @Override
        public void success(Object object, Response response) {
            dialog.hide();

            HomeData homeData = (HomeData) object;

            if (homeData.getStatus().equalsIgnoreCase("success")) {
                BaseAppClass.getPreferences().saveFleetAvg(homeData.getFleet_average());
                BaseAppClass.getPreferences().saveUserAvg(homeData.getYour_average());
                SlideFragment.PAGE_TITLES = homeData.getNotifications();

                Intent intent = new Intent(ActivityChooseTrailer.this, ActivityHome.class);
                startActivity(intent);
                finish();

            } else {
                dialog.hide();
                Toast.makeText(ActivityChooseTrailer.this, homeData.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void failure(RetrofitError error) {
            dialog.hide();
            Toast.makeText(ActivityChooseTrailer.this, "Something Wrong Please Try Again", Toast.LENGTH_SHORT).show();

            Log.e("error", error.getMessage());
        }
    };


}
