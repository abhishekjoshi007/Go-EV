package com.sih.goev.utility;

public class Load {

    private float loadCurrentPerPart;
    private float loadPerPart;
    private float numberOfParts;
    private float totalLoadCurrent;
    private float totalLoad;
    private float rainyDayDutyCycle;
    private float rainyNightDutyCycle;

    public Load(float loadCurrentPerPart, float loadPerPart, float numberOfParts, float totalLoadCurrent, float totalLoad, float rainyDayDutyCycle, float rainyNightDutyCycle) {
        this.loadCurrentPerPart = loadCurrentPerPart;
        this.loadPerPart = loadPerPart;
        this.numberOfParts = numberOfParts;
        this.totalLoadCurrent = totalLoadCurrent;
        this.totalLoad = totalLoad;
        this.rainyDayDutyCycle = rainyDayDutyCycle;
        this.rainyNightDutyCycle = rainyNightDutyCycle;
    }

    public float getLoadCurrentPerPart() {
        return loadCurrentPerPart;
    }

    public void setLoadCurrentPerPart(float loadCurrentPerPart) {
        this.loadCurrentPerPart = loadCurrentPerPart;
    }

    public float getLoadPerPart() {
        return loadPerPart;
    }

    public void setLoadPerPart(float loadPerPart) {
        this.loadPerPart = loadPerPart;
    }

    public float getNumberOfParts() {
        return numberOfParts;
    }

    public void setNumberOfParts(float numberOfParts) {
        this.numberOfParts = numberOfParts;
    }

    public float getTotalLoadCurrent() {
        return totalLoadCurrent;
    }

    public void setTotalLoadCurrent(float totalLoadCurrent) {
        this.totalLoadCurrent = totalLoadCurrent;
    }

    public float getTotalLoad() {
        return totalLoad;
    }

    public void setTotalLoad(float totalLoad) {
        this.totalLoad = totalLoad;
    }

    public float getRainyDayDutyCycle() {
        return rainyDayDutyCycle;
    }

    public void setRainyDayDutyCycle(float rainyDayDutyCycle) {
        this.rainyDayDutyCycle = rainyDayDutyCycle;
    }

    public float getRainyNightDutyCycle() {
        return rainyNightDutyCycle;
    }

    public void setRainyNightDutyCycle(float rainyNightDutyCycle) {
        this.rainyNightDutyCycle = rainyNightDutyCycle;
    }
}
