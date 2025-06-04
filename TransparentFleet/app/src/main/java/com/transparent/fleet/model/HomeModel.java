package com.transparent.fleet.model;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.fragment.app.Fragment;


public class HomeModel extends BaseObservable {
    private String strBottomImgTag;
    private Context context;
    private int chatCount;

    public HomeModel(Context context) {
        this.context = context;
    }


    public HomeModel(){}


    @Bindable
    public String getStrBottomImgTag() {
        return strBottomImgTag;
    }

    public void setStrBottomImgTag(String strBottomImgTag) {
        this.strBottomImgTag = strBottomImgTag;
        notifyChange();
    }

    @Bindable
    public int getChatCount() {
        return chatCount;
    }

    public void setChatCount(int chatCount) {
        this.chatCount = chatCount;
        notifyChange();
    }



}
