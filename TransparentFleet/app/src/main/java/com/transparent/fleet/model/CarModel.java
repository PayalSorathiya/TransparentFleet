package com.transparent.fleet.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


public class CarModel extends BaseObservable implements Parcelable {

    boolean isSelected;

    public CarModel() {
    }
    private String insurance_papers_url;
    private long km_driven;
    private String created_at;
    private String registration_certificate_url;
    private String registration_number;
    private String organisation_id;
    private String registration_certificate;
    private String model_name;
    private String insurance_papers;
    private String updated_at;
    private String id;
    private String status;
    private String car_number;
    private String annual_testing_certificate;
    private String more_documents;
    private String annual_testing_certificate_url;
    private String more_documents_url;

    protected CarModel(Parcel in) {
        isSelected = in.readByte() != 0;
        insurance_papers_url = in.readString();
        km_driven = in.readLong();
        created_at = in.readString();
        registration_certificate_url = in.readString();
        registration_number = in.readString();
        organisation_id = in.readString();
        registration_certificate = in.readString();
        model_name = in.readString();
        insurance_papers = in.readString();
        updated_at = in.readString();
        id = in.readString();
        status = in.readString();
        car_number = in.readString();
        annual_testing_certificate = in.readString();
        more_documents = in.readString();
        annual_testing_certificate_url = in.readString();
        more_documents_url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (isSelected ? 1 : 0));
        dest.writeString(insurance_papers_url);
        dest.writeLong(km_driven);
        dest.writeString(created_at);
        dest.writeString(registration_certificate_url);
        dest.writeString(registration_number);
        dest.writeString(organisation_id);
        dest.writeString(registration_certificate);
        dest.writeString(model_name);
        dest.writeString(insurance_papers);
        dest.writeString(updated_at);
        dest.writeString(id);
        dest.writeString(status);
        dest.writeString(car_number);
        dest.writeString(annual_testing_certificate);
        dest.writeString(more_documents);
        dest.writeString(annual_testing_certificate_url);
        dest.writeString(more_documents_url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CarModel> CREATOR = new Creator<CarModel>() {
        @Override
        public CarModel createFromParcel(Parcel in) {
            return new CarModel(in);
        }

        @Override
        public CarModel[] newArray(int size) {
            return new CarModel[size];
        }
    };

    public String getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }

    public String getInsurance_papers_url() {
        return this.insurance_papers_url;
    }

    public void setInsurance_papers_url(String insurance_papers_url) {
        this.insurance_papers_url = insurance_papers_url;
    }

    public long getKm_driven() {
        return this.km_driven;
    }

    public void setKm_driven(long km_driven) {
        this.km_driven = km_driven;
    }

    public String getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getRegistration_certificate_url() {
        return this.registration_certificate_url;
    }

    public void setRegistration_certificate_url(String registration_certificate_url) {
        this.registration_certificate_url = registration_certificate_url;
    }

    public String getOrganisation_id() {
        return this.organisation_id;
    }

    public void setOrganisation_id(String organisation_id) {
        this.organisation_id = organisation_id;
    }

    public String getRegistration_certificate() {
        return this.registration_certificate;
    }

    public void setRegistration_certificate(String registration_certificate) {
        this.registration_certificate = registration_certificate;
    }

    public String getModel_name() {
        return this.model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public String getInsurance_papers() {
        return this.insurance_papers;
    }

    public void setInsurance_papers(String insurance_papers) {
        this.insurance_papers = insurance_papers;
    }

    public String getUpdated_at() {
        return this.updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCar_number() {
        return car_number;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    public String getAnnual_testing_certificate() {
        return annual_testing_certificate;
    }

    public void setAnnual_testing_certificate(String annual_testing_certificate) {
        this.annual_testing_certificate = annual_testing_certificate;
    }

    public String getMore_documents() {
        return more_documents;
    }

    public void setMore_documents(String more_documents) {
        this.more_documents = more_documents;
    }

    public String getAnnual_testing_certificate_url() {
        return annual_testing_certificate_url;
    }

    public void setAnnual_testing_certificate_url(String annual_testing_certificate_url) {
        this.annual_testing_certificate_url = annual_testing_certificate_url;
    }

    public String getMore_documents_url() {
        return more_documents_url;
    }

    public void setMore_documents_url(String more_documents_url) {
        this.more_documents_url = more_documents_url;
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
