package com.transparent.fleet.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


public class SelectStationModel extends BaseObservable implements Parcelable {

    String name;
    boolean isBtnEnable;
    boolean depotSelection =false;
    boolean otherSelaction =false;
    public SelectStationModel(){

    }

    protected SelectStationModel(Parcel in) {
        name = in.readString();
        isBtnEnable = in.readByte() != 0;
        depotSelection = in.readByte() != 0;
        otherSelaction = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeByte((byte) (isBtnEnable ? 1 : 0));
        dest.writeByte((byte) (depotSelection ? 1 : 0));
        dest.writeByte((byte) (otherSelaction ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SelectStationModel> CREATOR = new Creator<SelectStationModel>() {
        @Override
        public SelectStationModel createFromParcel(Parcel in) {
            return new SelectStationModel(in);
        }

        @Override
        public SelectStationModel[] newArray(int size) {
            return new SelectStationModel[size];
        }
    };

    @Bindable
    public boolean isDepotSelection() {
        return depotSelection;
    }

    public void setDepotSelection(boolean depotSelection) {
        this.depotSelection = depotSelection;
        notifyChange();
    }

    @Bindable
    public boolean isOtherSelaction() {
        return otherSelaction;
    }

    public void setOtherSelaction(boolean otherSelaction) {
        this.otherSelaction = otherSelaction;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyChange();
    }

    @Bindable
    public boolean isBtnEnable() {
        return isBtnEnable;
    }

    public void setBtnEnable(boolean btnEnable) {
        isBtnEnable = btnEnable;
        notifyChange();
    }
}
