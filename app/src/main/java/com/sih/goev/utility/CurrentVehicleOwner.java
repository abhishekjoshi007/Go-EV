package com.sih.goev.utility;

import com.google.firebase.auth.FirebaseUser;
import com.sih.goev.entities.VehicleOwner;
import com.sih.goev.firebase.FirebaseUserLiveData;
import com.sih.goev.repository.VehicleOwnerRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

public class CurrentVehicleOwner extends LiveData<VehicleOwner> {

    private static final CurrentVehicleOwner VEHICLE_OWNER = new CurrentVehicleOwner();
    private VehicleOwnerRepository vehicleOwnerRepository;
    private Observer<VehicleOwner> vehicleOwnerObserver = new Observer<VehicleOwner>() {
        @Override
        public void onChanged(VehicleOwner vehicleOwner) {
            setValue(vehicleOwner);
        }
    };
    private CurrentVehicleOwner() {
        new FirebaseUserLiveData().observeForever(new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser == null) {
                    setValue(null);
                    return;
                }
                vehicleOwnerRepository = new VehicleOwnerRepository();
                vehicleOwnerRepository.getVehicleOwner(firebaseUser.getUid()).observeForever(vehicleOwnerObserver);
            }
        });
    }

    public static CurrentVehicleOwner getInstance() {
        return VEHICLE_OWNER;
    }
}
