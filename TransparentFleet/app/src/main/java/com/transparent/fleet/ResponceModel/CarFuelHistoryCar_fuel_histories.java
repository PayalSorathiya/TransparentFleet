package com.transparent.fleet.ResponceModel;

public class CarFuelHistoryCar_fuel_histories {
    private String updated_at;
    private String user_id;
    private String liters_fueled;
    private String fuel_location_id;
    private String created_at;
    private String id;
    private String on_date;
    private String car_id;
    private String on_km;
    private CarFuelHistoryCar_fuel_historiesFuel_location fuel_location;

    public String getUpdated_at() {
        return this.updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getUser_id() {
        return this.user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLiters_fueled() {
        return this.liters_fueled;
    }

    public void setLiters_fueled(String liters_fueled) {
        this.liters_fueled = liters_fueled;
    }

    public String getFuel_location_id() {
        return this.fuel_location_id;
    }

    public void setFuel_location_id(String fuel_location_id) {
        this.fuel_location_id = fuel_location_id;
    }

    public String getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOn_date() {
        return this.on_date;
    }

    public void setOn_date(String on_date) {
        this.on_date = on_date;
    }

    public String getCar_id() {
        return this.car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    public String getOn_km() {
        return this.on_km;
    }

    public void setOn_km(String on_km) {
        this.on_km = on_km;
    }

    public CarFuelHistoryCar_fuel_historiesFuel_location getFuel_location() {
        return this.fuel_location;
    }

    public void setFuel_location(CarFuelHistoryCar_fuel_historiesFuel_location fuel_location) {
        this.fuel_location = fuel_location;
    }
}
