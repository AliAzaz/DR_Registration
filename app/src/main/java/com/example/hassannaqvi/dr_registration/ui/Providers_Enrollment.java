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
        setInitialization();
        setFunctionality();
    }

    public void setUI() {
        bi = DataBindingUtil.setContentView(this, R.layout.activity_providers__enrollment);
        bi.setCallback(this);
        db = new DatabaseHelper(this);
    }

    public void setUIListeners() {

        bi.spProvince.setOnItemSelectedListener(this);
        bi.spDistrict.setOnItemSelectedListener(this);
        bi.spVillage.setOnItemSelectedListener(this);

        bi.edWorking.addTextChangedListener(generalTextWatcher);

    }

    public void setInitialization() {
        List<String> listPrv, listDist, listVil;
        listPrv = new ArrayList<>();
        listDist = new ArrayList<>();
        listVil = new ArrayList<>();
    }

    public void setFunctionality() {

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
                startActivity(new Intent(getApplicationContext(), EndingActivity.class)
                        .putExtra("complete", true));
            } else {
                Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    /*if (view.getId() == R.id.edWorking) {

        if (phase.size() > 8) {
            Toast.makeText(A4251_A4284.this, "Cannot be more then 9 ", Toast.LENGTH_LONG).show();


            DBHelper db = new DBHelper(this);
            Cursor res = db.getData3("A4252_atributes", study_id);


            if (res.getCount() > 1) {


                Toast.makeText(this, "" + res.getCount(), Toast.LENGTH_LONG).show();

                res.moveToFirst();


            }

            return;
        }

        final AlertDialog b = new AlertDialog.Builder(this).create();

        LayoutInflater layoutInflater = getLayoutInflater();
        final View v = layoutInflater.inflate(R.layout.dialog_tt, null);
        b.setView(v);
        b.setCancelable(false);
        b.show();

        Button btnsbt = v.findViewById(R.id.btn_sbt);

        Button btnCancel = v.findViewById(R.id.btn_cncl);

        LinearLayout ll4_A4252 = v.findViewById(R.id.ll4_A4252);

        final LinearLayout ll5_A4252 = v.findViewById(R.id.ll5_A4252);
        final LinearLayout ll2_A4252 = v.findViewById(R.id.ll2_A4252);
        final LinearLayout ll3_A4252 = v.findViewById(R.id.ll3_A4252);


        final RadioButton

                rb_A4252_1_1,
                rb_A4252_1_2,
                rb_A4252_1_3,
                rb_A4252_2_1,
                rb_A4252_2_2,
                rb_A4252_2_3,
                rb_A4252_2_4,
                rb_A4252_2_5,
                rb_A4252_2_6,
                rb_A4252_2_7;

        rb_A4252_1_1 = v.findViewById(R.id.rb_A4252_1_1);
        rb_A4252_1_2 = v.findViewById(R.id.rb_A4252_1_2);
        rb_A4252_1_3 = v.findViewById(R.id.rb_A4252_1_3);
        rb_A4252_2_1 = v.findViewById(R.id.rb_A4252_2_1);
        rb_A4252_2_2 = v.findViewById(R.id.rb_A4252_2_2);
        rb_A4252_2_3 = v.findViewById(R.id.rb_A4252_2_3);
        rb_A4252_2_4 = v.findViewById(R.id.rb_A4252_2_4);
        rb_A4252_2_5 = v.findViewById(R.id.rb_A4252_2_5);
        rb_A4252_2_6 = v.findViewById(R.id.rb_A4252_2_6);
        rb_A4252_2_7 = v.findViewById(R.id.rb_A4252_2_7);

        final CheckBox cb_place = v.findViewById(R.id.cb_A4252_2_8);

        final EditText ed_symt = v.findViewById(R.id.ed_A4252);


        if (b_place_where == true) {

            ll4_A4252.setVisibility(View.GONE);
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // A4251_A4284.super.onBackPressed();


                b.cancel();
            }
        });

        btnsbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    *//*if (rb_A4251_1.isChecked()) {
                        ClearAllcontrol(ll)
                    }*//*


                if (Gothrough.IamHiden(ll2_A4252) == false || Gothrough.IamHiden(ll3_A4252) == false || Gothrough.IamHiden(ll5_A4252) == false) {
                    Toast.makeText(A4251_A4284.this, "Select All", Toast.LENGTH_LONG).show();


                    return;
                }


                if (rb_A4252_1_1.isChecked()) {

                    if (Collections.frequency(phase, "1") > 2) {
                        Toast.makeText(A4251_A4284.this, "Cannot be more then 3 ", Toast.LENGTH_LONG).show();

                        return;
                    }
                    phase.add("1");

                } else if (rb_A4252_1_2.isChecked()) {


                    if (Collections.frequency(phase, "2") > 2) {
                        Toast.makeText(A4251_A4284.this, "Cannot be more then 3 ", Toast.LENGTH_LONG).show();

                        return;
                    }

                    phase.add("2");
                } else if (rb_A4252_1_3.isChecked()) {

                    if (Collections.frequency(phase, "3") > 2) {
                        Toast.makeText(A4251_A4284.this, "Cannot be more then 3 ", Toast.LENGTH_LONG).show();

                        return;
                    }

                    phase.add("3");
                } else {
                    Toast.makeText(A4251_A4284.this, "Please Fill Question A4252 (Card)", Toast.LENGTH_LONG).show();

                    return;
                }


                if (rb_A4252_2_4.isChecked() || rb_A4252_2_5.isChecked() || rb_A4252_2_6.isChecked() || rb_A4252_2_7.isChecked()) {

                    check = check + 1;
                    Toast.makeText(getApplicationContext(), "" + check, Toast.LENGTH_LONG).show();
                }

                if (rb_A4252_2_1.isChecked()) {
                    care.add("1");
                } else if (rb_A4252_2_2.isChecked()) {
                    care.add("2");
                } else if (rb_A4252_2_3.isChecked()) {
                    care.add("3");
                } else if (rb_A4252_2_4.isChecked()) {
                    care.add("4");
                } else if (rb_A4252_2_5.isChecked()) {
                    care.add("5");
                } else if (rb_A4252_2_6.isChecked()) {
                    care.add("6");
                } else if (rb_A4252_2_7.isChecked()) {
                    care.add("7");
                } else {
                    // toast

                    return;
                }

                if (cb_place.isChecked()) {
                    b_place_where = true;

                    place.add("1");
                    cb_place.setChecked(false);
                } else {
                    place.add("0");
                }

                if (ed_symt.getText().toString().trim().length() > 0) {
                    symtomps.add(ed_symt.getText().toString().trim());
                } else {
                    ed_symt.requestFocus();
                    ed_symt.setError("Enter Symtoms");
                    return;
                }


                A4252_start.setText("Start (" + Collections.frequency(phase, "1") + ")");
                A4252_mid.setText("Mid (" + Collections.frequency(phase, "2") + ")");
                A4252_End.setText("End (" + Collections.frequency(phase, "3") + ")");
                btn_addd.setText("ADD  (" + phase.size() + ")");


                    *//*List<String> careL = new ArrayList();


                    careL.add("4");
                    careL.add("5");
                    careL.add("6");
                    careL.add("7");
*//*
                if (care.contains("4") || care.contains("5") || care.contains("6") || care.contains("7")) {
                    ll_A4253.setVisibility(View.VISIBLE);
                    ll_A4254_2.setVisibility(View.VISIBLE);
                    ClearAllcontrol.ClearAll(ll_A4254_1);
                    ll_A4254_1.setVisibility(View.GONE);
                } else {
                    ClearAllcontrol.ClearAll(ll_A4253);
                    ClearAllcontrol.ClearAll(ll_A4254_2);

                    ll_A4253.setVisibility(View.GONE);
                    ll_A4254_2.setVisibility(View.GONE);
                    ll_A4254_1.setVisibility(View.VISIBLE);
                }

                b.cancel();

            }

        });


    }*/

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

            case R.id.spVillage:

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
