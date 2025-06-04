package com.transparent.fleet.ResponceModel;

import java.util.ArrayList;

public class CarFuelHistory {
    private ArrayList<CarFuelHistoryCar_fuel_histories> car_fuel_histories;
    private String status;

    public ArrayList<CarFuelHistoryCar_fuel_histories> getCar_fuel_histories() {
        return this.car_fuel_histories;
    }

    public void setCar_fuel_histories(ArrayList<CarFuelHistoryCar_fuel_histories> car_fuel_histories) {
        this.car_fuel_histories = car_fuel_histories;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
