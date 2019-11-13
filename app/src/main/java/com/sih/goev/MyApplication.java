package com.sih.goev;

import android.app.Application;

import com.sih.goev.entities.VehicleOwner;
import com.sih.goev.utility.VehicleData;

public class MyApplication extends Application {

    private static VehicleOwner vehicleOwner;
    private static VehicleData vehicleData;

    public static VehicleOwner getVehicleOwner() {
        return vehicleOwner;
    }

    public static void setVehicleOwner(VehicleOwner vehicleOwner1) {
        vehicleOwner = vehicleOwner1;
    }

    public static VehicleData getVehicleData() {
        return vehicleData;
    }

    public static void setVehicleData(VehicleData vehicleData) {
        MyApplication.vehicleData = vehicleData;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        vehicleOwner = new VehicleOwner();
        vehicleData = new VehicleData();
    }
}
