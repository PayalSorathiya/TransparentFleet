package com.transparent.fleet.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


public class TrailerModel extends BaseObservable implements Parcelable {

    private long hours_driven;
    private String created_at;
    private String atp_certificate_url;
    private String registration_certificate_url;
    private String registration_number;
    private String cvrt_url;
    private String organisation_id;
    private String registration_certificate;
    private String atp_certificate;
    private String updated_at;
    private String name;
    private String id;
    private String cvrt;
    private String status;
    boolean isSelected;

    public TrailerModel() {

    }

    protected TrailerModel(Parcel in) {
        hours_driven = in.readLong();
        created_at = in.readString();
        atp_certificate_url = in.readString();
        registration_certificate_url = in.readString();
        registration_number = in.readString();
        cvrt_url = in.readString();
        organisation_id = in.readString();
        registration_certificate = in.readString();
        atp_certificate = in.readString();
        updated_at = in.readString();
        name = in.readString();
        id = in.readString();
        cvrt = in.readString();
        status = in.readString();
        isSelected = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(hours_driven);
        dest.writeString(created_at);
        dest.writeString(atp_certificate_url);
        dest.writeString(registration_certificate_url);
        dest.writeString(registration_number);
        dest.writeString(cvrt_url);
        dest.writeString(organisation_id);
        dest.writeString(registration_certificate);
        dest.writeString(atp_certificate);
        dest.writeString(updated_at);
        dest.writeString(name);
        dest.writeString(id);
        dest.writeString(cvrt);
        dest.writeString(status);
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TrailerModel> CREATOR = new Creator<TrailerModel>() {
        @Override
        public TrailerModel createFromParcel(Parcel in) {
            return new TrailerModel(in);
        }

        @Override
        public TrailerModel[] newArray(int size) {
            return new TrailerModel[size];
        }
    };

    public String getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }

    public long getHours_driven() {
        return hours_driven;
    }

    public void setHours_driven(long hours_driven) {
        this.hours_driven = hours_driven;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getAtp_certificate_url() {
        return atp_certificate_url;
    }

    public void setAtp_certificate_url(String atp_certificate_url) {
        this.atp_certificate_url = atp_certificate_url;
    }

    public String getRegistration_certificate_url() {
        return registration_certificate_url;
    }

    public void setRegistration_certificate_url(String registration_certificate_url) {
        this.registration_certificate_url = registration_certificate_url;
    }

    public String getCvrt_url() {
        return cvrt_url;
    }

    public void setCvrt_url(String cvrt_url) {
        this.cvrt_url = cvrt_url;
    }

    public String getOrganisation_id() {
        return organisation_id;
    }

    public void setOrganisation_id(String organisation_id) {
        this.organisation_id = organisation_id;
    }

    public String getRegistration_certificate() {
        return registration_certificate;
    }

    public void setRegistration_certificate(String registration_certificate) {
        this.registration_certificate = registration_certificate;
    }

    public String getAtp_certificate() {
        return atp_certificate;
    }

    public void setAtp_certificate(String atp_certificate) {
        this.atp_certificate = atp_certificate;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCvrt() {
        return cvrt;
    }

    public void setCvrt(String cvrt) {
        this.cvrt = cvrt;
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
