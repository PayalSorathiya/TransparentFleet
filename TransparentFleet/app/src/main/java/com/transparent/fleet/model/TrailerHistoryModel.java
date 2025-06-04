package com.transparent.fleet.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;


public class TrailerHistoryModel extends BaseObservable implements Parcelable {


    String date;
    String station;
    String ltr;
    String hour;
    public TrailerHistoryModel(){

    }

    protected TrailerHistoryModel(Parcel in) {
        date = in.readString();
        station = in.readString();
        ltr = in.readString();
        hour = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(station);
        dest.writeString(ltr);
        dest.writeString(hour);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TrailerHistoryModel> CREATOR = new Creator<TrailerHistoryModel>() {
        @Override
        public TrailerHistoryModel createFromParcel(Parcel in) {
            return new TrailerHistoryModel(in);
        }

        @Override
        public TrailerHistoryModel[] newArray(int size) {
            return new TrailerHistoryModel[size];
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

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
