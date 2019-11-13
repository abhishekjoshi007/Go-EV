package com.sih.goev.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.sih.goev.R;
import com.sih.goev.entities.Vehicle;
import com.sih.goev.entities.VehicleOwner;
import com.sih.goev.viewmodels.EditProfileViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class EditProfileActivity extends AppCompatActivity {


    private Spinner vehicleModelSpinner;
    private TextInputEditText firstNameEditText;
    private TextInputEditText lastNameEditText;
    private VehicleOwner vehicleOwner;
    private List<Vehicle> vehicleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile_activity);
        vehicleOwner = new VehicleOwner();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        firstNameEditText = findViewById(R.id.first_name);
        lastNameEditText = findViewById(R.id.last_name);
        vehicleModelSpinner = findViewById(R.id.vehicle_model);

        EditProfileViewModel editProfileViewModel = ViewModelProviders.of(this).get(EditProfileViewModel.class);
        editProfileViewModel.getVehicleList().observe(this, new Observer<List<Vehicle>>() {
            @Override
            public void onChanged(List<Vehicle> vehicles) {
                vehicleList = vehicles;
                ArrayList<String> vehicleModelArrayList = new ArrayList<>();
                vehicleModelArrayList.add("Choose a vehicle model");
                for (Vehicle vehicle : vehicles) {
                    vehicleModelArrayList.add(vehicle.getModel() + "  " + vehicle.getVariant());
                }
                ArrayAdapter<String> vehicleModelAdapter = new ArrayAdapter<>(EditProfileActivity.this, R.layout.vehicle_model_spinner_item, vehicleModelArrayList);
                vehicleModelAdapter.setDropDownViewResource(R.layout.vehicle_model_drop_down_list_item);
                vehicleModelSpinner.setAdapter(vehicleModelAdapter);
            }
        });
        FirebaseAuth.getInstance().addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null)
                    finish();
                vehicleOwner.setUid(firebaseAuth.getCurrentUser().getUid());
                vehicleOwner.setPrimaryPhoneNumber(firebaseAuth.getCurrentUser().getPhoneNumber());
            }
        });

        vehicleModelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    vehicleOwner.setVehicle(null);
                    return;
                }
                vehicleOwner.setVehicle(vehicleList.get(position - 1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.submit_button:
                String firstName = "";
                String lastName = "";
                if (firstNameEditText.getText() != null)
                    firstName = firstNameEditText.getText().toString();
                if (firstName.length() < 2) {
                    Toast.makeText(this, "Enter a valid name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (lastNameEditText.getText() != null) {
                    lastName = lastNameEditText.getText().toString();
                }

                if (lastName.length() == 1) {
                    Toast.makeText(this, "Enter a valid name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (vehicleOwner.getVehicle() == null) {
                    Toast.makeText(this, "Choose a vehicle model", Toast.LENGTH_SHORT).show();
                    return;
                }

                vehicleOwner.setFirstName(firstName);
                vehicleOwner.setLastName(lastName);
                vehicleOwner.setFullName(firstName + " " + lastName);

                Toast.makeText(this, "Updating profile...", Toast.LENGTH_SHORT).show();
                view.setEnabled(false);

                UserProfileChangeRequest changeRequest = new UserProfileChangeRequest.Builder()
                        .setDisplayName(vehicleOwner.getFullName())
                        .build();
                Task<Void> updateUserTask = FirebaseFirestore.getInstance()
                        .document("vehicle_owners/" + vehicleOwner.getUid())
                        .set(vehicleOwner, SetOptions.merge());
                Task<Void> updateProfileTask = FirebaseAuth.getInstance().getCurrentUser().updateProfile(changeRequest);
                Task<Void> allTask = Tasks.whenAll(updateUserTask, updateProfileTask);

                Toast.makeText(this, "Updating you information...", Toast.LENGTH_SHORT).show();
                allTask.addOnCompleteListener(EditProfileActivity.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(EditProfileActivity.this, "Profile Updated. Cheers!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditProfileActivity.this, MainActivity.class));
                        finish();
                        Toast.makeText(EditProfileActivity.this, "Cheers! Your profile is updated...", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }
}
