package com.example.hassannaqvi.dr_registration.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.hassannaqvi.dr_registration.R;
import com.example.hassannaqvi.dr_registration.databinding.ActivityHfacilityBinding;

public class HFacilityActivity extends AppCompatActivity {

    ActivityHfacilityBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_hfacility);
        bi.setCallback(this);

    }

    public void BtnContinue() {

    }

    public void BtnEnd() {

    }
}
