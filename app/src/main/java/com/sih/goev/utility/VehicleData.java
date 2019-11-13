package com.sih.goev.utility;

import com.sih.goev.entities.Vehicle;

public class VehicleData extends Vehicle {

    private int batteryPercentage;
    private int numberOfPassengers;
    private int acLevel;

    private boolean isWiperOn;
    private boolean isHeadLampOn;
    private boolean isAudioOn;

    private float rollingCoefficientDry;
    private float rollingCoefficientWet;
    private float energyPerDegreeCelsiusForHeating;

    public VehicleData() {
        batteryPercentage = 100;
        numberOfPassengers = 1;
        acLevel = 2;
        isWiperOn = false;
        isHeadLampOn = false;
        isAudioOn = false;

    }

    public int getBatteryPercentage() {
        return batteryPercentage;
    }

    public void setBatteryPercentage(int batteryPercentage) {
        this.batteryPercentage = batteryPercentage;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public int getAcLevel() {
        return acLevel;
    }

    public void setAcLevel(int acLevel) {
        this.acLevel = acLevel;
    }

    public float getRollingCoefficientDry() {
        return rollingCoefficientDry;
    }

    public void setRollingCoefficientDry(float rollingCoefficientDry) {
        this.rollingCoefficientDry = rollingCoefficientDry;
    }

    public float getRollingCoefficientWet() {
        return rollingCoefficientWet;
    }

    public void setRollingCoefficientWet(float rollingCoefficientWet) {
        this.rollingCoefficientWet = rollingCoefficientWet;
    }

    public float getEnergyPerDegreeCelsiusForHeating() {
        return energyPerDegreeCelsiusForHeating;
    }

    public void setEnergyPerDegreeCelsiusForHeating(float energyPerDegreeCelsiusForHeating) {
        this.energyPerDegreeCelsiusForHeating = energyPerDegreeCelsiusForHeating;
    }

    public boolean isWiperOn() {
        return isWiperOn;
    }

    public void setWiperOn(boolean wiperOn) {
        isWiperOn = wiperOn;
    }

    public boolean isHeadLampOn() {
        return isHeadLampOn;
    }

    public void setHeadLampOn(boolean headLampOn) {
        isHeadLampOn = headLampOn;
    }

    public boolean isAudioOn() {
        return isAudioOn;
    }

    public void setAudioOn(boolean audioOn) {
        isAudioOn = audioOn;
    }
}