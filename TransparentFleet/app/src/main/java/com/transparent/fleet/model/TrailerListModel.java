package com.transparent.fleet.model;


import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


import com.transparent.fleet.util.Constants;

import java.util.ArrayList;


public class TrailerListModel extends BaseObservable implements Parcelable {
    private ArrayList<TrailerModel> arrayList;

    private boolean apiCallActive = true, isSwipeEnabled, isSwipeRefress, isCompleted;
    private int page = 0;
    private int limit = Constants.LIMIT;
    private int count = 0;
    private String noData, sorting = "asc", search;
    private int progress;
    private Drawable noWifiImg;
    private String language;
    private int type;
    boolean btnEnable;


    public TrailerListModel() {

    }


    protected TrailerListModel(Parcel in) {
        arrayList = in.createTypedArrayList(TrailerModel.CREATOR);
        apiCallActive = in.readByte() != 0;
        isSwipeEnabled = in.readByte() != 0;
        isSwipeRefress = in.readByte() != 0;
        isCompleted = in.readByte() != 0;
        page = in.readInt();
        limit = in.readInt();
        count = in.readInt();
        noData = in.readString();
        sorting = in.readString();
        search = in.readString();
        progress = in.readInt();
        language = in.readString();
        type = in.readInt();
        btnEnable = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(arrayList);
        dest.writeByte((byte) (apiCallActive ? 1 : 0));
        dest.writeByte((byte) (isSwipeEnabled ? 1 : 0));
        dest.writeByte((byte) (isSwipeRefress ? 1 : 0));
        dest.writeByte((byte) (isCompleted ? 1 : 0));
        dest.writeInt(page);
        dest.writeInt(limit);
        dest.writeInt(count);
        dest.writeString(noData);
        dest.writeString(sorting);
        dest.writeString(search);
        dest.writeInt(progress);
        dest.writeString(language);
        dest.writeInt(type);
        dest.writeByte((byte) (btnEnable ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TrailerListModel> CREATOR = new Creator<TrailerListModel>() {
        @Override
        public TrailerListModel createFromParcel(Parcel in) {
            return new TrailerListModel(in);
        }

        @Override
        public TrailerListModel[] newArray(int size) {
            return new TrailerListModel[size];
        }
    };

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    @Bindable
    public boolean isApiCallActive() {
        return apiCallActive;
    }

    public void setApiCallActive(boolean apiCallActive) {
        this.apiCallActive = apiCallActive;
        notifyChange();
    }

    @Bindable
    public boolean isBtnEnable() {
        return btnEnable;
    }

    public void setBtnEnable(boolean btnEnable) {
        this.btnEnable = btnEnable;
        notifyChange();
    }

    public boolean isSwipeEnabled() {
        return isSwipeEnabled;
    }

    public void setSwipeEnabled(boolean swipeEnabled) {
        isSwipeEnabled = swipeEnabled;
    }

    public boolean isSwipeRefress() {
        return isSwipeRefress;
    }

    public void setSwipeRefress(boolean swipeRefress) {
        isSwipeRefress = swipeRefress;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNoData() {
        return noData;
    }

    public void setNoData(String noData) {
        this.noData = noData;
    }

    public String getSorting() {
        return sorting;
    }

    public void setSorting(String sorting) {
        this.sorting = sorting;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    @Bindable
    public Drawable getNoWifiImg() {
        return noWifiImg;
    }


    public void setNoWifiImg(Drawable noWifiImg) {
        this.noWifiImg = noWifiImg;
        notifyChange();

    }

    public ArrayList<TrailerModel> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<TrailerModel> arrayList) {
        this.arrayList = arrayList;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
