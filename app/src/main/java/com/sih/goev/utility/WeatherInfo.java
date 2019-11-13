package com.sih.goev.utility;

public class WeatherInfo {
    private boolean isRain;
    private boolean isDay;
    private float temperature;
    private float airDensity;
    private float precipitation;
    private float outsideTemperature;

    public WeatherInfo(){}

    public boolean isRain() {
        return isRain;
    }

    public void setRain(boolean rain) {
        isRain = rain;
    }

    public boolean isDay() {
        return isDay;
    }

    public void setDay(boolean day) {
        isDay = day;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getAirDensity() {
        return airDensity;
    }

    public void setAirDensity(float airDensity) {
        this.airDensity = airDensity;
    }

    public float getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(float precipitation) {
        this.precipitation = precipitation;
    }

    public WeatherInfo(boolean isRain, boolean isDay, float temperature, float airDensity, float precipitation, float outsideTemperature) {
        this.isRain = isRain;
        this.isDay = isDay;
        this.temperature = temperature;
        this.airDensity = airDensity;
        this.precipitation = precipitation;
        this.outsideTemperature = outsideTemperature;
    }

    public float getOutsideTemperature() {
        return outsideTemperature;
    }

    public void setOutsideTemperature(float outsideTemperature) {
        this.outsideTemperature = outsideTemperature;
    }
}