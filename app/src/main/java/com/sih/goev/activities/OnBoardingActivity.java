package com.sih.goev.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.ramotion.paperonboarding.PaperOnboardingEngine;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener;
import com.sih.goev.R;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import static com.sih.goev.R.id.onboardingRootView;

public class OnBoardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding_main_layout);

        PaperOnboardingPage page1 = new PaperOnboardingPage("Reliable",
                getString(R.string.reliability_text),
                Color.parseColor("#61AAB6"),
                R.drawable.ic_reliability, R.drawable.ic_thumb_up_black_24dp);

        PaperOnboardingPage page2 = new PaperOnboardingPage("Easy To Navigate",
                getString(R.string.easy_to_navigate_text),
                Color.parseColor("#B6D7E0"),
                R.drawable.ic_navigation, R.drawable.ic_near_me_black_24dp);

        PaperOnboardingPage page3 = new PaperOnboardingPage("Faster Payments",
                getString(R.string.faster_payments),
                Color.parseColor("#9E92C1"),
                R.drawable.ic_easy_payment, R.drawable.ic_payment_black_24dp);

        ArrayList<PaperOnboardingPage> pages = new ArrayList<>();

        pages.add(page1);
        pages.add(page2);
        pages.add(page3);

        PaperOnboardingEngine engine = new PaperOnboardingEngine(findViewById(onboardingRootView), pages, getApplicationContext());

        engine.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
            @Override
            public void onRightOut() {
                ProgressDialog progressDialog = new ProgressDialog(OnBoardingActivity.this);
                progressDialog.setMessage("Please waite...");
                progressDialog.show();
                startActivity(new Intent(OnBoardingActivity.this, LoginActivity.class));
                finish();
            }
        });

    }
}
