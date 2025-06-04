package com.transparent.fleet.util;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.transparent.fleet.R;
import com.transparent.fleet.callbackClick.TwoButtonListener;


public class AlertDialogAndIntents {

    //--------------------------------------Toasts--------------------------------------------------


    public static void showShortToast(Context context, String strToast) {
        Toast.makeText(context, strToast, Toast.LENGTH_SHORT).show();
    }

    //------------------------------------Soical Intents--------------------------------------------

    //open webview
    public static void openUrl(Context context, String strUrl) {
        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(strUrl));
            context.startActivity(browserIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void shareApp(Context context, String strUrl) {
        String s1 = "http://play.google.com/store/apps/details?id="
                + AppConstant.getPackageName(context);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, s1);
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }


    public static void openPlayStore(Context context) {
        final String appPackageName = AppConstant.getPackageName(context);
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + appPackageName)));
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id="
                            + appPackageName)));
        }
    }
    public static void showLogoutPopUp(final Context context, TwoButtonListener btnListener) {


        final TwoButtonListener listener = btnListener;

        final Dialog alertPopup = new Dialog(context);
        alertPopup.getWindow().setBackgroundDrawableResource(R.color.white_alpha_50);
        alertPopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertPopup.setContentView(R.layout.logout_popup);
        alertPopup.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        alertPopup.setCancelable(true);
        CustomTextView btnOk = (CustomTextView) alertPopup.findViewById(R.id.txtPostitive);
        CustomTextView btnCancel = (CustomTextView) alertPopup.findViewById(R.id.txtNagetive);



        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener)
                    listener.OkClick();
                alertPopup.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener)
                    listener.CanselClick();
                alertPopup.dismiss();
            }
        });

        alertPopup.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });

        alertPopup.show();

    }

}