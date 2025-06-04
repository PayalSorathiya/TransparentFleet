package com.transparent.fleet.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;


public class AddFuelInTruckModel extends BaseObservable implements Parcelable {

    String name;
    boolean isSelected =true;
    public AddFuelInTruckModel(){

    }


    protected AddFuelInTruckModel(Parcel in) {
        name = in.readString();
        isSelected = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AddFuelInTruckModel> CREATOR = new Creator<AddFuelInTruckModel>() {
        @Override
        public AddFuelInTruckModel createFromParcel(Parcel in) {
            return new AddFuelInTruckModel(in);
        }

        @Override
        public AddFuelInTruckModel[] newArray(int size) {
            return new AddFuelInTruckModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
