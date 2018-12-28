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
import com.example.hassannaqvi.dr_registration.contracts.HealthFacContract;
import com.example.hassannaqvi.dr_registration.core.DatabaseHelper;
import com.example.hassannaqvi.dr_registration.databinding.ActivityHfacilityBinding;
import com.example.hassannaqvi.dr_registration.validation.validatorClass;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HFacilityActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ActivityHfacilityBinding bi;
    DatabaseHelper db;
    Collection<HealthFacContract> colDATA;
    int hfCount = 0, count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUI();
        setUIListeners();
        setFunctionality();

    }

    private void setUI() {
        bi = DataBindingUtil.setContentView(this, R.layout.activity_hfacility);
        bi.setCallback(this);
        db = new DatabaseHelper(this);

        //Get Count HF
        hfCount = getIntent().getIntExtra("hf_count", 0);

    }

    public void BtnContinue() {

        if (!formValidation())
            return;

        SaveDraft();

        if (!UpdateDB()) {
            Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (count != hfCount) {
            count++;
            clearFields();
        } else {
            finish();
            startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true));
        }

    }


    private boolean UpdateDB() {
        return true;
    }

    private void SaveDraft() {
        JSONObject Json = GeneratorClass.getContainerJSON(bi.fldgrphf01, true);
    }

    private boolean formValidation() {
        return validatorClass.EmptyCheckingContainer(this, bi.fldgrphf01);
    }

    public void BtnEnd() {

        finish();
        startActivity(new Intent(this, EndingActivity.class).putExtra("complete", false));

    }

    private void clearFields() {

        bi.spProvince.setSelection(0);
        bi.spDistrict.setSelection(0);
        bi.spTehsil.setSelection(0);
        bi.spUC.setSelection(0);
        bi.spFacilityName.setSelection(0);

    }

    private void setUIListeners() {

        bi.spProvince.setOnItemSelectedListener(this);
        bi.spDistrict.setOnItemSelectedListener(this);
        bi.spTehsil.setOnItemSelectedListener(this);
        bi.spUC.setOnItemSelectedListener(this);
        bi.spFacilityName.setOnItemSelectedListener(this);

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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        int id = adapterView.getId();
        switch (id) {
            case R.id.spProvince:

                if (bi.spProvince.getSelectedItemPosition() == 0)
                    return;

//              Working on District Populating data
                colDATA = db.getHFData(new HealthFacContract.ColumnsClass(HealthFacContract.singleHF.COLUMN_HF_PROVINCE_NAME, bi.spProvince.getSelectedItem().toString()));
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

//              Working on Tehsil Populating data
                colDATA = db.getHFData(new HealthFacContract.ColumnsClass(HealthFacContract.singleHF.COLUMN_HF_PROVINCE_NAME, bi.spProvince.getSelectedItem().toString()),
                        new HealthFacContract.ColumnsClass(HealthFacContract.singleHF.COLUMN_HF_DISTRICT_NAME, bi.spDistrict.getSelectedItem().toString()));
                List<String> listTehsils = new ArrayList<>();
                listTehsils.add("....");
                for (HealthFacContract hf : colDATA) {
                    if (!listTehsils.contains(hf.getHf_teh_name())) {
                        listTehsils.add(hf.getHf_teh_name());
                    }
                }
                bi.spTehsil.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, listTehsils));

                break;

            case R.id.spTehsil:

                if (bi.spTehsil.getSelectedItemPosition() == 0)
                    return;

//              Working on UCs Populating data
                colDATA = db.getHFData(new HealthFacContract.ColumnsClass(HealthFacContract.singleHF.COLUMN_HF_PROVINCE_NAME, bi.spProvince.getSelectedItem().toString()),
                        new HealthFacContract.ColumnsClass(HealthFacContract.singleHF.COLUMN_HF_DISTRICT_NAME, bi.spDistrict.getSelectedItem().toString()),
                        new HealthFacContract.ColumnsClass(HealthFacContract.singleHF.COLUMN_HF_TEHSIL_NAME, bi.spTehsil.getSelectedItem().toString()));
                List<String> listUCs = new ArrayList<>();
                listUCs.add("....");
                for (HealthFacContract hf : colDATA) {
                    if (!listUCs.contains(hf.getHf_uc_name())) {
                        listUCs.add(hf.getHf_uc_name());
                    }
                }
                bi.spUC.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, listUCs));

                break;

            case R.id.spUC:

                if (bi.spUC.getSelectedItemPosition() == 0)
                    return;

//              Working on UCs Populating data
                colDATA = db.getHFData(new HealthFacContract.ColumnsClass(HealthFacContract.singleHF.COLUMN_HF_PROVINCE_NAME, bi.spProvince.getSelectedItem().toString()),
                        new HealthFacContract.ColumnsClass(HealthFacContract.singleHF.COLUMN_HF_DISTRICT_NAME, bi.spDistrict.getSelectedItem().toString()),
                        new HealthFacContract.ColumnsClass(HealthFacContract.singleHF.COLUMN_HF_TEHSIL_NAME, bi.spTehsil.getSelectedItem().toString()),
                        new HealthFacContract.ColumnsClass(HealthFacContract.singleHF.COLUMN_HF_UC_NAME, bi.spUC.getSelectedItem().toString()));
                List<String> listHF = new ArrayList<>();
                listHF.add("....");
                for (HealthFacContract hf : colDATA) {
                    if (!listHF.contains(hf.getHf_name())) {
                        listHF.add(hf.getHf_name());
                    }
                }
                bi.spFacilityName.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, listHF));

                break;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Sorry!! you can't go back.", Toast.LENGTH_SHORT).show();
    }
}
