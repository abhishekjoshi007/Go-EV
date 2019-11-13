package com.sih.goev.entities;

public class PowerUsageLoad {

    private float loadCurrentPerPart;
    private int loadPerPart;
    private LoadType loadType;
    private int numberOfParts;
    private int rainyDayDutyCycle;
    private int rainyNightDutyCycle;


    public float getLoadCurrentPerPart() {
        return loadCurrentPerPart;
    }

    public void setLoadCurrentPerPart(float loadCurrentPerPart) {
        this.loadCurrentPerPart = loadCurrentPerPart;
    }

    public int getLoadPerPart() {
        return loadPerPart;
    }

    public void setLoadPerPart(int loadPerPart) {
        this.loadPerPart = loadPerPart;
    }

    public LoadType getLoadType() {
        return loadType;
    }

    public void setLoadType(LoadType loadType) {
        this.loadType = loadType;
    }

    public int getNumberOfParts() {
        return numberOfParts;
    }

    public void setNumberOfParts(int numberOfParts) {
        this.numberOfParts = numberOfParts;
    }

    public int getRainyDayDutyCycle() {
        return rainyDayDutyCycle;
    }

    public void setRainyDayDutyCycle(int rainyDayDutyCycle) {
        this.rainyDayDutyCycle = rainyDayDutyCycle;
    }

    public int getRainyNightDutyCycle() {
        return rainyNightDutyCycle;
    }

    public void setRainyNightDutyCycle(int rainyNightDutyCycle) {
        this.rainyNightDutyCycle = rainyNightDutyCycle;
    }
}
