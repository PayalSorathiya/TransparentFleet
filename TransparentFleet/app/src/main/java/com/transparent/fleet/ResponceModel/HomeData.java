package com.transparent.fleet.ResponceModel;

public class HomeData {
    private String fleet_average;
    private String your_average;
    private String[] notifications;
    private String status;
    private String message;

    public String getFleet_average() {
        return this.fleet_average;
    }

    public void setFleet_average(String fleet_average) {
        this.fleet_average = fleet_average;
    }

    public String getYour_average() {
        return this.your_average;
    }

    public void setYour_average(String your_average) {
        this.your_average = your_average;
    }

    public String[] getNotifications() {
        return this.notifications;
    }

    public void setNotifications(String[] notifications) {
        this.notifications = notifications;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
