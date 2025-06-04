package com.transparent.fleet.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.transparent.fleet.BaseAppClass;


public class TruckHistoryModel extends BaseObservable implements Parcelable {

    String date;
    String station;
    String ltr;
    String km;
    public TruckHistoryModel(){

    }

    protected TruckHistoryModel(Parcel in) {
        date = in.readString();
        station = in.readString();
        ltr = in.readString();
        km = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(station);
        dest.writeString(ltr);
        dest.writeString(km);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TruckHistoryModel> CREATOR = new Creator<TruckHistoryModel>() {
        @Override
        public TruckHistoryModel createFromParcel(Parcel in) {
            return new TruckHistoryModel(in);
        }

        @Override
        public TruckHistoryModel[] newArray(int size) {
            return new TruckHistoryModel[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getLtr() {
        return ltr;
    }

    public void setLtr(String ltr) {
        this.ltr = ltr;
    }

    @Bindable
    public String getKm() {

        return km;
    }

    public void setKm(String km) {
        this.km = km;
        notifyChange();

    }
}
