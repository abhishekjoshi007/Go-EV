package com.sih.goev.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sih.goev.R;
import com.sih.goev.entities.VehicleOwner;
import com.sih.goev.utility.CurrentVehicleOwner;
import com.sih.goev.viewmodels.AccountViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class AccountFragment extends Fragment {

    private TextView phoneNumberTextView;
    private TextView displayNameTextView;
    private TextView vehicleModelTextView;

    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_fragment, container, false);
        phoneNumberTextView = view.findViewById(R.id.phone_number);
        displayNameTextView = view.findViewById(R.id.display_name);
        vehicleModelTextView = view.findViewById(R.id.vehicle_model);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Profile");
        }
        AccountViewModel accountViewModel = ViewModelProviders.of(this).get(AccountViewModel.class);
        CurrentVehicleOwner.getInstance().observe(this, new Observer<VehicleOwner>() {
            @Override
            public void onChanged(VehicleOwner vehicleOwner) {
                phoneNumberTextView.setText(vehicleOwner.getPrimaryPhoneNumber());
                displayNameTextView.setText(vehicleOwner.getFullName());
                vehicleModelTextView.setText(vehicleOwner.getVehicle().getModel());
            }
        });
    }
}
