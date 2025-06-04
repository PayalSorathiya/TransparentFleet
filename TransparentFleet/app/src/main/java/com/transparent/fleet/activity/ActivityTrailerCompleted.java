package com.transparent.fleet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.transparent.fleet.R;
import com.transparent.fleet.basecomponent.BaseActivity;
import com.transparent.fleet.databinding.ActivityTrailerfuelComplateBinding;
import com.transparent.fleet.model.TrailerCompletedModel;


public class ActivityTrailerCompleted extends BaseActivity implements View.OnClickListener {
    public static final String TAG = "ActivityTrailerCompleted";
    public TrailerCompletedModel model;
    private ActivityTrailerfuelComplateBinding binding;
    public static final String LITRES = "Litres";
    public static final String DATE = "date";
    public static final String HOUR = "hour";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    }

    @Override
    public void setModelAndBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_trailerfuel_complate);
        model = new TrailerCompletedModel();
        binding.setTrailerCompletedModel(model);
    }

    @Override
    public void setToolBar() {

    }

    @Override
    public void initViews() {
        binding.ltrFuel.setText(getIntent().getStringExtra(LITRES) +" Litres fueled!");
        binding.txtdate.setText(getIntent().getStringExtra(DATE));
        binding.txtHour.setText(getIntent().getStringExtra(HOUR) +" Hours");

        OnClick();


    }

    private void OnClick() {
        binding.txtFinish.setOnClickListener(this);
        binding.txtHistory.setOnClickListener(this);
    }


    @Override
    public void closeActivity() {
        finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtFinish:
                Intent intent = new Intent(ActivityTrailerCompleted.this, ActivityHome.class);
                startActivity(intent);
                finish();
                break;
            case R.id.txtHistory:
                Intent intentHistory = new Intent(ActivityTrailerCompleted.this, HistoryActivity.class);
                intentHistory.putExtra(getString(R.string.position),1);
                startActivity(intentHistory);
                break;


        }
    }


}
