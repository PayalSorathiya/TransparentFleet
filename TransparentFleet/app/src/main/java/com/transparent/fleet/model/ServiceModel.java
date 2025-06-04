package com.transparent.fleet.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;


public class ServiceModel extends BaseObservable implements Parcelable {


    String date;
    String name;
    String description;
    String status;
    String type;
    public ServiceModel(){

    }

    protected ServiceModel(Parcel in) {
        date = in.readString();
        name = in.readString();
        description = in.readString();
        status = in.readString();
        type = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(status);
        dest.writeString(type);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ServiceModel> CREATOR = new Creator<ServiceModel>() {
        @Override
        public ServiceModel createFromParcel(Parcel in) {
            return new ServiceModel(in);
        }

        @Override
        public ServiceModel[] newArray(int size) {
            return new ServiceModel[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
