package com.transparent.fleet.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


public class VehicleModel extends BaseObservable implements Parcelable {

    String name;
    boolean isBtnEnable;
    boolean isTruck;
    boolean isTrailer;

    public VehicleModel() {

    }

    protected VehicleModel(Parcel in) {
        name = in.readString();
        isBtnEnable = in.readByte() != 0;
        isTruck = in.readByte() != 0;
        isTrailer = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeByte((byte) (isBtnEnable ? 1 : 0));
        dest.writeByte((byte) (isTruck ? 1 : 0));
        dest.writeByte((byte) (isTrailer ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VehicleModel> CREATOR = new Creator<VehicleModel>() {
        @Override
        public VehicleModel createFromParcel(Parcel in) {
            return new VehicleModel(in);
        }

        @Override
        public VehicleModel[] newArray(int size) {
            return new VehicleModel[size];
        }
    };

    @Bindable
    public boolean isBtnEnable() {
        return isBtnEnable;
    }

    public void setBtnEnable(boolean btnEnable) {
        isBtnEnable = btnEnable;
        notifyChange();
    }

    @Bindable
    public boolean isTruck() {
        return isTruck;
    }

    public void setTruck(boolean truck) {
        isTruck = truck;
        notifyChange();

    }

    @Bindable
    public boolean isTrailer() {
        return isTrailer;
    }

    public void setTrailer(boolean trailer) {
        isTrailer = trailer;
        notifyChange();

    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyChange();
    }

}
