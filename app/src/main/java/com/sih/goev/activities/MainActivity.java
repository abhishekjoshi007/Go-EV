package com.sih.goev.activities;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sih.goev.R;
import com.sih.goev.fragments.AccountFragment;
import com.sih.goev.fragments.AdvanceBookingFragment;
import com.sih.goev.fragments.DashboardFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private int currentFragId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                currentFragId = item.getItemId();
                switch (item.getItemId()) {
                    case R.id.navigation_dashboard:
                        if (currentFragId != 0 && currentFragId == R.id.navigation_dashboard)
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_layout, DashboardFragment.newInstance())
                                .commitNow();
                        break;
                    case R.id.navigation_profile:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_layout, AccountFragment.newInstance())
                                .commitNow();
                        break;
                    case R.id.navigation_advance_booking:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_layout, AdvanceBookingFragment.newInstance()).commitNow();
                        break;
                }
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.navigation_dashboard);
    }
}
