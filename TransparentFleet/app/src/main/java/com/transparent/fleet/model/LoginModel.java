package com.transparent.fleet.model;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.fragment.app.Fragment;


public class LoginModel extends BaseObservable {
    private String strBottomImgTag;
    private BottomBtnClick bottomBtnClick;
    private Context context;
    private boolean isBtmVisible = true;
    private int chatCount;

    boolean isLatestSelected ,isHomeSeleted,isFavSelected,isSettingSelected;
    public LoginModel(Context context) {
        this.context = context;
    }


    public LoginModel(){}
    public interface BottomBtnClick {
        void onBottomBtnClick(Fragment fragToReplace, String tag);
    }

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

    @Bindable
    public boolean isBtmVisible() {
        return isBtmVisible;
    }

    public void setBtmVisible(boolean btmVisible) {
        isBtmVisible = btmVisible;
//        notifyPropertyChanged(BR.isBtmVisible);
        notifyChange();
    }


    private void showFragment(String tag) {
        if (bottomBtnClick != null) {
            bottomBtnClick.onBottomBtnClick(null, tag);
        }
    }

    public boolean isLatestSelected() {
        return isLatestSelected;
    }

    public void setLatestSelected(boolean latestSelected) {
        isLatestSelected = latestSelected;
    }

    public boolean isHomeSeleted() {
        return isHomeSeleted;
    }

    public void setHomeSeleted(boolean homeSeleted) {
        isHomeSeleted = homeSeleted;
    }

    public boolean isFavSelected() {
        return isFavSelected;
    }

    public void setFavSelected(boolean favSelected) {
        isFavSelected = favSelected;
    }

    public boolean isSettingSelected() {
        return isSettingSelected;
    }

    public void setSettingSelected(boolean settingSelected) {
        isSettingSelected = settingSelected;
    }
}
