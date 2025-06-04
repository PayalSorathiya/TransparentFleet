package com.transparent.fleet.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;


public class TruckModel extends BaseObservable implements Parcelable {

    private String tecko_certificate;
    private String cop;
    private String insurance_papers_url;
    private String registration_number;
    private String km_driven;
    private String created_at;
    private String registration_certificate_url;
    private String truck_number;
    private String cvrt_url;
    private String organisation_id;
    private String registration_certificate;
    private String model_name;
    private String insurance_papers;
    private String updated_at;
    private String id;
    private String cvrt;
    private String tecko_certificate_url;
    private String cop_url;
    private String status;
    boolean isSelected;

    public TruckModel() {
    }


    protected TruckModel(Parcel in) {
        tecko_certificate = in.readString();
        cop = in.readString();
        insurance_papers_url = in.readString();
        registration_number = in.readString();
        km_driven = in.readString();
        created_at = in.readString();
        registration_certificate_url = in.readString();
        truck_number = in.readString();
        cvrt_url = in.readString();
        organisation_id = in.readString();
        registration_certificate = in.readString();
        model_name = in.readString();
        insurance_papers = in.readString();
        updated_at = in.readString();
        id = in.readString();
        cvrt = in.readString();
        tecko_certificate_url = in.readString();
        cop_url = in.readString();
        status = in.readString();
        isSelected = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tecko_certificate);
        dest.writeString(cop);
        dest.writeString(insurance_papers_url);
        dest.writeString(registration_number);
        dest.writeString(km_driven);
        dest.writeString(created_at);
        dest.writeString(registration_certificate_url);
        dest.writeString(truck_number);
        dest.writeString(cvrt_url);
        dest.writeString(organisation_id);
        dest.writeString(registration_certificate);
        dest.writeString(model_name);
        dest.writeString(insurance_papers);
        dest.writeString(updated_at);
        dest.writeString(id);
        dest.writeString(cvrt);
        dest.writeString(tecko_certificate_url);
        dest.writeString(cop_url);
        dest.writeString(status);
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TruckModel> CREATOR = new Creator<TruckModel>() {
        @Override
        public TruckModel createFromParcel(Parcel in) {
            return new TruckModel(in);
        }

        @Override
        public TruckModel[] newArray(int size) {
            return new TruckModel[size];
        }
    };

    public String getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }

    public String getTecko_certificate() {
        return tecko_certificate;
    }

    public void setTecko_certificate(String tecko_certificate) {
        this.tecko_certificate = tecko_certificate;
    }

    public String getCop() {
        return cop;
    }

    public void setCop(String cop) {
        this.cop = cop;
    }

    public String getInsurance_papers_url() {
        return insurance_papers_url;
    }

    public void setInsurance_papers_url(String insurance_papers_url) {
        this.insurance_papers_url = insurance_papers_url;
    }

    public String getKm_driven() {
        return km_driven;
    }

    public void setKm_driven(String km_driven) {
        this.km_driven = km_driven;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getRegistration_certificate_url() {
        return registration_certificate_url;
    }

    public void setRegistration_certificate_url(String registration_certificate_url) {
        this.registration_certificate_url = registration_certificate_url;
    }

    public String getTruck_number() {
        return truck_number;
    }

    public void setTruck_number(String truck_number) {
        this.truck_number = truck_number;
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

    @Bindable
    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
        notifyChange();
    }

    public String getInsurance_papers() {
        return insurance_papers;
    }

    public void setInsurance_papers(String insurance_papers) {
        this.insurance_papers = insurance_papers;
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

    public String getTecko_certificate_url() {
        return tecko_certificate_url;
    }

    public void setTecko_certificate_url(String tecko_certificate_url) {
        this.tecko_certificate_url = tecko_certificate_url;
    }

    public String getCop_url() {
        return cop_url;
    }

    public void setCop_url(String cop_url) {
        this.cop_url = cop_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static Creator<TruckModel> getCREATOR() {
        return CREATOR;
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
