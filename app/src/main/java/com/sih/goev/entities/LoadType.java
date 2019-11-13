package com.sih.goev.entities;

public enum LoadType {

    HEAD_LAMP_HIGH_BEAM, STOP_LAMP_HMSL, AUDIO_SYSTEM, FRONT_FOG_LAMP,
    PARKING_LAMP, HAZARD_INDICATOR_LAMPS, FRONT_WIPER, FRONT_BLOWER,
    HORN, FRONT_WASHER;

    public String getDisplayName() {
        switch (this) {
            case HEAD_LAMP_HIGH_BEAM:
                return "Head Lamp High Beam";
            case STOP_LAMP_HMSL:
                return "Stop Lamp + HMSL";
            case AUDIO_SYSTEM:
                return "Audio System";
            case FRONT_FOG_LAMP:
                return "Front Fog Lamp";
            case PARKING_LAMP:
                return "Parking Lamp";
            case HAZARD_INDICATOR_LAMPS:
                return "Hazard Indicator Lamps";
            case FRONT_WIPER:
                return "Front Wiper";
            case FRONT_BLOWER:
                return "Front Blower";
            case HORN:
                return "Horn";
            case FRONT_WASHER:
                return "Front Washer";
            default:
                return "wrong value";
        }
    }
}
