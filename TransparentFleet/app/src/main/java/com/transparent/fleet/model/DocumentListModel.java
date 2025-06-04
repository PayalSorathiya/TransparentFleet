package com.transparent.fleet.model;


import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.transparent.fleet.util.Constants;

import java.util.ArrayList;


public class DocumentListModel extends BaseObservable implements Parcelable {
    private ArrayList<DocumentModel> arrayList;
    private ArrayList<CardModel> cardModelArrayList;

    private boolean apiCallActive = true, isSwipeEnabled, isSwipeRefress, isCompleted;
    private int page = 0;
    private int limit = Constants.LIMIT;
    private int count = 0;
    private String noData, sorting = "asc", search;
    private int progress;
    private Drawable noWifiImg;
    private String language;
    private int type;

    public DocumentListModel() {

    }

    protected DocumentListModel(Parcel in) {
        arrayList = in.createTypedArrayList(DocumentModel.CREATOR);
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
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DocumentListModel> CREATOR = new Creator<DocumentListModel>() {
        @Override
        public DocumentListModel createFromParcel(Parcel in) {
            return new DocumentListModel(in);
        }

        @Override
        public DocumentListModel[] newArray(int size) {
            return new DocumentListModel[size];
        }
    };

    public ArrayList<DocumentModel> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<DocumentModel> arrayList) {
        this.arrayList = arrayList;
    }

    public ArrayList<CardModel> getCardModelArrayList() {
        return cardModelArrayList;
    }

    public void setCardModelArrayList(ArrayList<CardModel> cardModelArrayList) {
        this.cardModelArrayList = cardModelArrayList;
    }

    public boolean isApiCallActive() {
        return apiCallActive;
    }

    public void setApiCallActive(boolean apiCallActive) {
        this.apiCallActive = apiCallActive;
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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
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

    public Drawable getNoWifiImg() {
        return noWifiImg;
    }

    public void setNoWifiImg(Drawable noWifiImg) {
        this.noWifiImg = noWifiImg;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
