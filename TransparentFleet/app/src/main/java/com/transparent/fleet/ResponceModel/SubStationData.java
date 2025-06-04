package com.transparent.fleet.ResponceModel;

import java.util.ArrayList;

public class SubStationData {
    private ArrayList<SubStationDataSub_fuel_stations> sub_fuel_stations;
    private String status;
    private String message;

    public ArrayList<SubStationDataSub_fuel_stations> getSub_fuel_stations() {
        return this.sub_fuel_stations;
    }

    public void setSub_fuel_stations(ArrayList<SubStationDataSub_fuel_stations> sub_fuel_stations) {
        this.sub_fuel_stations = sub_fuel_stations;
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
