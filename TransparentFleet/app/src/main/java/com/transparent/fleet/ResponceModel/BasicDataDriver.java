package com.transparent.fleet.ResponceModel;

import java.util.ArrayList;

public class BasicDataDriver {
    private String last_login_at;
    private String confirmation_code;
    private String timezone;
    private BasicDataDriverTruck truck;
    private String avatar_location;
    private String created_at;
    private String uuid;
    private boolean confirmed;
    private String avatar_type;
    private int organisation_id;
    private String updated_at;
    private String last_login_ip;
    private String contact_no;
    private boolean to_be_logged_out;
    private int id;
    private String first_name;
    private String email;
    private String password_changed_at;
    private String address;
    private String card1_pin;
    private String card2_pin;
    private String card2_number;
    private String card1_number;

    private String last_name;
    private boolean active;
    private String deleted_at;
    private ArrayList<BasicDataDriverTrailers> trailers;
    private String license;
    private String license_url;
    private String photo_url;
    private String full_name;
    private String username;
    private String more_documents_url;
    private String more_documents;

    public String getMore_documents_url() {
        return more_documents_url;
    }

    public void setMore_documents_url(String more_documents_url) {
        this.more_documents_url = more_documents_url;
    }

    public String getMore_documents() {
        return more_documents;
    }

    public void setMore_documents(String more_documents) {
        this.more_documents = more_documents;
    }

    public String getLast_login_at() {
        return this.last_login_at;
    }

    public void setLast_login_at(String last_login_at) {
        this.last_login_at = last_login_at;
    }

    public String getConfirmation_code() {
        return this.confirmation_code;
    }

    public void setConfirmation_code(String confirmation_code) {
        this.confirmation_code = confirmation_code;
    }

    public String getTimezone() {
        return this.timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public BasicDataDriverTruck getTruck() {
        return this.truck;
    }

    public void setTruck(BasicDataDriverTruck truck) {
        this.truck = truck;
    }

    public String getAvatar_location() {
        return this.avatar_location;
    }

    public void setAvatar_location(String avatar_location) {
        this.avatar_location = avatar_location;
    }

    public String getCard2_number() {
        return this.card2_number;
    }

    public void setCard2_number(String card2_number) {
        this.card2_number = card2_number;
    }

    public String getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCard1_number() {
        return this.card1_number;
    }

    public void setCard1_number(String card1_number) {
        this.card1_number = card1_number;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean getConfirmed() {
        return this.confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getAvatar_type() {
        return this.avatar_type;
    }

    public void setAvatar_type(String avatar_type) {
        this.avatar_type = avatar_type;
    }

    public int getOrganisation_id() {
        return this.organisation_id;
    }

    public void setOrganisation_id(int organisation_id) {
        this.organisation_id = organisation_id;
    }

    public String getUpdated_at() {
        return this.updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getLast_login_ip() {
        return this.last_login_ip;
    }

    public void setLast_login_ip(String last_login_ip) {
        this.last_login_ip = last_login_ip;
    }

    public String getContact_no() {
        return this.contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public boolean getTo_be_logged_out() {
        return this.to_be_logged_out;
    }

    public void setTo_be_logged_out(boolean to_be_logged_out) {
        this.to_be_logged_out = to_be_logged_out;
    }

    public String getCard2_pin() {
        return this.card2_pin;
    }

    public void setCard2_pin(String card2_pin) {
        this.card2_pin = card2_pin;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword_changed_at() {
        return this.password_changed_at;
    }

    public void setPassword_changed_at(String password_changed_at) {
        this.password_changed_at = password_changed_at;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCard1_pin() {
        return this.card1_pin;
    }

    public void setCard1_pin(String card1_pin) {
        this.card1_pin = card1_pin;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDeleted_at() {
        return this.deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public ArrayList<BasicDataDriverTrailers> getTrailers() {
        return this.trailers;
    }

    public void setTrailers(ArrayList<BasicDataDriverTrailers> trailers) {
        this.trailers = trailers;
    }

    public String getLicense() {
        return this.license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getFull_name() {
        return this.full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public boolean isTo_be_logged_out() {
        return to_be_logged_out;
    }

    public boolean isActive() {
        return active;
    }

    public String getLicense_url() {
        return license_url;
    }

    public void setLicense_url(String license_url) {
        this.license_url = license_url;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }
}
