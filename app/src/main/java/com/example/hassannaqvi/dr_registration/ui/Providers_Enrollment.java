package com.example.hassannaqvi.dr_registration.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.hassannaqvi.dr_registration.JSON.GeneratorClass;
import com.example.hassannaqvi.dr_registration.R;
import com.example.hassannaqvi.dr_registration.databinding.ActivityProvidersEnrollmentBinding;
import com.example.hassannaqvi.dr_registration.utils.Districts;
import com.example.hassannaqvi.dr_registration.utils.VC;
import com.example.hassannaqvi.dr_registration.validation.validatorClass;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Providers_Enrollment extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ActivityProvidersEnrollmentBinding bi;
    List<String> dataDistricts = new ArrayList<>();
    List<String> dataTehsil = new ArrayList<>();
    List<String> dataUC = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_providers__enrollment);
        bi.setCallback(this);

        bi.spProvince.setOnItemSelectedListener(this);
        bi.spDistrict.setOnItemSelectedListener(this);
        bi.spVC.setOnItemSelectedListener(this);
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


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        int id = adapterView.getId();
        switch (id) {
            case R.id.spProvince:

                if (bi.spProvince.getSelectedItem().toString().equals("...."))
                    break;

                dataDistricts = Districts.get(bi.spProvince.getSelectedItem().toString());
                ArrayAdapter<String> dataAdapterD = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, dataDistricts);

                dataAdapterD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                bi.spDistrict.setAdapter(dataAdapterD);

                bi.spDistrict.setSelection(0);
                bi.spVC.setAdapter(null);
                break;

            case R.id.spDistrict:
                if (bi.spDistrict.getSelectedItem().toString().equals("...."))
                    break;
                dataTehsil = VC.get(bi.spDistrict.getSelectedItem().toString());
                dataAdapterD = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataTehsil);
                dataAdapterD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                bi.spVC.setAdapter(dataAdapterD);

                bi.spVC.setSelection(0);
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
