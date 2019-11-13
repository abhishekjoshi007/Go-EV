package com.sih.goev.viewmodels;

import com.sih.goev.entities.Vehicle;
import com.sih.goev.repository.VehicleRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class EditProfileViewModel extends ViewModel {

    private LiveData<List<Vehicle>> vehicleList;

    public LiveData<List<Vehicle>> getVehicleList() {
        if (vehicleList == null) {
            vehicleList = new VehicleRepository().getVehicleList();
        }
        return vehicleList;
    }
}
