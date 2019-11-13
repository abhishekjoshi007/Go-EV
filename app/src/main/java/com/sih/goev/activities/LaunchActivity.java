package com.sih.goev.activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sih.goev.MyApplication;
import com.sih.goev.entities.VehicleOwner;
import com.sih.goev.repository.VehicleOwnerRepository;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

public class LaunchActivity extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    startActivity(new Intent(LaunchActivity.this, OnBoardingActivity.class));
                    finish();
                } else if (user.getDisplayName() == null || user.getDisplayName().length() < 1) {
                    startActivity(new Intent(LaunchActivity.this, EditProfileActivity.class));
                    finish();
                }
                else {
                    new VehicleOwnerRepository().getVehicleOwner(user.getUid())
                            .observe(LaunchActivity.this, new Observer<VehicleOwner>() {
                                @Override
                                public void onChanged(VehicleOwner vehicleOwner) {
                                    MyApplication.setVehicleOwner(vehicleOwner);
                                    startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                                    finish();
                                }
                            });
                }
            }
        };
        FirebaseAuth.getInstance().addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().removeAuthStateListener(authStateListener);
    }
}
