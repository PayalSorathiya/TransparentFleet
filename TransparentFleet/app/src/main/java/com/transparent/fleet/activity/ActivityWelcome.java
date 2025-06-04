package com.transparent.fleet.activity;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.transparent.fleet.BaseAppClass;
import com.transparent.fleet.R;
import com.transparent.fleet.ResponceModel.BasicData;
import com.transparent.fleet.ResponceModel.LoginData;
import com.transparent.fleet.basecomponent.BaseActivity;
import com.transparent.fleet.databinding.WelcomescreenBinding;
import com.transparent.fleet.model.CarModel;
import com.transparent.fleet.model.CardModel;
import com.transparent.fleet.model.DocumentListModel;
import com.transparent.fleet.model.DocumentModel;
import com.transparent.fleet.model.MainModel;
import com.transparent.fleet.model.TrailerListModel;
import com.transparent.fleet.model.TrailerModel;
import com.transparent.fleet.model.TruckModel;
import com.transparent.fleet.net.RetrofitClient;
import com.transparent.fleet.util.Constants;
import com.transparent.fleet.util.CustomEditText;
import com.transparent.fleet.util.CustomProgressDialog;
import com.transparent.fleet.util.CustomTextView;
import com.transparent.fleet.util.CustomTextViewBold;
import com.transparent.fleet.util.Utility;
import com.transparent.fleet.util.Validator;

import java.util.ArrayList;

import retrofit.RetrofitError;
import retrofit.client.Response;


public class ActivityWelcome extends BaseActivity implements View.OnClickListener {
    public static final String TAG = "ActivityWelcome";
    public MainModel model;
    private WelcomescreenBinding binding;
    CustomProgressDialog dialog;

