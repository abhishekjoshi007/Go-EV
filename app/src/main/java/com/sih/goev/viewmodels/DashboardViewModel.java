package com.sih.goev.viewmodels;

import com.google.firebase.auth.FirebaseAuth;
import com.sih.goev.entities.VehicleOwner;
import com.sih.goev.repository.VehicleOwnerRepository;
import com.sih.goev.utility.VehicleData;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private LiveData<VehicleOwner> vehicleOwner;
    private VehicleData vehicleData;

    public LiveData<VehicleOwner> getVehicleOwner() {
        if (vehicleOwner == null) {
            vehicleOwner = new VehicleOwnerRepository().getVehicleOwner(FirebaseAuth.getInstance().getCurrentUser().getUid());
        }
        return vehicleOwner;
    }

    public VehicleData getVehicleData() {
        return vehicleData;
    }

    public void setVehicleData(VehicleData vehicleData) {
        this.vehicleData = vehicleData;
    }
}
