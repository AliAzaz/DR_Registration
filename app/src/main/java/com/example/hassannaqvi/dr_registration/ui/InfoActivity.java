package com.example.hassannaqvi.dr_registration.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hassannaqvi.dr_registration.JSON.GeneratorClass;
import com.example.hassannaqvi.dr_registration.R;
import com.example.hassannaqvi.dr_registration.databinding.ActivityInfoBinding;
import com.example.hassannaqvi.dr_registration.validation.ClearClass;
import com.example.hassannaqvi.dr_registration.validation.validatorClass;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.example.hassannaqvi.dr_registration.ui.LoginActivity.db;

public class InfoActivity extends AppCompatActivity {

    private static final String TAG = InfoActivity.class.getName();
    ActivityInfoBinding bi;
    String fTYPE = "", fExt = "", deviceID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_info);
        bi.setCallback(this);
        deviceID = Settings.Secure.getString(InfoActivity.this.getContentResolver(), Settings.Secure.ANDROID_ID);
        setContentUI();
    }

    private void setContentUI() {

        /*Setting BlackBox date picker*/
        bi.lsid5.setManager(getSupportFragmentManager());
        bi.lsid5.setMaxDate(new SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis()));
        bi.lsid14.setManager(getSupportFragmentManager());
        bi.lsid14.setMaxDate(new SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis()));

        /*Setting edittext*/
        bi.lsid7m.setManager(this, "Month");

        /*Setting listeners*/
        bi.lsid1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                bi.fldgrpls01.setVisibility(GONE);
                ClearClass.ClearAllFields(bi.fldgrpls01);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        bi.lsid11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == bi.lsid11b.getId()) {
                    bi.lsid12.clearCheck();
                    bi.lsid13.clearCheck();
                }
            }
        });

        bi.lsid12.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i != bi.lsid12a.getId()) {
                    bi.lsid13.clearCheck();
                }
            }
        });

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

    public void BtnIDValid() {
        if (!validatorClass.EmptyTextBox(this, bi.lsid1, getString(R.string.ls01a04))) {
            return;
        }

        /*if (CheckingID.getIDValidation( this, bi.lsid1, fTYPE)) {
            Toast.makeText(this, "Child ID validate..", Toast.LENGTH_SHORT).show();
            bi.fldgrpls01.setVisibility(VISIBLE);
        }*/

        bi.fldgrpls01.setVisibility(VISIBLE);
    }

    private boolean UpdateDB() {
        return true;
    }

    private void SaveDraft() {
        JSONObject Json = GeneratorClass.getContainerJSON(bi.fldgrpls01, true, fExt);
    }

    /*public void setGPS(Forms fc) {
        SharedPreferences GPSPref = getSharedPreferences("GPSCoordinates", Context.MODE_PRIVATE);
        try {
            String lat = GPSPref.getString("Latitude", "0");
            String lang = GPSPref.getString("Longitude", "0");
            String acc = GPSPref.getString("Accuracy", "0");
            String elevation = GPSPref.getString("Elevation", "0");

            if (lat == "0" && lang == "0") {
                Toast.makeText(this, "Could not obtained GPS points", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();
            }

            String date = DateFormat.format("dd-MM-yyyy HH:mm", Long.parseLong(GPSPref.getString("Time", "0"))).toString();

            fc.setGpsLat(lat);
            fc.setGpsLng(lang);
            fc.setGpsAcc(acc);
            fc.setGpsDT(date); // Timestamp is converted to date above
            fc.setGpsElev(elevation);

            Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e(TAG, "setGPS: " + e.getMessage());
        }

    }*/

    private boolean formValidation() {

        return validatorClass.EmptyCheckingContainer(this, bi.fldgrpls01);
    }

    public void BtnEnd() {

        SaveDraft();
        if (UpdateDB()) {
            startActivity(new Intent(getApplicationContext(), EndingActivity.class).putExtra("complete", false));
        } else {
            Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
        }
    }


}
