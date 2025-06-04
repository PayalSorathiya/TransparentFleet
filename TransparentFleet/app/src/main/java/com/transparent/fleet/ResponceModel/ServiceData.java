package com.transparent.fleet.ResponceModel;

import java.util.ArrayList;

public class ServiceData {
    private ArrayList<ServiceDataAll_truck_services> all_truck_services;
    private ArrayList<ServiceDataAll_trailer_services> all_trailer_services;
    private ArrayList<ServiceDataTruck_services_today> truck_services_today;
    private ArrayList<ServiceDataTrailer_services_today> trailer_services_today;

    private ArrayList<ServiceDataCar_service_today> car_services_today;
    private ArrayList<ServiceDataAll_car_service> all_car_services;
    private String status;

    public ArrayList<ServiceDataAll_truck_services> getAll_truck_services() {
        return this.all_truck_services;
    }

    public void setAll_truck_services(ArrayList<ServiceDataAll_truck_services> all_truck_services) {
        this.all_truck_services = all_truck_services;
    }

    public ArrayList<ServiceDataAll_trailer_services> getAll_trailer_services() {
        return this.all_trailer_services;
    }

    public void setAll_trailer_services(ArrayList<ServiceDataAll_trailer_services> all_trailer_services) {
        this.all_trailer_services = all_trailer_services;
    }

    public ArrayList<ServiceDataTruck_services_today> getTruck_services_today() {
        return this.truck_services_today;
    }

    public void setTruck_services_today(ArrayList<ServiceDataTruck_services_today> truck_services_today) {
        this.truck_services_today = truck_services_today;
    }

    public ArrayList<ServiceDataTrailer_services_today> getTrailer_services_today() {
        return this.trailer_services_today;
    }

    public void setTrailer_services_today(ArrayList<ServiceDataTrailer_services_today> trailer_services_today) {
        this.trailer_services_today = trailer_services_today;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<ServiceDataCar_service_today> getCar_services_today() {
        return car_services_today;
    }

    public void setCar_services_today(ArrayList<ServiceDataCar_service_today> car_services_today) {
        this.car_services_today = car_services_today;
    }

    public ArrayList<ServiceDataAll_car_service> getAll_car_services() {
        return all_car_services;
    }

    public void setAll_car_services(ArrayList<ServiceDataAll_car_service> all_car_services) {
        this.all_car_services = all_car_services;
    }
}
