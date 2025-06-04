package com.transparent.fleet.ResponceModel;

public class BasicDataTrailers {
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

    public String getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }

    public long getHours_driven() {
        return this.hours_driven;
    }

    public void setHours_driven(long hours_driven) {
        this.hours_driven = hours_driven;
    }

    public String getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getAtp_certificate_url() {
        return this.atp_certificate_url;
    }

    public void setAtp_certificate_url(String atp_certificate_url) {
        this.atp_certificate_url = atp_certificate_url;
    }

    public String getRegistration_certificate_url() {
        return this.registration_certificate_url;
    }

    public void setRegistration_certificate_url(String registration_certificate_url) {
        this.registration_certificate_url = registration_certificate_url;
    }

    public String getCvrt_url() {
        return this.cvrt_url;
    }

    public void setCvrt_url(String cvrt_url) {
        this.cvrt_url = cvrt_url;
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

    public String getAtp_certificate() {
        return this.atp_certificate;
    }

    public void setAtp_certificate(String atp_certificate) {
        this.atp_certificate = atp_certificate;
    }

    public String getUpdated_at() {
        return this.updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCvrt() {
        return this.cvrt;
    }

    public void setCvrt(String cvrt) {
        this.cvrt = cvrt;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
