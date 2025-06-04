package com.transparent.fleet.ResponceModel;

public class AddFuelTrailerData {
    private String liters_fueled;
    private String message;
    private String total_hours;
    private String on_date;
    private String status;

    public String getLiters_fueled() {
        return this.liters_fueled;
    }

    public void setLiters_fueled(String liters_fueled) {
        this.liters_fueled = liters_fueled;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTotal_hours() {
        return this.total_hours;
    }

    public void setTotal_hours(String total_hours) {
        this.total_hours = total_hours;
    }

    public String getOn_date() {
        return this.on_date;
    }

    public void setOn_date(String on_date) {
        this.on_date = on_date;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
