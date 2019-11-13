package com.sih.goev.entities;

public class Vehicle {

    private int batteryCapacity;
    private String batteryType;
    private String bodyType;
    private int drivingRange;
    private int kerbWeight;
    private int grossWeight;
    private String model;
    private String motorType;
    private int noOfCells;
    private int noOfModules;
    private int onBoardPower;
    private int power;
    private int powerRPM;
    private int revive;
    private int seatingCapacity;
    private int torque;
    private int torqRPM;
    private String variant;
    private String vehicleType;


    public Vehicle() {
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public int getDrivingRange() {
        return drivingRange;
    }

    public void setDrivingRange(int drivingRange) {
        this.drivingRange = drivingRange;
    }

    public int getKerbWeight() {
        return kerbWeight;
    }

    public void setKerbWeight(int kerbWeight) {
        this.kerbWeight = kerbWeight;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMotorType() {
        return motorType;
    }

    public void setMotorType(String motorType) {
        this.motorType = motorType;
    }

    public int getNoOfCells() {
        return noOfCells;
    }

    public void setNoOfCells(int noOfCells) {
        this.noOfCells = noOfCells;
    }

    public int getNoOfModules() {
        return noOfModules;
    }

    public void setNoOfModules(int noOfModules) {
        this.noOfModules = noOfModules;
    }

    public int getOnBoardPower() {
        return onBoardPower;
    }

    public void setOnBoardPower(int onBoardPower) {
        this.onBoardPower = onBoardPower;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPowerRPM() {
        return powerRPM;
    }

    public void setPowerRPM(int powerRPM) {
        this.powerRPM = powerRPM;
    }

    public int getRevive() {
        return revive;
    }

    public void setRevive(int revive) {
        this.revive = revive;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public int getTorque() {
        return torque;
    }

    public void setTorque(int torque) {
        this.torque = torque;
    }

    public int getTorqRPM() {
        return torqRPM;
    }

    public void setTorqRPM(int torqRPM) {
        this.torqRPM = torqRPM;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        if (!model.equals(vehicle.model)) return false;
        return variant.equals(vehicle.variant);
    }

    @Override
    public int hashCode() {
        int result = model.hashCode();
        result = 31 * result + variant.hashCode();
        return result;
    }

    public int getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(int grossWeight) {
        this.grossWeight = grossWeight;
    }
}
