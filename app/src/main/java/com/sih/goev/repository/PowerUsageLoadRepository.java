package com.sih.goev.repository;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sih.goev.entities.PowerUsageLoad;
import com.sih.goev.entities.Vehicle;
import com.sih.goev.firebase.FirebaseQuery;

import java.util.ArrayList;
import java.util.List;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

public class PowerUsageLoadRepository {

    public PowerUsageLoadRepository() {
    }

    public LiveData<List<PowerUsageLoad>> getPowerUsageLoadList() {
        CollectionReference reference = FirebaseFirestore.getInstance().collection("power_usage_load");
        FirebaseQuery firebaseQuery = new FirebaseQuery(reference);

        LiveData<List<PowerUsageLoad>> powerUsageLoadList;
        powerUsageLoadList = Transformations.map(firebaseQuery, new Function<QuerySnapshot, List<PowerUsageLoad>>() {
            @Override
            public List<PowerUsageLoad> apply(QuerySnapshot input) {
                List<PowerUsageLoad> powerUsageLoads = new ArrayList<>();
                for (QueryDocumentSnapshot queryDocumentSnapshot : input) {
                    powerUsageLoads.add(queryDocumentSnapshot.toObject(PowerUsageLoad.class));
                }
                return powerUsageLoads;
            }
        });
        return powerUsageLoadList;
    }
}
