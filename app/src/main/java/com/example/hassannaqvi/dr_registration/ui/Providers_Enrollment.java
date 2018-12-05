package com.example.hassannaqvi.dr_registration.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.hassannaqvi.dr_registration.JSON.GeneratorClass;
import com.example.hassannaqvi.dr_registration.R;
import com.example.hassannaqvi.dr_registration.databinding.ActivityProvidersEnrollmentBinding;
import com.example.hassannaqvi.dr_registration.validation.validatorClass;

import org.json.JSONObject;

public class Providers_Enrollment extends AppCompatActivity {

    ActivityProvidersEnrollmentBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_providers__enrollment);
        bi.setCallback(this);
    }


    public void BtnContinue() {
        if (formValidation()) {
            SaveDraft();
            if (UpdateDB()) {
                startActivity(new Intent(getApplicationContext(), EndingActivity.class)
                        .putExtra("complete", true));
            } else {
                Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private boolean UpdateDB() {
        return true;
    }

    private void SaveDraft() {
        JSONObject Json = GeneratorClass.getContainerJSON(bi.fldgrppe01, true);
    }

    private boolean formValidation() {
        return validatorClass.EmptyCheckingContainer(this, bi.fldgrppe01);
    }
}
