package com.transparent.fleet.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


public class FuelStationModel extends BaseObservable implements Parcelable {


    private String updated_at;
    private String parent_id;
    private String name;
    private String created_at;
    private String id;
    private String status;
    boolean isSelected;

    public FuelStationModel(){

    }

    protected FuelStationModel(Parcel in) {
        updated_at = in.readString();
        parent_id = in.readString();
        name = in.readString();
        created_at = in.readString();
        id = in.readString();
        status = in.readString();
        isSelected = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(updated_at);
        dest.writeString(parent_id);
        dest.writeString(name);
        dest.writeString(created_at);
        dest.writeString(id);
        dest.writeString(status);
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FuelStationModel> CREATOR = new Creator<FuelStationModel>() {
        @Override
        public FuelStationModel createFromParcel(Parcel in) {
            return new FuelStationModel(in);
        }

        @Override
        public FuelStationModel[] newArray(int size) {
            return new FuelStationModel[size];
        }
    };

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        notifyChange();
    }
}
