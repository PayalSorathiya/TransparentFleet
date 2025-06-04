package com.transparent.fleet.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


public class TrailerCompletedModel extends BaseObservable implements Parcelable {


    public TrailerCompletedModel() {
    }

    String name;
    boolean isSelected;


    protected TrailerCompletedModel(Parcel in) {
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

    public static final Creator<TrailerCompletedModel> CREATOR = new Creator<TrailerCompletedModel>() {
        @Override
        public TrailerCompletedModel createFromParcel(Parcel in) {
            return new TrailerCompletedModel(in);
        }

        @Override
        public TrailerCompletedModel[] newArray(int size) {
            return new TrailerCompletedModel[size];
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
