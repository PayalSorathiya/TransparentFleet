package com.transparent.fleet.ResponceModel;

import java.util.ArrayList;

public class FuelStationData {
    private ArrayList<FuelStationDataFuel_stations> fuel_stations;
    private String status;
    private String message;

    public ArrayList<FuelStationDataFuel_stations> getFuel_stations() {
        return this.fuel_stations;
    }

    public void setFuel_stations(ArrayList<FuelStationDataFuel_stations> fuel_stations) {
        this.fuel_stations = fuel_stations;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
