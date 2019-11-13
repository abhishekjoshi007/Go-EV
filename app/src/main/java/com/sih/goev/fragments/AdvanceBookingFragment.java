package com.sih.goev.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sih.goev.R;
import com.sih.goev.viewmodels.AdvanceBookingViewModel;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


public class AdvanceBookingFragment extends Fragment implements OnMapReadyCallback {

    public static AdvanceBookingFragment newInstance() {
        return new AdvanceBookingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.advance_booking_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Advance Booking");
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        double lat = 13.369;
        double lng = 74.78;
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(new LatLng(lat, lng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 300));
    }
}
