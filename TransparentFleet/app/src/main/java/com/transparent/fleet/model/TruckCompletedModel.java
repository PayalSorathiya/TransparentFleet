package com.transparent.fleet.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


public class TruckCompletedModel extends BaseObservable implements Parcelable {


    public TruckCompletedModel() {
    }

    String name;
    boolean isSelected;


    protected TruckCompletedModel(Parcel in) {
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

    public static final Creator<TruckCompletedModel> CREATOR = new Creator<TruckCompletedModel>() {
        @Override
        public TruckCompletedModel createFromParcel(Parcel in) {
            return new TruckCompletedModel(in);
        }

        @Override
        public TruckCompletedModel[] newArray(int size) {
            return new TruckCompletedModel[size];
        }
    };


    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyChange();

    }

    @Bindable
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        notifyChange();

    }


}
