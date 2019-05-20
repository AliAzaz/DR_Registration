package com.example.hassannaqvi.dr_registration.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hassannaqvi.dr_registration.JSON.GeneratorClass;
import com.example.hassannaqvi.dr_registration.R;
import com.example.hassannaqvi.dr_registration.contracts.HealthFacContract;
import com.example.hassannaqvi.dr_registration.contracts.HealthFacContract.ColumnsClass;
import com.example.hassannaqvi.dr_registration.contracts.HealthFacContract.singleHF;
import com.example.hassannaqvi.dr_registration.core.DatabaseHelper;
import com.example.hassannaqvi.dr_registration.databinding.ActivityProvidersEnrollmentBinding;
import com.example.hassannaqvi.dr_registration.validation.validatorClass;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Providers_Enrollment extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ActivityProvidersEnrollmentBinding bi;
    DatabaseHelper db;
    Collection<HealthFacContract> colDATA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUI();
        setUIListeners();
        setFunctionality();
    }

    private void setUI() {
        bi = DataBindingUtil.setContentView(this, R.layout.activity_providers__enrollment);
        bi.setCallback(this);
        db = new DatabaseHelper(this);
    }

    private void setUIListeners() {

        bi.spProvince.setOnItemSelectedListener(this);
        bi.spDistrict.setOnItemSelectedListener(this);
        bi.spVillage.setOnItemSelectedListener(this);

//        bi.edWorking.addTextChangedListener(generalTextWatcher);

        bi.edWorking.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!bi.edWorking.getText().toString().isEmpty())
                    bi.btnPrSubmit.setText("NEXT SECTION");
                else
                    bi.btnPrSubmit.setText("END SECTION");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void setFunctionality() {

//      Working on Province Populating data
        colDATA = db.getHFData();
        List<String> listPrv = new ArrayList<>();
        listPrv.add("....");
        for (HealthFacContract hf : colDATA) {
            if (!listPrv.contains(hf.getHf_prv_name())) {
                listPrv.add(hf.getHf_prv_name());
            }
        }
        bi.spProvince.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listPrv));


    }


    private TextWatcher generalTextWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (bi.edWorking.getText().hashCode() == s.hashCode()) {
                if (bi.edWorking.getText().toString().trim().length() > 0 && Integer.parseInt(bi.edWorking.getText().toString().trim()) > 0) {
                    final AlertDialog b = new AlertDialog.Builder(Providers_Enrollment.this).create();

                    LayoutInflater layoutInflater = getLayoutInflater();
                    final View v = layoutInflater.inflate(R.layout.dialog_facility_selection, null);
                    b.setView(v);
                    b.setCancelable(true);
                    b.show();

                    Spinner spProvinceHF = v.findViewById(R.id.spProvinceHF);
                    Spinner spDistrictHF = v.findViewById(R.id.spDistrictHF);
                    Spinner spTehsilHF = v.findViewById(R.id.spTehsilHF);
                    Spinner spUC = v.findViewById(R.id.spUC);
                    Spinner spFacilityName = v.findViewById(R.id.spFacilityName);

                    Spinner.OnItemSelectedListener spx = new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    };

                    spProvinceHF.setOnItemSelectedListener(spx);
                    spDistrictHF.setOnItemSelectedListener(spx);
                    spTehsilHF.setOnItemSelectedListener(spx);
                    spUC.setOnItemSelectedListener(spx);
                    spFacilityName.setOnItemSelectedListener(spx);

                   /* bi.spProvince.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) dataDistricts);{

                        int id = adapterView.getId();
                        switch (id) {
                            case R.id.spProvince:

                                if (bi.spProvince.getSelectedItem().toString().equals("select"))
                                    break;

                                dataDistricts = Districts.get(bi.spProvince.getSelectedItem().toString());
                                ArrayAdapter<String> dataAdapterD = new ArrayAdapter<String>(this,
                                        android.R.layout.simple_spinner_item, dataDistricts);

                                dataAdapterD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                bi.spDistrict.setAdapter(dataAdapterD);

                                bi.spDistrict.setSelection(0);
                                break;

                        }
                    } *//*else {
                    //ll_A4178_2.setVisibility(View.VISIBLE);
                }*/
                }
            }
        }
    };

    public void BtnContinue() {
        if (formValidation()) {
            SaveDraft();
            if (UpdateDB()) {
                startActivity(new Intent(getApplicationContext(), !bi.edWorking.getText().toString().isEmpty() ? HFacilityActivity.class : EndingActivity.class)
                        .putExtra("hf_count", Integer.valueOf(bi.edWorking.getText().toString()))
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

                if (bi.spProvince.getSelectedItemPosition() == 0)
                    return;

//              Working on District Populating data
                colDATA = db.getHFData(new ColumnsClass(singleHF.COLUMN_HF_PROVINCE_NAME, bi.spProvince.getSelectedItem().toString()));
                List<String> listDistrict = new ArrayList<>();
                listDistrict.add("....");
                for (HealthFacContract hf : colDATA) {
                    if (!listDistrict.contains(hf.getHf_dist_name())) {
                        listDistrict.add(hf.getHf_dist_name());
                    }
                }
                bi.spDistrict.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, listDistrict));

                break;

            case R.id.spDistrict:

                if (bi.spDistrict.getSelectedItemPosition() == 0)
                    return;

//              Working on District Populating data
                colDATA = db.getHFData(new ColumnsClass(singleHF.COLUMN_HF_PROVINCE_NAME, bi.spProvince.getSelectedItem().toString()),
                        new ColumnsClass(singleHF.COLUMN_HF_DISTRICT_NAME, bi.spDistrict.getSelectedItem().toString()));
                List<String> listVillages = new ArrayList<>();
                listVillages.add("....");
                for (HealthFacContract hf : colDATA) {
                    if (!listVillages.contains(hf.getHf_uc_name())) {
                        listVillages.add(hf.getHf_uc_name());
                    }
                }
                bi.spVillage.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, listVillages));

                break;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
