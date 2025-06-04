package com.transparent.fleet.ResponceModel;

import java.util.ArrayList;

public class InspectionData {
    private ArrayList<InspectionDataAll_trailer_inspections> all_trailer_inspections;
    private ArrayList<InspectionDataAll_truck_inspections> all_truck_inspections;
    private ArrayList<InspectionDataTruck_inspections_today> truck_inspections_today;
    private ArrayList<InspectionDataTrailer_inspections_today> trailer_inspections_today;
    private ArrayList<InspectionDataCar_inspections_today> car_inspections_today;
    private ArrayList<InspectionDataAll_car_inspections> all_car_inspections;
    private String status;

    public ArrayList<InspectionDataAll_trailer_inspections> getAll_trailer_inspections() {
        return this.all_trailer_inspections;
    }

    public void setAll_trailer_inspections(ArrayList<InspectionDataAll_trailer_inspections> all_trailer_inspections) {
        this.all_trailer_inspections = all_trailer_inspections;
    }

    public ArrayList<InspectionDataAll_truck_inspections> getAll_truck_inspections() {
        return this.all_truck_inspections;
    }

    public void setAll_truck_inspections(ArrayList<InspectionDataAll_truck_inspections> all_truck_inspections) {
        this.all_truck_inspections = all_truck_inspections;
    }

    public ArrayList<InspectionDataTruck_inspections_today> getTruck_inspections_today() {
        return this.truck_inspections_today;
    }

    public void setTruck_inspections_today(ArrayList<InspectionDataTruck_inspections_today> truck_inspections_today) {
        this.truck_inspections_today = truck_inspections_today;
    }

    public ArrayList<InspectionDataTrailer_inspections_today> getTrailer_inspections_today() {
        return this.trailer_inspections_today;
    }

    public void setTrailer_inspections_today(ArrayList<InspectionDataTrailer_inspections_today> trailer_inspections_today) {
        this.trailer_inspections_today = trailer_inspections_today;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<InspectionDataCar_inspections_today> getCar_inspections_today() {
        return car_inspections_today;
    }

    public void setCar_inspections_today(ArrayList<InspectionDataCar_inspections_today> car_inspections_today) {
        this.car_inspections_today = car_inspections_today;
    }

    public ArrayList<InspectionDataAll_car_inspections> getAll_car_inspections() {
        return all_car_inspections;
    }

    public void setAll_car_inspections(ArrayList<InspectionDataAll_car_inspections> all_car_inspections) {
        this.all_car_inspections = all_car_inspections;
    }
}
