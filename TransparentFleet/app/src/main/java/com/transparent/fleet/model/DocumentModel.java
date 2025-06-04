package com.transparent.fleet.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


public class DocumentModel extends BaseObservable implements Parcelable {


    String name;
    String link;
    String status;
    public DocumentModel(){

    }

    protected DocumentModel(Parcel in) {
        name = in.readString();
        link = in.readString();
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(link);
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DocumentModel> CREATOR = new Creator<DocumentModel>() {
        @Override
        public DocumentModel createFromParcel(Parcel in) {
            return new DocumentModel(in);
        }

        @Override
        public DocumentModel[] newArray(int size) {
            return new DocumentModel[size];
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
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
        notifyChange();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
