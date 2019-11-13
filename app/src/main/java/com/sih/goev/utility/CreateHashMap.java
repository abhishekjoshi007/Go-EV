package com.sih.goev.utility;

import java.util.HashMap;

public class CreateHashMap {

    public static HashMap<String, Load> loadData() {
        HashMap<String, Load> loadMap;
        Load horn = new Load(3.2f, 43.5f, 2f, 6.4f, 87f, 10f, 10f);
        Load parkingLamps = new Load(2.4f, 33f, 1f, 2.4f, 33f, 50f, 100f);
        Load frontFogLamp = new Load(2.3f, 30.5f, 2f, 4.5f, 61f, 30f, 100f);
        Load stopLamps = new Load(1.6f, 21.7f, 3f, 4.8f, 65f, 40f, 40f);
        Load headLampHighBeam = new Load(4.8f, 64.5f, 2f, 9.6f, 129f, 10f, 10f);
        Load hazard = new Load(8f, 108f, 1f, 8f, 108, 10, 10);
        Load frontWiper = new Load(5.6f, 75f, 1f, 5.6f, 75f, 50, 80);
        Load frontBlower = new Load(17.6f, 237f, 1f, 17.6f, 237f, 100f, 90f);
        Load audioSystem = new Load(4f, 54f, 1f, 4f, 54f, 90f, 80f);
        Load frontWasher = new Load(1.5f, 20f, 1f, 1.5f, 20f, 10f, 10f);

        loadMap = new HashMap<>();
        loadMap.put("horn", horn);
        loadMap.put("parkingLamps", parkingLamps);
        loadMap.put("frontFogLamp", frontFogLamp);
        loadMap.put("stopLamps", stopLamps);
        loadMap.put("headLampHighBeam", headLampHighBeam);
        loadMap.put("hazard", hazard);
        loadMap.put("frontWiper", frontWiper);
        loadMap.put("frontBlower", frontBlower);
        loadMap.put("audioSystem", audioSystem);
        loadMap.put("frontWasher", frontWasher);

        return loadMap;
    }
}
