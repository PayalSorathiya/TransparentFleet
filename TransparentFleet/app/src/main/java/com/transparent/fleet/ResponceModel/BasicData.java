package com.transparent.fleet.ResponceModel;

import java.util.ArrayList;

public class BasicData {
    private BasicDataDriver driver;
    private BasicDataTruck truck;
    private BasicDataCar car;
    private String status;
    private String message;
    private ArrayList<BasicDataTrailers> trailers;

    public BasicDataDriver getDriver() {
        return this.driver;
    }

    public void setDriver(BasicDataDriver driver) {
        this.driver = driver;
    }

    public BasicDataTruck getTruck() {
        return this.truck;
    }

    public void setTruck(BasicDataTruck truck) {
        this.truck = truck;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<BasicDataTrailers> getTrailers() {
        return this.trailers;
    }

    public void setTrailers(ArrayList<BasicDataTrailers> trailers) {
        this.trailers = trailers;
    }

    public BasicDataCar getCar() {
        return car;
    }

    public void setCar(BasicDataCar car) {
        this.car = car;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
