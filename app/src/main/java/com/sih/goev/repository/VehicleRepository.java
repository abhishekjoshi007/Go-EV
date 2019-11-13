package com.sih.goev.repository;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sih.goev.entities.Vehicle;
import com.sih.goev.firebase.FirebaseQuery;

import java.util.ArrayList;
import java.util.List;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

public class VehicleRepository {

    public VehicleRepository() {
    }

    public LiveData<List<Vehicle>> getVehicleList() {
        CollectionReference reference = FirebaseFirestore.getInstance().collection("vehicle_data");
        FirebaseQuery firebaseQuery = new FirebaseQuery(reference);

        LiveData<List<Vehicle>> vehicleList;
        vehicleList = Transformations.map(firebaseQuery, new Function<QuerySnapshot, List<Vehicle>>() {
            @Override
            public List<Vehicle> apply(QuerySnapshot input) {
                List<Vehicle> vehicleArrayList = new ArrayList<>();
                for (QueryDocumentSnapshot queryDocumentSnapshot : input) {
                    vehicleArrayList.add(queryDocumentSnapshot.toObject(Vehicle.class));
                }
                return vehicleArrayList;
            }
        });
        return vehicleList;
    }
}
