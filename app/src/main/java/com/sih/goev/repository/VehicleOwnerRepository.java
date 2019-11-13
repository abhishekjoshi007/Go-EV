package com.sih.goev.repository;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sih.goev.entities.VehicleOwner;
import com.sih.goev.firebase.FirebaseDocument;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

public class VehicleOwnerRepository {

    public VehicleOwnerRepository() {
    }

    public LiveData<VehicleOwner> getVehicleOwner(String uid) {
        DocumentReference reference = FirebaseFirestore.getInstance().collection("vehicle_owners").document(uid);
        FirebaseDocument firebaseDocument = new FirebaseDocument(reference);

        LiveData<VehicleOwner> vehicleOwner;
        vehicleOwner = Transformations.map(firebaseDocument, new Function<DocumentSnapshot, VehicleOwner>() {
            @Override
            public VehicleOwner apply(DocumentSnapshot input) {
                return input.toObject(VehicleOwner.class);
            }
        });
        return vehicleOwner;
    }
}
