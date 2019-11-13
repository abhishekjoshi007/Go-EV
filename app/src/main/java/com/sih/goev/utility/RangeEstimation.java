package com.sih.goev.utility;

import com.sih.goev.entities.Vehicle;

import java.util.HashMap;

public class RangeEstimation {

    private static float actualCapacity = -1.0f;
    private static float result = -1.0f;
    private static float avgSpeed = -1.0f;

    private static float kineticEnergy = 0.0f;
    private static float aerodynamicDragLoss = 0.0f;
    private static float rollingResistanceWastage = 0.0f;

    private static float Cr;

    private static float wiperWhpk = 0.0f;
    private static float hornWhpk = 0.0f;
    private static float parkingLampsWhpk = 0.0f;
    private static float frontFogLampWhpk = 0.0f;
    private static float stopLampsWhpk = 0.0f;
    private static float headLampHighBeamWhpk = 0.0f;
    private static float hazardWhpk = 0.0f;
    private static float audioSystemWhpk = 0.0f;
    private static float frontWasherWhpk = 0.0f;
    private static float blowerWhpk = 0.0f;
    private static float motorWhpk = 0.0f;
    private static float acWhpk = 0.0f;

    private static float requiredWhpk = 0.0f;
    private static HashMap<String, Load> loadMap;


    private static float potentialEnergy;
    private static float weightOverall;

    public static float getRange(WeatherInfo weatherInfo, VehicleData vehicleData) {


        float temperature = weatherInfo.getTemperature();
        actualCapacity = (float) (((float) vehicleData.getBatteryPercentage() / 100) * (0.7) * (1000 * vehicleData.getOnBoardPower()));

        //just the avg value, we will change it later if necessary
        if (vehicleData.getVehicleType().equalsIgnoreCase("Car")) {
            avgSpeed = 40f;
            Cr = 0.009f;
        } else if (vehicleData.getVehicleType().equalsIgnoreCase("Van")) {
            avgSpeed = 35f;
            Cr = 0.008f;
        } else if (vehicleData.getVehicleType().equalsIgnoreCase("Auto")) {
            avgSpeed = 40f;
            Cr = 0.009f;
        } else {
            avgSpeed = 20f;
            Cr = 0.007f;
        }

        loadMap = CreateHashMap.loadData();

        if (weatherInfo.isRain()) {
            Load load = loadMap.get("frontWiper");
            wiperWhpk = load.getTotalLoad() * (1 / avgSpeed) * (((weatherInfo.isDay()) ? load.getRainyDayDutyCycle() : load.getRainyNightDutyCycle()) / 100);
        }

        {
            Load load = loadMap.get("horn");
            hornWhpk = load.getTotalLoad() * (1 / avgSpeed) * (((weatherInfo.isDay()) ? load.getRainyDayDutyCycle() : load.getRainyNightDutyCycle()) / 100);
        }

        {
            Load load = loadMap.get("parkingLamps");
            parkingLampsWhpk = load.getTotalLoad() * (1 / avgSpeed) * (((weatherInfo.isDay()) ? load.getRainyDayDutyCycle() : load.getRainyNightDutyCycle()) / 100);
        }

        {
            Load load = loadMap.get("frontFogLamp");
            frontFogLampWhpk = load.getTotalLoad() * (1 / avgSpeed) * (((weatherInfo.isDay()) ? load.getRainyDayDutyCycle() : load.getRainyNightDutyCycle()) / 100);
        }
        {
            Load load = loadMap.get("stopLamps");
            stopLampsWhpk = load.getTotalLoad() * (1 / avgSpeed) * (((weatherInfo.isDay()) ? load.getRainyDayDutyCycle() : load.getRainyNightDutyCycle()) / 100);
        }
        if (!weatherInfo.isDay()) {
            Load load = loadMap.get("headLampHighBeam");
            headLampHighBeamWhpk = load.getTotalLoad() * (1 / avgSpeed) * (((weatherInfo.isDay()) ? load.getRainyDayDutyCycle() : load.getRainyNightDutyCycle()) / 100);
        }

        {
            Load load = loadMap.get("hazard");
            hazardWhpk = load.getTotalLoad() * (1 / avgSpeed) * (((weatherInfo.isDay()) ? load.getRainyDayDutyCycle() : load.getRainyNightDutyCycle()) / 100);
        }
        {
            Load load = loadMap.get("frontBlower");
            blowerWhpk = load.getTotalLoad() * (1 / avgSpeed) * (((weatherInfo.isDay()) ? load.getRainyDayDutyCycle() : load.getRainyNightDutyCycle()) / 100);
        }
        {
            Load load = loadMap.get("audioSystem");
            audioSystemWhpk = load.getTotalLoad() * (1 / avgSpeed) * (((weatherInfo.isDay()) ? load.getRainyDayDutyCycle() : load.getRainyNightDutyCycle()) / 100);
        }
        {
            Load load = loadMap.get("frontWasher");
            frontWasherWhpk = load.getTotalLoad() * (1 / avgSpeed) * (((weatherInfo.isDay()) ? load.getRainyDayDutyCycle() : load.getRainyNightDutyCycle()) / 100);
        }

        motorWhpk = vehicleData.getPower() * (1 / avgSpeed);

        // air drag to be considered and the data regarding that to be inserted in firebase

        // rolling resistance

        requiredWhpk = wiperWhpk + hornWhpk + parkingLampsWhpk + frontFogLampWhpk + stopLampsWhpk + headLampHighBeamWhpk + hazardWhpk + blowerWhpk + audioSystemWhpk + frontWasherWhpk + motorWhpk;

        //adding aerp drag:
        //float aeroForce = 0.5 *

        //main calculation
        //result = actualCapacity / requiredWhpk;
        //return result;


        result = ((actualCapacity - requiredWhpk) / (vehicleData.getKerbWeight() + vehicleData.getNumberOfPassengers() * 65)) * 10;

        if(vehicleData.isWiperOn()){
            result = result - (result / 30);
        }
        if(vehicleData.isAudioOn()){
            result  = result - (result / 20);
        }
        if(vehicleData.isHeadLampOn()){
            result = result - (result / 20);
        }
        result = result - (((float)vehicleData.getAcLevel()) / 10) * result;

        if(result < 0){
            result = 0;
        }

        return result;

        /*
        result = vehicleData.getDrivingRange() * ((float) vehicleData.getBatteryPercentage() / 100);
        return result;
        */
    }

