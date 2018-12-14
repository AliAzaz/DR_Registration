package com.example.hassannaqvi.dr_registration.contracts;


import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

public class HealthFacContract {

    private static final String TAG = "Villages_CONTRACT";
    String ID;
    String hf_name;
    String hf_type;
    String hf_dist_name;
    String hf_teh_name;
    String hf_prv_name;
    String hf_uc_name;
    String hf_uen_code;

    public HealthFacContract() {
        // Default Constructor
    }

    public HealthFacContract Sync(JSONObject jsonObject) throws JSONException {
        this.hf_name = jsonObject.getString(singleHF.COLUMN_HF_NAME);
        this.hf_type = jsonObject.getString(singleHF.COLUMN_HF_TYPE);
        this.hf_dist_name = jsonObject.getString(singleHF.COLUMN_HF_DISTRICT_NAME);
        this.hf_teh_name = jsonObject.getString(singleHF.COLUMN_HF_TEHSIL_NAME);
        this.hf_prv_name = jsonObject.getString(singleHF.COLUMN_HF_PROVINCE_NAME);
        this.hf_uc_name = jsonObject.getString(singleHF.COLUMN_HF_UC_NAME);
        this.hf_uen_code = jsonObject.getString(singleHF.COLUMN_HF_UEN_CODE);

        return this;
    }

    public HealthFacContract HydrateVillages(Cursor cursor) {
        this.hf_type = cursor.getString(cursor.getColumnIndex(singleHF.COLUMN_HF_TYPE));
        this.hf_dist_name = cursor.getString(cursor.getColumnIndex(singleHF.COLUMN_HF_DISTRICT_NAME));
        this.hf_name = cursor.getString(cursor.getColumnIndex(singleHF.COLUMN_HF_NAME));
        this.hf_teh_name = cursor.getString(cursor.getColumnIndex(singleHF.COLUMN_HF_TEHSIL_NAME));
        this.hf_prv_name = cursor.getString(cursor.getColumnIndex(singleHF.COLUMN_HF_PROVINCE_NAME));
        this.hf_uc_name = cursor.getString(cursor.getColumnIndex(singleHF.COLUMN_HF_UC_NAME));
        this.hf_uen_code = cursor.getString(cursor.getColumnIndex(singleHF.COLUMN_HF_UEN_CODE));
        return this;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getHf_name() {
        return hf_name;
    }

    public void setHf_name(String hf_name) {
        this.hf_name = hf_name;
    }

    public String getHf_type() {
        return hf_type;
    }

    public void setHf_type(String hf_type) {
        this.hf_type = hf_type;
    }

    public String getHf_dist_name() {
        return hf_dist_name;
    }

    public void setHf_dist_name(String hf_dist_name) {
        this.hf_dist_name = hf_dist_name;
    }

    public String getHf_teh_name() {
        return hf_teh_name;
    }

    public void setHf_teh_name(String hf_teh_name) {
        this.hf_teh_name = hf_teh_name;
    }

    public String getHf_prv_name() {
        return hf_prv_name;
    }

    public void setHf_prv_name(String hf_prv_name) {
        this.hf_prv_name = hf_prv_name;
    }

    public String getHf_uc_name() {
        return hf_uc_name;
    }

    public void setHf_uc_name(String hf_uc_name) {
        this.hf_uc_name = hf_uc_name;
    }

    public String getHf_uen_code() {
        return hf_uen_code;
    }

    public void setHf_uen_code(String hf_uen_code) {
        this.hf_uen_code = hf_uen_code;
    }

    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(singleHF.COLUMN_ID, this.ID == null ? JSONObject.NULL : this.ID);
        json.put(singleHF.COLUMN_HF_NAME, this.hf_name == null ? JSONObject.NULL : this.hf_name);
        json.put(singleHF.COLUMN_HF_TYPE, this.hf_type == null ? JSONObject.NULL : this.hf_type);
        json.put(singleHF.COLUMN_HF_DISTRICT_NAME, this.hf_dist_name == null ? JSONObject.NULL : this.hf_dist_name);
        json.put(singleHF.COLUMN_HF_TEHSIL_NAME, this.hf_teh_name == null ? JSONObject.NULL : this.hf_teh_name);
        json.put(singleHF.COLUMN_HF_PROVINCE_NAME, this.hf_prv_name == null ? JSONObject.NULL : this.hf_prv_name);
        json.put(singleHF.COLUMN_HF_UC_NAME, this.hf_uc_name == null ? JSONObject.NULL : this.hf_uc_name);
        json.put(singleHF.COLUMN_HF_UEN_CODE, this.hf_uen_code == null ? JSONObject.NULL : this.hf_uen_code);
        return json;
    }


    public static abstract class singleHF implements BaseColumns {

        public static final String TABLE_NAME = "health_fc";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_HF_NAME = "hf_name";
        public static final String COLUMN_HF_TYPE = "hf_type";
        public static final String COLUMN_HF_DISTRICT_NAME = "hf_district";
        public static final String COLUMN_HF_TEHSIL_NAME = "hf_tehsil";
        public static final String COLUMN_HF_PROVINCE_NAME = "p_name";
        public static final String COLUMN_HF_UC_NAME = "hf_uc";
        public static final String COLUMN_HF_UEN_CODE = "hf_uen_code";
    }
}