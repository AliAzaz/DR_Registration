package com.example.hassannaqvi.dr_registration.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class HFFormContract {

    private String projectName = "DR-Registration";
    private String surveyType = "BL";
    private String _ID = "";
    private String _UID = "";
    private String _UUID = "";
    private String formDate = ""; // Date
    private String user = ""; // Interviewer 01
    private String istatus = ""; // Interview Status
    private String istatus88x = ""; // Interview Status
    private String sA = "";
    private String sno = "";
    private String endingdatetime = "";
    private String gpsLat = "";
    private String gpsLng = "";
    private String gpsDT = "";
    private String gpsAcc = "";
    private String gpsElev = "";
    private String deviceID = "";
    private String devicetagID = "";
    private String synced = "";
    private String synced_date = "";
    private String appversion;

    public HFFormContract() {
    }

    public HFFormContract Sync(JSONObject jsonObject) throws JSONException {
        this.projectName = jsonObject.getString(HFTable.COLUMN_PROJECTNAME);
        this.surveyType = jsonObject.getString(HFTable.COLUMN_SURVEYTYPE);
        this._ID = jsonObject.getString(HFTable.COLUMN_ID);
        this._UID = jsonObject.getString(HFTable.COLUMN_UID);
        this._UUID = jsonObject.getString(HFTable.COLUMN_UUID);
        this.formDate = jsonObject.getString(HFTable.COLUMN_FORMDATE);
        this.user = jsonObject.getString(HFTable.COLUMN_USER);
        this.istatus = jsonObject.getString(HFTable.COLUMN_ISTATUS);
        this.istatus88x = jsonObject.getString(HFTable.COLUMN_ISTATUS88X);
        this.sA = jsonObject.getString(HFTable.COLUMN_SA);

        this.sno = jsonObject.getString(HFTable.COLUMN_SNO);
        this.endingdatetime = jsonObject.getString(HFTable.COLUMN_ENDINGDATETIME);
        this.gpsLat = jsonObject.getString(HFTable.COLUMN_GPSLAT);
        this.gpsLng = jsonObject.getString(HFTable.COLUMN_GPSLNG);
        this.gpsDT = jsonObject.getString(HFTable.COLUMN_GPSDT);
        this.gpsAcc = jsonObject.getString(HFTable.COLUMN_GPSACC);
        this.gpsElev = jsonObject.getString(HFTable.COLUMN_GPSELEV);
        this.deviceID = jsonObject.getString(HFTable.COLUMN_DEVICEID);
        this.devicetagID = jsonObject.getString(HFTable.COLUMN_DEVICETAGID);
        this.synced = jsonObject.getString(HFTable.COLUMN_SYNCED);
        this.synced_date = jsonObject.getString(HFTable.COLUMN_SYNCED_DATE);
        this.appversion = jsonObject.getString(HFTable.COLUMN_APPVERSION);


        return this;

    }

    public HFFormContract Hydrate(Cursor cursor) {
        this.projectName = cursor.getString(cursor.getColumnIndex(HFTable.COLUMN_PROJECTNAME));
        this.surveyType = cursor.getString(cursor.getColumnIndex(HFTable.COLUMN_SURVEYTYPE));
        this._ID = cursor.getString(cursor.getColumnIndex(HFTable.COLUMN_ID));
        this._UID = cursor.getString(cursor.getColumnIndex(HFTable.COLUMN_UID));
        this._UUID = cursor.getString(cursor.getColumnIndex(HFTable.COLUMN_UUID));
        this.formDate = cursor.getString(cursor.getColumnIndex(HFTable.COLUMN_FORMDATE));
        this.user = cursor.getString(cursor.getColumnIndex(HFTable.COLUMN_USER));

        this.istatus = cursor.getString(cursor.getColumnIndex(HFTable.COLUMN_ISTATUS));
        this.istatus88x = cursor.getString(cursor.getColumnIndex(HFTable.COLUMN_ISTATUS88X));
        this.sA = cursor.getString(cursor.getColumnIndex(HFTable.COLUMN_SA));

        this.sno = cursor.getString(cursor.getColumnIndex(HFTable.COLUMN_SNO));
        this.endingdatetime = cursor.getString(cursor.getColumnIndex(HFTable.COLUMN_ENDINGDATETIME));
        this.gpsLat = cursor.getString(cursor.getColumnIndex(HFTable.COLUMN_GPSLAT));
        this.gpsLng = cursor.getString(cursor.getColumnIndex(HFTable.COLUMN_GPSLNG));
        this.gpsDT = cursor.getString(cursor.getColumnIndex(HFTable.COLUMN_GPSDT));
        this.gpsAcc = cursor.getString(cursor.getColumnIndex(HFTable.COLUMN_GPSACC));
        this.gpsElev = cursor.getString(cursor.getColumnIndex(HFTable.COLUMN_GPSELEV));
        this.deviceID = cursor.getString(cursor.getColumnIndex(HFTable.COLUMN_DEVICEID));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(HFTable.COLUMN_DEVICETAGID));
        this.synced = cursor.getString(cursor.getColumnIndex(HFTable.COLUMN_SYNCED));
        this.synced_date = cursor.getString(cursor.getColumnIndex(HFTable.COLUMN_SYNCED_DATE));
        this.appversion = cursor.getString(cursor.getColumnIndex(HFTable.COLUMN_APPVERSION));


        return this;

    }


    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();

        json.put(HFTable.COLUMN_PROJECTNAME, this.projectName == null ? JSONObject.NULL : this.projectName);
        json.put(HFTable.COLUMN_SURVEYTYPE, this.surveyType == null ? JSONObject.NULL : this.surveyType);
        json.put(HFTable.COLUMN_ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(HFTable.COLUMN_UID, this._UID == null ? JSONObject.NULL : this._UID);
        json.put(HFTable.COLUMN_UUID, this._UUID == null ? JSONObject.NULL : this._UUID);
        json.put(HFTable.COLUMN_FORMDATE, this.formDate == null ? JSONObject.NULL : this.formDate);
        json.put(HFTable.COLUMN_USER, this.user == null ? JSONObject.NULL : this.user);
        json.put(HFTable.COLUMN_ISTATUS, this.istatus == null ? JSONObject.NULL : this.istatus);
        json.put(HFTable.COLUMN_ISTATUS88X, this.istatus88x == null ? JSONObject.NULL : this.istatus88x);
        json.put(HFTable.COLUMN_SNO, this.sno == null ? JSONObject.NULL : this.sno);
        json.put(HFTable.COLUMN_ENDINGDATETIME, this.endingdatetime == null ? JSONObject.NULL : this.endingdatetime);

        if (!this.sA.equals("")) {
            json.put(HFTable.COLUMN_SA, this.sA.equals("") ? JSONObject.NULL : new JSONObject(this.sA));
        }

        json.put(HFTable.COLUMN_GPSLAT, this.gpsLat == null ? JSONObject.NULL : this.gpsLat);
        json.put(HFTable.COLUMN_GPSLNG, this.gpsLng == null ? JSONObject.NULL : this.gpsLng);
        json.put(HFTable.COLUMN_GPSDT, this.gpsDT == null ? JSONObject.NULL : this.gpsDT);
        json.put(HFTable.COLUMN_GPSACC, this.gpsAcc == null ? JSONObject.NULL : this.gpsAcc);
        json.put(HFTable.COLUMN_GPSELEV, this.gpsElev == null ? JSONObject.NULL : this.gpsElev);
        json.put(HFTable.COLUMN_DEVICEID, this.deviceID == null ? JSONObject.NULL : this.deviceID);
        json.put(HFTable.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);
        json.put(HFTable.COLUMN_SYNCED, this.synced == null ? JSONObject.NULL : this.synced);
        json.put(HFTable.COLUMN_SYNCED_DATE, this.synced_date == null ? JSONObject.NULL : this.synced_date);
        json.put(HFTable.COLUMN_APPVERSION, this.appversion == null ? JSONObject.NULL : this.appversion);

        return json;
    }

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String get_UID() {
        return _UID;
    }

    public void set_UID(String _UID) {
        this._UID = _UID;
    }

    public String get_UUID() {
        return _UUID;
    }

    public void set_UUID(String _UUID) {
        this._UUID = _UUID;
    }

    public String getFormDate() {
        return formDate;
    }

    public void setFormDate(String formDate) {
        this.formDate = formDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getIstatus() {
        return istatus;
    }

    public void setIstatus(String istatus) {
        this.istatus = istatus;
    }

    public String getIstatus88x() {
        return istatus88x;
    }

    public void setIstatus88x(String istatus88x) {
        this.istatus88x = istatus88x;
    }

    public String getsA() {
        return sA;
    }

    public void setsA(String sA) {
        this.sA = sA;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getEndingdatetime() {
        return endingdatetime;
    }

    public void setEndingdatetime(String endingdatetime) {
        this.endingdatetime = endingdatetime;
    }

    public String getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(String gpsLat) {
        this.gpsLat = gpsLat;
    }

    public String getGpsLng() {
        return gpsLng;
    }

    public void setGpsLng(String gpsLng) {
        this.gpsLng = gpsLng;
    }

    public String getGpsDT() {
        return gpsDT;
    }

    public void setGpsDT(String gpsDT) {
        this.gpsDT = gpsDT;
    }

    public String getGpsAcc() {
        return gpsAcc;
    }

    public void setGpsAcc(String gpsAcc) {
        this.gpsAcc = gpsAcc;
    }

    public String getGpsElev() {
        return gpsElev;
    }

    public void setGpsElev(String gpsElev) {
        this.gpsElev = gpsElev;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getDevicetagID() {
        return devicetagID;
    }

    public void setDevicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
    }

    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }

    public String getSynced_date() {
        return synced_date;
    }

    public void setSynced_date(String synced_date) {
        this.synced_date = synced_date;
    }

    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }

    public static abstract class HFTable implements BaseColumns {

        public static final String TABLE_NAME = "hf_forms";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";
        public static final String COLUMN_PROJECTNAME = "projectname";
        public static final String COLUMN_SURVEYTYPE = "surveytype";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_UID = "_uid";
        public static final String COLUMN_UUID = "_uuid";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_USER = "user";
        public static final String COLUMN_ISTATUS = "istatus";
        public static final String COLUMN_ISTATUS88X = "istatus88x";
        public static final String COLUMN_SA = "sa";
        public static final String COLUMN_SNO = "sno";
        public static final String COLUMN_ENDINGDATETIME = "endingdatetime";
        public static final String COLUMN_GPSLAT = "gpslat";
        public static final String COLUMN_GPSLNG = "gpslng";
        public static final String COLUMN_GPSDT = "gpsdt";
        public static final String COLUMN_GPSACC = "gpsacc";
        public static final String COLUMN_GPSELEV = "gpselev";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_DEVICETAGID = "devicetagid";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";
        public static final String COLUMN_APPVERSION = "appversion";

    }
}