    public static float getRangeMain(WeatherInfo weatherInfo, VehicleData vehicleData, Route  route){

        weightOverall = (((float)(vehicleData.getGrossWeight() - vehicleData.getKerbWeight())) / vehicleData.getNumberOfPassengers()) * vehicleData.getNumberOfPassengers() + vehicleData.getKerbWeight();


        //potential energy

        /*
        float potentialEnergyLocal;

        float divisionGap = 20f;
        for (RouteSegment routeSegment: route.getRouteSegments()
            ) {
            float segmentLength = routeSegment.getSegmentLength();
            int gaps = (int)(segmentLength / divisionGap);
            float remainingGap = segmentLength % divisionGap;

        }
        */

        potentialEnergy = weightOverall * MapsIntegration.getHeight(route
                .getStart() , route.getEnd());

        //kinetic energy
        float gap = 20;
        kineticEnergy = (float)((0.5 * weightOverall / 9.8) *  MapsIntegration.getVelocitySquare(route.getStart(), route.getEnd()));

        //aerodynamic drag
        //aerodynamicDragLoss = 0.5 * weatherInfo.getAirDensity() *

        //rolling resistance
        float coefficientDry = vehicleData.getRollingCoefficientDry();
        float forceRolling = (coefficientDry + weatherInfo.getPrecipitation() * (vehicleData.getRollingCoefficientDry() - vehicleData.getRollingCoefficientWet())) * weightOverall;

        rollingResistanceWastage = forceRolling * route.getRouteLength();


        return 0.0f;

    }

}
