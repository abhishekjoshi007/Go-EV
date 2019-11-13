package com.sih.goev.activities;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sih.goev.MyApplication;
import com.sih.goev.entities.VehicleOwner;
import com.sih.goev.repository.VehicleOwnerRepository;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    List<AuthUI.IdpConfig> providers = Collections.singletonList(
                            new AuthUI.IdpConfig.PhoneBuilder().build()
                    );
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(providers)
                                    .build(),
                            110);
                } else if (user.getDisplayName() == null || user.getDisplayName().length() < 1)
                    startActivity(new Intent(LoginActivity.this, EditProfileActivity.class));
                else {
                    setVehicleOwner(user);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 110 && data != null) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK && response != null) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user == null)
                    return;
                if (user.getDisplayName() == null || user.getDisplayName().length() < 1)
                    startActivity(new Intent(LoginActivity.this, EditProfileActivity.class));
                else {
                    setVehicleOwner(user);
                }
            }
        }
    }

    private void setVehicleOwner(FirebaseUser user) {
        new VehicleOwnerRepository().getVehicleOwner(user.getUid())
                .observe(LoginActivity.this, new Observer<VehicleOwner>() {
                    @Override
                    public void onChanged(VehicleOwner vehicleOwner) {
                        MyApplication.setVehicleOwner(vehicleOwner);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                });
    }
}
