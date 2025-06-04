package com.transparent.fleet.ResponceModel;

public class AddFuelCarData {
    private String liters_fueled;
    private String total_km;
    private String message;
    private String on_date;
    private String status;

    public String getLiters_fueled() {
        return this.liters_fueled;
    }

    public void setLiters_fueled(String liters_fueled) {
        this.liters_fueled = liters_fueled;
    }

    public String getTotal_km() {
        return this.total_km;
    }

    public void setTotal_km(String total_km) {
        this.total_km = total_km;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
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
