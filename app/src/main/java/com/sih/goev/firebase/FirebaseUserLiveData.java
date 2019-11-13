package com.sih.goev.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

public class FirebaseUserLiveData extends LiveData<FirebaseUser> {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            setValue(firebaseAuth.getCurrentUser());
        }
    };

    public FirebaseUserLiveData() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onActive() {
        firebaseAuth.addAuthStateListener(authStateListener);

    }

    @Override
    protected void onInactive() {
        firebaseAuth.removeAuthStateListener(authStateListener);
    }
}
