package com.transparent.fleet.ResponceModel;

import java.util.ArrayList;

public class FuelHistoryData {
    private ArrayList<FuelHistoryDataTrailer_fuel_histories> trailer_fuel_histories;
    private ArrayList<FuelHistoryDataTruck_fuel_histories> truck_fuel_histories;
    private String status;

    public ArrayList<FuelHistoryDataTrailer_fuel_histories> getTrailer_fuel_histories() {
        return this.trailer_fuel_histories;
    }

    public void setTrailer_fuel_histories(ArrayList<FuelHistoryDataTrailer_fuel_histories> trailer_fuel_histories) {
        this.trailer_fuel_histories = trailer_fuel_histories;
    }

    public ArrayList<FuelHistoryDataTruck_fuel_histories> getTruck_fuel_histories() {
        return this.truck_fuel_histories;
    }

    public void setTruck_fuel_histories(ArrayList<FuelHistoryDataTruck_fuel_histories> truck_fuel_histories) {
        this.truck_fuel_histories = truck_fuel_histories;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
