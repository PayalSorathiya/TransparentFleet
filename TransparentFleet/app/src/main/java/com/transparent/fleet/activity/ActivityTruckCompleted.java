package com.transparent.fleet.activity;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.transparent.fleet.BaseAppClass;
import com.transparent.fleet.R;
import com.transparent.fleet.basecomponent.BaseActivity;
import com.transparent.fleet.databinding.ActivityTruckrfuelComplateBinding;
import com.transparent.fleet.model.TruckCompletedModel;
import com.transparent.fleet.util.CustomTextView;
import com.transparent.fleet.util.CustomTextViewBold;


public class ActivityTruckCompleted extends BaseActivity implements View.OnClickListener {
    public static final String TAG = "ActivityTruckCompleted";
    public TruckCompletedModel model;
    private ActivityTruckrfuelComplateBinding binding;


    public static final String LITRES = "Litres";
    public static final String DATE = "date";
    public static final String KM = "km";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    }


    @Override
    public void setModelAndBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_truckrfuel_complate);
        model = new TruckCompletedModel();
        binding.setTruckCompletedModel(model);
    }

    @Override
    public void setToolBar() {

    }

    @Override
    public void initViews() {
        binding.litre.setText(getIntent().getStringExtra(LITRES) + " Litres fueled!");
        binding.txtdate.setText(getIntent().getStringExtra(DATE));

        String dummyKm = getIntent().getStringExtra(KM).replace('.',',');
        binding.txtKm.setText(dummyKm + " KM’s");

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
                Intent intent = new Intent(ActivityTruckCompleted.this, ActivityHome.class);
                startActivity(intent);
                finish();
                break;
            case R.id.txtHistory:
                if (BaseAppClass.getPreferences().isTruck()) {
                    Intent intentHistory = new Intent(ActivityTruckCompleted.this, HistoryActivity.class);
                    intentHistory.putExtra(getString(R.string.position), 0);
                    startActivity(intentHistory);
                } else {
                    Intent intentHistory = new Intent(ActivityTruckCompleted.this, CarHistoryActivity.class);
                    intentHistory.putExtra(getString(R.string.position), 0);
                    startActivity(intentHistory);

                }
                break;

        }
    }


}