    @Override
    public void setModelAndBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.welcomescreen);
        model = new MainModel();
        binding.setMainModel(model);
    }

    @Override
    public void setToolBar() {

    }

    @Override
    public void initViews() {
        dialog = new CustomProgressDialog(Constants.PROGRESS_IMAGE, this).createProgressBar();

        binding.txtStarted.setOnClickListener(this);


    }


    @Override
    public void closeActivity() {
        finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtStarted:
                if (!BaseAppClass.getPreferences().isUserLoggedIn())
                    loginPopup(ActivityWelcome.this);
                else
                    basicDataApi();


        }
    }

    public void loginPopup(final Context context) {
        Dialog alertPopup = new Dialog(context);
        alertPopup.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertPopup.requestWindowFeature(Window.FEATURE_NO_TITLE);

        alertPopup.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        alertPopup.setContentView(R.layout.activity_login);
        alertPopup.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        alertPopup.setCancelable(true);
        LinearLayout container = (LinearLayout) alertPopup.findViewById(R.id.container);
        ScrollView scrollView = (ScrollView) alertPopup.findViewById(R.id.scrollView);
        container.startAnimation(AnimationUtils.loadAnimation(this, R.anim.custom_bounce_entry));
        CustomTextView txtForgot = (CustomTextView) alertPopup.findViewById(R.id.txtForgot);
        CustomTextViewBold txtLogin = (CustomTextViewBold) alertPopup.findViewById(R.id.txtLogin);
        final CustomEditText edtUsername = (CustomEditText) alertPopup.findViewById(R.id.edtUsername);
        final CustomEditText edtPass = (CustomEditText) alertPopup.findViewById(R.id.edtPass);
        final CustomTextViewBold hrader = (CustomTextViewBold) alertPopup.findViewById(R.id.hrader);
        final CustomTextView title = (CustomTextView) alertPopup.findViewById(R.id.title);
        final CustomTextView txtUserName = (CustomTextView) alertPopup.findViewById(R.id.txtUserName);
        final CustomTextView txtpass = (CustomTextView) alertPopup.findViewById(R.id.txtpass);
        hrader.startAnimation(AnimationUtils.loadAnimation(this, R.anim.btncustom_bounce_entry));
        title.startAnimation(AnimationUtils.loadAnimation(this, R.anim.btncustom_bounce_entry));
        txtUserName.startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn1custom_bounce_entry));
        edtUsername.startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn1custom_bounce_entry));
        edtPass.startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn2custom_bounce_entry));
        txtpass.startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn2custom_bounce_entry));
        txtLogin.startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn3custom_bounce_entry));


        txtForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotPopup(context);
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateInput(edtUsername, edtPass))
                    loginApi(edtUsername.getText().toString(), edtPass.getText().toString());

            }
        });
        alertPopup.show();


    }

    private boolean validateInput(CustomEditText edtName, CustomEditText edtPass) {
        if (Validator.validateString(edtName.getText().toString())) {
            if (Validator.validateString(edtPass.getText().toString())) {

                return true;
            } else
                Toast.makeText(ActivityWelcome.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(ActivityWelcome.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
        return false;
    }

    public static void forgotPopup(Context context) {
        final Dialog alertPopup = new Dialog(new ContextThemeWrapper(context, R.style.DialogSlideAnim));
        alertPopup.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertPopup.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        alertPopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertPopup.setContentView(R.layout.activity_password_reset);
        alertPopup.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        alertPopup.setCancelable(true);
        ImageView imgClose = (ImageView) alertPopup.findViewById(R.id.imgClose);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertPopup.dismiss();
            }
        });

        alertPopup.show();


    }


    public void loginApi(String UserName, String PassWord) {
        dialog.show();
        dialog.setCancelable(false);
        if (Utility.checkInternetConnection(ActivityWelcome.this)) {
            RetrofitClient.getInstance().getRestOkClient().
                    loginAPi(Constants.LOGINTYPE,
                            Constants.CLIENT_ID,
                            Constants.CLIENT_SECRET,
                            UserName,
                            PassWord,
                            "*",
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
            LoginData loginData = (LoginData) object;

            if (loginData.getStatus().equalsIgnoreCase("success")) {
                BaseAppClass.getPreferences().saveToken(loginData.getAccess_token());
                BaseAppClass.getPreferences().saveIsTokenAvailable(true);
                basicDataApi();

            } else {
                Toast.makeText(ActivityWelcome.this, loginData.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void failure(RetrofitError error) {
            dialog.hide();
            Log.e("error", error.getMessage());
        }
    };

    public void basicDataApi() {
        dialog.show();
        dialog.setCancelable(false);
        if (Utility.checkInternetConnection(ActivityWelcome.this)) {
            RetrofitClient.getInstance().getRestOkClient().
                    basicListApi("",
                            basiccallback);
        } else {
            dialog.hide();
            Toast.makeText(this, getString(R.string.noInternet), Toast.LENGTH_SHORT).show();

        }
    }

    private final retrofit.Callback basiccallback = new retrofit.Callback() {
        @Override
        public void success(Object object, Response response) {
            dialog.hide();
            BasicData basicData = (BasicData) object;
            DocumentActivity.model = new DocumentListModel();

            if (basicData.getStatus().equalsIgnoreCase("success")) {
                BaseAppClass.getPreferences().saveUserName(basicData.getDriver().getFirst_name());
                BaseAppClass.getPreferences().saveUserImage(basicData.getDriver().getPhoto_url());
                if (basicData.getTruck() != null) {
                    BaseAppClass.getPreferences().saveUserKm(basicData.getTruck().getKm_driven());
                    BaseAppClass.getPreferences().saveIsTruck(true);

                    ActivityChooseTruck.model = new TruckModel();
                    ActivityChooseTruck.model.setModel_name(basicData.getTruck().getModel_name());
                    ActivityChooseTruck.model.setId(basicData.getTruck().getId());
                    ActivityChooseTruck.model.setRegistration_certificate_url(basicData.getTruck().getRegistration_certificate_url());
                    ActivityChooseTruck.model.setInsurance_papers_url(basicData.getTruck().getInsurance_papers_url());
                    ActivityChooseTruck.model.setTecko_certificate_url(basicData.getTruck().getTecko_certificate_url());
                    ActivityChooseTruck.model.setCop_url(basicData.getTruck().getCop_url());
                    ActivityChooseTruck.model.setCvrt_url(basicData.getTruck().getCvrt_url());
                    ActivityChooseTruck.model.setRegistration_number(basicData.getTruck().getRegistration_number());


                    DocumentActivity.model.setCardModelArrayList(new ArrayList<CardModel>());


                    CardModel cardModel;
                    for (int i = 0; i < 2; i++) {
                        cardModel = new CardModel();
                        if (i == 0) {
                            cardModel.setName("Card 1");
                            cardModel.setCardno(basicData.getTruck().getCard1_number());
                            cardModel.setPinno(basicData.getTruck().getCard1_pin());
                            DocumentActivity.model.getCardModelArrayList().add(cardModel);

                        } else {
                            cardModel.setName("Card 2");
                            cardModel.setCardno(basicData.getTruck().getCard2_number());
                            cardModel.setPinno(basicData.getTruck().getCard2_pin());
                            DocumentActivity.model.getCardModelArrayList().add(cardModel);

                        }

                    }

                }
                if (basicData.getCar() != null) {
                    BaseAppClass.getPreferences().saveUserKm(basicData.getCar().getKm_driven());

                    ActivityChooseCar.model = new CarModel();
                    ActivityChooseCar.model.setModel_name(basicData.getCar().getModel_name());
                    ActivityChooseCar.model.setId(basicData.getCar().getId());
                    ActivityChooseCar.model.setRegistration_certificate_url(basicData.getCar().getRegistration_certificate_url());
                    ActivityChooseCar.model.setRegistration_number(basicData.getCar().getRegistration_number());
                    ActivityChooseCar.model.setInsurance_papers_url(basicData.getCar().getInsurance_papers_url());
                    ActivityChooseCar.model.setAnnual_testing_certificate_url(basicData.getCar().getAnnual_testing_certificate_url());
                    ActivityChooseCar.model.setMore_documents_url(basicData.getCar().getMore_documents_url());

                }
                if (basicData.getTrailers() != null) {
                    ActivityChooseTrailer.model = new TrailerListModel();
                    ActivityChooseTrailer.model.setArrayList(new ArrayList<TrailerModel>());
                    ActivityChooseTrailer.model.getArrayList().clear();
                    TrailerModel trailerModel;
                    for (int i = 0; i < basicData.getTrailers().size(); i++) {
                        trailerModel = new TrailerModel();
                        trailerModel.setName(basicData.getTrailers().get(i).getName());
                        trailerModel.setId(basicData.getTrailers().get(i).getId());
                        trailerModel.setHours_driven(basicData.getTrailers().get(i).getHours_driven());
                        trailerModel.setAtp_certificate_url(basicData.getTrailers().get(i).getAtp_certificate_url());
                        trailerModel.setCvrt_url(basicData.getTrailers().get(i).getCvrt_url());
                        trailerModel.setRegistration_certificate_url(basicData.getTrailers().get(i).getRegistration_certificate_url());
                        trailerModel.setRegistration_number(basicData.getTrailers().get(i).getRegistration_number());
                        trailerModel.setSelected(false);
                        ActivityChooseTrailer.model.getArrayList().add(trailerModel);

                    }
                }

                DocumentActivity.model.setArrayList(new ArrayList<DocumentModel>());
                DocumentModel documentModel;
                for (int i = 0; i < 3; i++) {
                    documentModel = new DocumentModel();
                    if (i == 0) {
                        documentModel.setName("Driving License");
                        documentModel.setLink(basicData.getDriver().getLicense_url());
                        DocumentActivity.model.getArrayList().add(documentModel);
                    }
                    if (i == 1) {
                        documentModel.setName("Photo");
                        documentModel.setLink(basicData.getDriver().getPhoto_url());
                        DocumentActivity.model.getArrayList().add(documentModel);
                    }
                    if (i == 2) {
                        documentModel.setName("More Document");
                        documentModel.setLink(basicData.getDriver().getMore_documents_url());
                        DocumentActivity.model.getArrayList().add(documentModel);
                    }
                }

                if (basicData.getTruck() != null) {
                    Intent intent = new Intent(ActivityWelcome.this, ActivityChooseTruck.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(ActivityWelcome.this, ActivityChooseCar.class);
                    startActivity(intent);
                }

                finish();
            } else {
                Toast.makeText(ActivityWelcome.this, basicData.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void failure(RetrofitError error) {
            dialog.hide();

            Log.e("error", error.getMessage());
        }
    };


}
