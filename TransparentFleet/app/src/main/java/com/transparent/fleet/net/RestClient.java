package com.transparent.fleet.net;

import com.transparent.fleet.ResponceModel.AddFuelCarData;
import com.transparent.fleet.ResponceModel.AddFuelTrailerData;
import com.transparent.fleet.ResponceModel.AddFuelTruckData;
import com.transparent.fleet.ResponceModel.BasicData;
import com.transparent.fleet.ResponceModel.CarFuelHistory;
import com.transparent.fleet.ResponceModel.FuelHistoryData;
import com.transparent.fleet.ResponceModel.FuelStationData;
import com.transparent.fleet.ResponceModel.HomeData;
import com.transparent.fleet.ResponceModel.InspectionData;
import com.transparent.fleet.ResponceModel.LoginData;
import com.transparent.fleet.ResponceModel.ServiceData;
import com.transparent.fleet.ResponceModel.SubStationData;
import com.transparent.fleet.ResponceModel.SuccessData;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;

public interface RestClient {
    @FormUrlEncoded
    @POST("/auth/login")
    void loginAPi(@Field("grant_type") String grant_type,
                  @Field("client_id") String client_id,
                  @Field("client_secret") String client_secret,
                  @Field("username") String username,
                  @Field("password") String password,
                  @Field("scope") String scope,
                  Callback<LoginData> callback);

    @FormUrlEncoded
    @POST("/auth/logout")
    void logoutApi(@Field("test") String test,
                   Callback<SuccessData> callback);

    @FormUrlEncoded
    @POST("/auth/get-home-notifications")
    void homeDataAPi(@Field("truck_id") String truck_id,
                     @Field("trailer_id") String trailer_id,
                     @Field("car_id") String car_id,
                     Callback<HomeData> callback);

    @FormUrlEncoded
    @POST("/auth/get-basic-data")
    void basicListApi(@Field("test") String test,
                      Callback<BasicData> callback);

    @FormUrlEncoded
    @POST("/auth/get-fuel-stations")
    void fuelStationApi(@Field("test") String test,
                        Callback<FuelStationData> callback);

    @FormUrlEncoded
    @POST("/auth/get-sub-fuel-stations")
    void fuelSubStationApi(@Field("fuel_station_id") String fuel_station_id,
                           Callback<SubStationData> callback);

    @FormUrlEncoded
    @POST("/auth/add-truck-fuel")
    void addTruckFuelApi(@Field("truck_id") String truck_id,
                         @Field("fuel_station_id") String fuel_station_id,
                         @Field("liters_fueled") String liters_fueled,
                         @Field("total_km") String total_km,
                         Callback<AddFuelTruckData> callback);

    @FormUrlEncoded
    @POST("/auth/add-car-fuel")
    void addCarFuelApi(@Field("car_id") String truck_id,
                       @Field("fuel_station_id") String fuel_station_id,
                       @Field("liters_fueled") String liters_fueled,
                       @Field("total_km") String total_km,
                       Callback<AddFuelCarData> callback);

    @FormUrlEncoded
    @POST("/auth/add-trailer-fuel")
    void addTrailerFuelApi(@Field("trailer_id") String trailer_id,
                           @Field("fuel_station_id") String fuel_station_id,
                           @Field("liters_fueled") String liters_fueled,
                           @Field("total_hours") String total_hours,
                           Callback<AddFuelTrailerData> callback);

    @FormUrlEncoded
    @POST("/auth/get-fuel-histories")
    void FuelHistoryApi(@Field("trailer_id") String trailer_id,
                        @Field("truck_id") String truck_id,
                        Callback<FuelHistoryData> callback);

    @FormUrlEncoded
    @POST("/auth/get-fuel-histories")
    void FuelHistoryCarApi(@Field("car_id") String car_id,
                           Callback<CarFuelHistory> callback);

    @FormUrlEncoded
    @POST("/auth/get-inspections")
    void inspectionApi(@Field("trailer_id") String trailer_id,
                       @Field("truck_id") String truck_id,
                       @Field("car_id") String car_id,
                       Callback<InspectionData> callback);

    @FormUrlEncoded
    @POST("/auth/get-services")
    void serviceApi(@Field("trailer_id") String trailer_id,
                    @Field("truck_id") String truck_id,
                    @Field("car_id") String car_id,
                    Callback<ServiceData> callback);


}
