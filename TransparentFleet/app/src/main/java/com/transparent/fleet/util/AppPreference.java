package com.transparent.fleet.util;

import android.content.Context;


public class AppPreference {

    private static String SAVE_USER_DATA = "saveUserID";
    private static String USER_NAME = "username";
    private static String USER_TOKEN = "userToken";
    private static String IS_USER_TOKEN = "isUserToken";
    private static String IS_TRUCK = "isTruck";


    public static String HOME_NOTIFICATION = "homenotification";


    private Context context;

    public static String token = "token";
    public static String USERIMG = "userimg";
    public static String TRUCKID = "truckId";
    public static String CARID = "carId";
    public static String TRAILERID = "trailerId";
    public static String TRAILERPOS = "trailerPos";
    public static String DRIVERID = "driverId";
    public static String FLEETAVG = "fleetAvg";
    public static String USERAVG = "userAvg";
    public static String USER_KM = "userKm";
    public static String USER_HOUR = "userHour";

    public AppPreference(Context context) {
        this.context = context;
    }

    public void saveUserName(String userData) {
        context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE).edit().putString(USER_NAME, userData).commit();
    }


    public String getUserName() {
        return context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE).getString(USER_NAME, "");
    }

    public void saveUserImage(String userimg) {
        context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE).edit().putString(USERIMG, userimg).commit();
    }


    public String getUserImage() {
        return context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE).getString(USERIMG, "");
    }

    public void saveTruckId(String truckId) {
        context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE).edit().putString(TRUCKID, truckId).commit();
    }


    public String getTruckId() {
        return context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE).getString(TRUCKID, "");
    }

    public void saveCarId(String truckId) {
        context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE).edit().putString(CARID, truckId).commit();
    }


    public String getCarId() {
        return context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE).getString(CARID, "");
    }

    public void saveTrailerId(String trailerId) {
        context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE).edit().putString(TRAILERID, trailerId).commit();
    }


    public String getTrailerId() {
        return context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE).getString(TRAILERID, "");
    }

    public void saveTrailerPos(int trailerPos) {
        context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE).edit().putInt(TRAILERPOS, trailerPos).commit();
    }


    public int getTrailerPos() {
        return context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE).getInt(TRAILERPOS, 0);
    }

    public void saveDriverId(String driverId) {
        context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE).edit().putString(DRIVERID, driverId).commit();
    }


    public String getDriverId() {
        return context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE).getString(DRIVERID, "");
    }

    public void saveFleetAvg(String fleetAvg) {
        context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE).edit().putString(FLEETAVG, fleetAvg).commit();
    }


    public String getFleetAvg() {
        return context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE).getString(FLEETAVG, "");
    }

    public void saveUserAvg(String userAvg) {
        context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE).edit().putString(USERAVG, userAvg).commit();
    }


    public String getUserAvg() {
        return context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE).getString(USERAVG, "");
    }

    public void saveUserKm(long userKm) {
        context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE).edit().putLong(USER_KM, userKm).commit();
    }


    public long getUserKm() {
        return context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE).getLong(USER_KM, 0);
    }

    public void saveUserHour(long userHour) {
        context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE).edit().putLong(USER_HOUR, userHour).commit();
    }


    public long getUserHour() {
        return context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE).getLong(USER_HOUR, 0);
    }

    public void saveNotifiction(String notifiction) {
        context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE).edit().putString(HOME_NOTIFICATION, notifiction).commit();
    }


    public String getNotifiction() {
        return context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE).getString(HOME_NOTIFICATION, "");
    }


    public boolean isTokenAvailable() {
        return context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE).getBoolean(IS_USER_TOKEN, false);
    }

    public void saveIsTokenAvailable(boolean isToken) {
        (context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE)).edit().putBoolean(IS_USER_TOKEN, isToken).commit();

    }

    public boolean isTruck() {
        return context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE).getBoolean(IS_TRUCK, false);
    }

    public void saveIsTruck(boolean isToken) {
        (context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE)).edit().putBoolean(IS_TRUCK, isToken).commit();

    }

    public void saveToken(String token) {
        (context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE)).edit().putString(USER_TOKEN, token).commit();
    }

    public String getToken() {
        return context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE).getString(USER_TOKEN, "");
    }

    public boolean isUserLoggedIn() {
        if (getToken() == null || getToken().length() == 0)
            return false;
        else
            return true;
    }


    /**
     * Clears the User's Data Stored in Preferences
     */
    public void clearUserPrefs() {
        clearUserData();

    }


    public void clearUserData() {
        (context.getSharedPreferences(SAVE_USER_DATA, Context.MODE_PRIVATE)).edit().clear().commit();
    }


}
