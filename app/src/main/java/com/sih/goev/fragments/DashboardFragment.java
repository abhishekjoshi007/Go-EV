package com.sih.goev.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.sih.goev.MyApplication;
import com.sih.goev.R;
import com.sih.goev.utility.RangeEstimation;
import com.sih.goev.utility.VehicleData;
import com.sih.goev.utility.WeatherInfo;

import org.modelmapper.ModelMapper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class DashboardFragment extends Fragment {


    private TextView vehicleName;
    private TextView batteryPercentageTextView;
    private SeekBar batteryPercentageSeekBar;
    private ProgressBar batteryPercentageProgressBar;
    private SeekBar numberOfPassengersSeekBar;
    private CheckBox isAcOn;
    private CheckBox isWiperOn;
    private CheckBox isAudioOn;
    private CheckBox isHeadLamp;
    private TextView acLevelLabelTextView;
    private SeekBar acLevelSeekBar;
    private TextView numberOfPassengersTextView;
    private TextView acLevelTextView;
    private TextView rangeLeftTextView;

    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard_fragment, container, false);

        vehicleName = view.findViewById(R.id.vehicle_name);
        batteryPercentageTextView = view.findViewById(R.id.battery_percentage);
        batteryPercentageSeekBar = view.findViewById(R.id.battery_percentage_seek_bar);
        batteryPercentageProgressBar = view.findViewById(R.id.battery_progressbar);
        numberOfPassengersSeekBar = view.findViewById(R.id.number_of_passenger_seek_bar);
        numberOfPassengersTextView = view.findViewById(R.id.number_of_passengers);
        vehicleName = view.findViewById(R.id.vehicle_name);
        isAcOn = view.findViewById(R.id.is_ac_on_check_box);
        isAudioOn = view.findViewById(R.id.is_audio_on);
        isHeadLamp = view.findViewById(R.id.is_head_available);
        isWiperOn = view.findViewById(R.id.is_wiper_on);
        acLevelLabelTextView = view.findViewById(R.id.ac_level_label);
        acLevelSeekBar = view.findViewById(R.id.ac_level_seek_bar);
        acLevelTextView = view.findViewById(R.id.ac_level);
        rangeLeftTextView = view.findViewById(R.id.range_left);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Dashboard");
        }
        ModelMapper modelMapper = new ModelMapper();
        VehicleData vehicleData = modelMapper.map(MyApplication.getVehicleOwner().getVehicle(), VehicleData.class);
        MyApplication.setVehicleData(vehicleData);
        numberOfPassengersSeekBar.setMax(MyApplication.getVehicleOwner().getVehicle().getSeatingCapacity());
        vehicleName.setText(MyApplication.getVehicleOwner().getVehicle().getModel());
        addListeners();
    }

    private void addListeners() {
        batteryPercentageSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                MyApplication.getVehicleData().setBatteryPercentage(progress);
                batteryPercentageTextView.setText(String.valueOf(progress));
                batteryPercentageProgressBar.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                updateData();
            }
        });
        numberOfPassengersSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress < 1) {
                    numberOfPassengersSeekBar.setProgress(1);
                    return;
                }
                MyApplication.getVehicleData().setNumberOfPassengers(progress);
                numberOfPassengersTextView.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                updateData();
            }
        });

        acLevelSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                MyApplication.getVehicleData().setAcLevel(progress);
                acLevelTextView.setText(String.valueOf(progress));
                updateData();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                updateData();
            }
        });

        isAcOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    acLevelLabelTextView.setVisibility(View.VISIBLE);
                    acLevelSeekBar.setVisibility(View.VISIBLE);
                    acLevelTextView.setVisibility(View.VISIBLE);
                    MyApplication.getVehicleData().setAcLevel(acLevelSeekBar.getProgress());

                } else {
                    acLevelLabelTextView.setVisibility(View.GONE);
                    acLevelSeekBar.setVisibility(View.GONE);
                    acLevelTextView.setVisibility(View.GONE);
                    MyApplication.getVehicleData().setAcLevel(0);
                }
                updateData();
            }
        });
        isWiperOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MyApplication.getVehicleData().setWiperOn(isChecked);
                updateData();
            }
        });

        isAudioOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                MyApplication.getVehicleData().setAudioOn(isChecked);
                updateData();
            }
        });

        isHeadLamp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MyApplication.getVehicleData().setHeadLampOn(isChecked);
                updateData();
            }
        });

        batteryPercentageSeekBar.setProgress(MyApplication.getVehicleData().getBatteryPercentage());
        numberOfPassengersSeekBar.setProgress(MyApplication.getVehicleData().getNumberOfPassengers());
        acLevelSeekBar.setProgress(MyApplication.getVehicleData().getAcLevel());
        isWiperOn.setChecked(MyApplication.getVehicleData().isWiperOn());
        isAudioOn.setChecked(MyApplication.getVehicleData().isAudioOn());
        isHeadLamp.setChecked(MyApplication.getVehicleData().isHeadLampOn());

    }


    private void updateData() {
        float range = RangeEstimation.getRange(new WeatherInfo(), MyApplication.getVehicleData());
        rangeLeftTextView.setText(String.valueOf((int) range));
    }

}
