package com.example.hassannaqvi.dr_registration.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.hassannaqvi.dr_registration.contracts.FormsContract;
import com.example.hassannaqvi.dr_registration.contracts.FormsContract.FormsTable;
import com.example.hassannaqvi.dr_registration.contracts.HealthFacContract;
import com.example.hassannaqvi.dr_registration.contracts.HealthFacContract.singleHF;
import com.example.hassannaqvi.dr_registration.contracts.LHWsContract;
import com.example.hassannaqvi.dr_registration.contracts.LHWsContract.singleLHWs;
import com.example.hassannaqvi.dr_registration.contracts.TehsilContract;
import com.example.hassannaqvi.dr_registration.contracts.TehsilContract.singleTehsil;
import com.example.hassannaqvi.dr_registration.contracts.UCsContract;
import com.example.hassannaqvi.dr_registration.contracts.UCsContract.singleUCs;
import com.example.hassannaqvi.dr_registration.contracts.UsersContract;
import com.example.hassannaqvi.dr_registration.contracts.UsersContract.UsersTable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String SQL_CREATE_USERS = "CREATE TABLE " + UsersTable.TABLE_NAME + "("
            + UsersTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + UsersTable.ROW_USERNAME + " TEXT,"
            + UsersTable.ROW_PASSWORD + " TEXT );";
    public static final String DATABASE_NAME = "dr_reg.db";
    public static final String DB_NAME = DATABASE_NAME.replace(".", "_copy.");
    public static final String PROJECT_NAME = "DR-REGISTRATION-FORM";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_FORMS = "CREATE TABLE "
            + FormsTable.TABLE_NAME + "("
            + FormsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FormsTable.COLUMN_PROJECTNAME + " TEXT," +
            FormsTable.COLUMN_SURVEYTYPE + " TEXT," +
            FormsTable.COLUMN_UID + " TEXT," +
            FormsTable.COLUMN_FORMDATE + " TEXT," +
            FormsTable.COLUMN_USER + " TEXT," +
            FormsTable.COLUMN_ISTATUS + " TEXT," +
            FormsTable.COLUMN_ISTATUS88X + " TEXT," +
            FormsTable.COLUMN_SA + " TEXT," +
            FormsTable.COLUMN_SNO + " TEXT," +
            FormsTable.COLUMN_ENDINGDATETIME + " TEXT," +
            FormsTable.COLUMN_GPSLAT + " TEXT," +
            FormsTable.COLUMN_GPSLNG + " TEXT," +
            FormsTable.COLUMN_GPSDT + " TEXT," +
            FormsTable.COLUMN_GPSACC + " TEXT," +
            FormsTable.COLUMN_GPSELEV + " TEXT," +
            FormsTable.COLUMN_DEVICEID + " TEXT," +
            FormsTable.COLUMN_DEVICETAGID + " TEXT," +
            FormsTable.COLUMN_SYNCED + " TEXT," +
            FormsTable.COLUMN_SYNCED_DATE + " TEXT," +
            FormsTable.COLUMN_APPVERSION + " TEXT"
            + " );";

    private static final String SQL_DELETE_USERS =
            "DROP TABLE IF EXISTS " + UsersTable.TABLE_NAME;
    private static final String SQL_DELETE_FORMS =
            "DROP TABLE IF EXISTS " + FormsTable.TABLE_NAME;


    private static final String SQL_DELETE_HEALTH_FACILITIES = "DROP TABLE IF EXISTS " + singleHF.TABLE_NAME;
    private static final String SQL_DELETE_TEHSILS = "DROP TABLE IF EXISTS " + singleTehsil.TABLE_NAME;
    private static final String SQL_DELETE_UCS = "DROP TABLE IF EXISTS " + singleUCs.TABLE_NAME;
    private static final String SQL_DELETE_LHWs = "DROP TABLE IF EXISTS " + singleLHWs.TABLE_NAME;
    final String SQL_CREATE_HEALTH_FACTILITIES = "CREATE TABLE " + singleHF.TABLE_NAME + "("
            + singleHF.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + singleHF.COLUMN_HF_NAME + " TEXT,"
            + singleHF.COLUMN_HF_TYPE + " TEXT,"
            + singleHF.COLUMN_HF_DISTRICT_NAME + " TEXT,"
            + singleHF.COLUMN_HF_PROVINCE_NAME + " TEXT,"
            + singleHF.COLUMN_HF_UC_NAME + " TEXT,"
            + singleHF.COLUMN_HF_UEN_CODE + " TEXT,"
            + singleHF.COLUMN_HF_TEHSIL_NAME + " TEXT );";
    final String SQL_CREATE_LHWS = "CREATE TABLE " + singleLHWs.TABLE_NAME + "("
            + singleLHWs.COLUMN_LHW_NAME + " TEXT,"
            + singleLHWs.COLUMN_HF_CODE + " TEXT,"
            + singleLHWs.COLUMN_LHW_CODE + " TEXT );";
    final String SQL_CREATE_TEHSILS = "CREATE TABLE " + singleTehsil.TABLE_NAME + "("
            + singleTehsil.COLUMN_TEHSIL_CODE + " TEXT,"
            + singleTehsil.COLUMN_TEHSIL_NAME + " TEXT );";
    final String SQL_CREATE_UCS = "CREATE TABLE " + singleUCs.TABLE_NAME + "("
            + singleUCs.COLUMN_UCCODE + " TEXT,"
            + singleUCs.COLUMN_TEHSIL_CODE + " TEXT,"
            + singleUCs.COLUMN_UCS + " TEXT );";

    private final String TAG = "DatabaseHelper";


    public String spDateT = new SimpleDateFormat("dd-MM-yy").format(new Date().getTime());


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_USERS);
        db.execSQL(SQL_CREATE_FORMS);

        db.execSQL(SQL_CREATE_HEALTH_FACTILITIES);
        /*db.execSQL(SQL_CREATE_TEHSILS);
        db.execSQL(SQL_CREATE_UCS);
        db.execSQL(SQL_CREATE_LHWS);*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_USERS);
        db.execSQL(SQL_DELETE_FORMS);
        db.execSQL(SQL_DELETE_HEALTH_FACILITIES);
    }


    public Collection<HealthFacContract> getVillages(String uccode) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                singleHF.COLUMN_ID,
                singleHF.COLUMN_HF_NAME,
                singleHF.COLUMN_HF_TYPE,
                singleHF.COLUMN_HF_DISTRICT_NAME,
                singleHF.COLUMN_HF_TEHSIL_NAME,
                singleHF.COLUMN_HF_PROVINCE_NAME,
                singleHF.COLUMN_HF_UC_NAME,
                singleHF.COLUMN_HF_UEN_CODE,
        };

        String whereClause = singleHF.COLUMN_HF_TYPE + " =?";
        String[] whereArgs = {uccode};
        String groupBy = null;
        String having = null;

        String orderBy =
                singleHF.COLUMN_HF_NAME + " ASC";

        Collection<HealthFacContract> allDC = new ArrayList<>();
        try {
            c = db.query(
                    singleHF.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                HealthFacContract dc = new HealthFacContract();
                allDC.add(dc.HydrateVillages(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allDC;
    }

    public Collection<LHWsContract> getLHWs(String uccode) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                singleLHWs.COLUMN_LHW_NAME,
                singleLHWs.COLUMN_HF_CODE,
                singleLHWs.COLUMN_LHW_CODE,
        };

        String whereClause = singleLHWs.COLUMN_HF_CODE + " =?";
        String[] whereArgs = {uccode};
        String groupBy = null;
        String having = null;

        String orderBy =
                singleLHWs.COLUMN_LHW_NAME + " ASC";

        Collection<LHWsContract> allDC = new ArrayList<>();
        try {
            c = db.query(
                    singleLHWs.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                LHWsContract dc = new LHWsContract();
                allDC.add(dc.HydrateLHWs(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allDC;
    }

    public Collection<TehsilContract> getAllTehsils() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                singleTehsil.COLUMN_TEHSIL_CODE,
                singleTehsil.COLUMN_TEHSIL_NAME
        };

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                singleTehsil.COLUMN_TEHSIL_NAME + " ASC";

        Collection<TehsilContract> allDC = new ArrayList<>();
        try {
            c = db.query(
                    singleTehsil.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                TehsilContract dc = new TehsilContract();
                allDC.add(dc.HydrateTehsils(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allDC;
    }

    public Collection<UCsContract> getAllUCs(String talukaCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                singleUCs.COLUMN_UCCODE,
                singleUCs.COLUMN_UCS,
                singleUCs.COLUMN_TEHSIL_CODE
        };

        String whereClause = singleUCs.COLUMN_TEHSIL_CODE + "=?";
        String[] whereArgs = new String[]{talukaCode};
        String groupBy = null;
        String having = null;

        String orderBy =
                singleUCs.COLUMN_UCS + " ASC";

        Collection<UCsContract> allDC = new ArrayList<>();
        try {
            c = db.query(
                    singleUCs.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                UCsContract dc = new UCsContract();
                allDC.add(dc.HydrateUCs(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allDC;
    }

    public ArrayList<UsersContract> getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<UsersContract> userList = null;
        try {
            userList = new ArrayList<UsersContract>();
            String QUERY = "SELECT * FROM " + UsersTable.TABLE_NAME;
            Cursor cursor = db.rawQuery(QUERY, null);
            int num = cursor.getCount();
            if (!cursor.isLast()) {
                while (cursor.moveToNext()) {
                    UsersContract user = new UsersContract();
                    user.setId(cursor.getInt(0));
                    user.setUserName(cursor.getString(1));
                    user.setPassword(cursor.getString(2));
                    userList.add(user);
                }
            }
            db.close();
        } catch (Exception e) {
        }
        return userList;
    }

    public void syncUsers(JSONArray userlist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UsersTable.TABLE_NAME, null, null);

        try {
            JSONArray jsonArray = userlist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectUser = jsonArray.getJSONObject(i);
                String userName = jsonObjectUser.getString("username");
                String password = jsonObjectUser.getString("password");


                ContentValues values = new ContentValues();

                values.put(UsersTable.ROW_USERNAME, userName);
                values.put(UsersTable.ROW_PASSWORD, password);
                db.insert(UsersTable.TABLE_NAME, null, values);
            }
            db.close();

        } catch (Exception e) {
        }
    }

    public void syncLHWS(JSONArray lhwsList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(singleLHWs.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = lhwsList;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectCC = jsonArray.getJSONObject(i);

                LHWsContract Vc = new LHWsContract();
                Vc.Sync(jsonObjectCC);

                ContentValues values = new ContentValues();

                values.put(singleLHWs.COLUMN_LHW_NAME, Vc.getLhwname());
                values.put(singleLHWs.COLUMN_HF_CODE, Vc.getHf_code());
                values.put(singleLHWs.COLUMN_LHW_CODE, Vc.getLhwcode());

                db.insert(singleLHWs.TABLE_NAME, null, values);
            }
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    public void syncHF(JSONArray HFlist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(singleHF.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = HFlist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectCC = jsonArray.getJSONObject(i);

                HealthFacContract Vc = new HealthFacContract();
                Vc.Sync(jsonObjectCC);

                ContentValues values = new ContentValues();

                values.put(singleHF.COLUMN_HF_NAME, Vc.getHf_name());
                values.put(singleHF.COLUMN_HF_TYPE, Vc.getHf_type());
                values.put(singleHF.COLUMN_HF_DISTRICT_NAME, Vc.getHf_dist_name());
                values.put(singleHF.COLUMN_HF_TEHSIL_NAME, Vc.getHf_teh_name());
                values.put(singleHF.COLUMN_HF_PROVINCE_NAME, Vc.getHf_prv_name());
                values.put(singleHF.COLUMN_HF_UC_NAME, Vc.getHf_uc_name());
                values.put(singleHF.COLUMN_HF_UEN_CODE, Vc.getHf_uen_code());

                db.insert(singleHF.TABLE_NAME, null, values);
            }
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    public void syncTehsil(JSONArray Tehsillist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(singleTehsil.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = Tehsillist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectCC = jsonArray.getJSONObject(i);

                TehsilContract Vc = new TehsilContract();
                Vc.Sync(jsonObjectCC);

                ContentValues values = new ContentValues();

                values.put(singleTehsil.COLUMN_TEHSIL_CODE, Vc.getTehsilcode());
                values.put(singleTehsil.COLUMN_TEHSIL_NAME, Vc.getTehsil_name());

                db.insert(singleTehsil.TABLE_NAME, null, values);
            }
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    public void syncUCs(JSONArray UCslist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(singleUCs.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = UCslist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectCC = jsonArray.getJSONObject(i);

                UCsContract Vc = new UCsContract();
                Vc.Sync(jsonObjectCC);

                ContentValues values = new ContentValues();

                values.put(singleUCs.COLUMN_UCCODE, Vc.getUccode());
                values.put(singleUCs.COLUMN_UCS, Vc.getUcs());
                values.put(singleUCs.COLUMN_TEHSIL_CODE, Vc.getTehsil_code());

                db.insert(singleUCs.TABLE_NAME, null, values);
            }
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    public boolean Login(String username, String password) throws SQLException {

        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        String[] columns = {
                UsersTable._ID
        };

// Which row to update, based on the ID
        String selection = UsersTable.ROW_USERNAME + " = ?" + " AND " + UsersTable.ROW_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(UsersTable.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        return cursorCount > 0;
    }

    public List<FormsContract> getFormsByDSS(String dssID) {
        List<FormsContract> formList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + FormsTable.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                FormsContract fc = new FormsContract();
                formList.add(fc.Hydrate(c));
            } while (c.moveToNext());
        }

        // return contact list
        return formList;
    }

    public Long addForm(FormsContract fc) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_PROJECTNAME, fc.getProjectName());
        values.put(FormsTable.COLUMN_SURVEYTYPE, fc.getSurveyType());
        values.put(FormsTable.COLUMN_UID, fc.getUID());
        values.put(FormsTable.COLUMN_FORMDATE, fc.getFormDate());
        values.put(FormsTable.COLUMN_USER, fc.getUser());
        values.put(FormsTable.COLUMN_ISTATUS, fc.getIstatus());
        values.put(FormsTable.COLUMN_ISTATUS88X, fc.getIstatus88x());
        values.put(FormsTable.COLUMN_SA, fc.getsA());
        values.put(FormsTable.COLUMN_ENDINGDATETIME, fc.getEndingdatetime());
        values.put(FormsTable.COLUMN_GPSLAT, fc.getGpsLat());
        values.put(FormsTable.COLUMN_GPSLNG, fc.getGpsLng());
        values.put(FormsTable.COLUMN_GPSDT, fc.getGpsDT());
        values.put(FormsTable.COLUMN_GPSACC, fc.getGpsAcc());
        values.put(FormsTable.COLUMN_GPSELEV, fc.getGpsElev());
        values.put(FormsTable.COLUMN_DEVICEID, fc.getDeviceID());
        values.put(FormsTable.COLUMN_DEVICETAGID, fc.getDevicetagID());
        values.put(FormsTable.COLUMN_SYNCED, fc.getSynced());
        values.put(FormsTable.COLUMN_SYNCED_DATE, fc.getSynced_date());
        values.put(FormsTable.COLUMN_APPVERSION, fc.getAppversion());


        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FormsTable.TABLE_NAME,
                FormsTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }

    public void updateSyncedForms(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SYNCED, true);
        values.put(FormsTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = FormsTable._ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                FormsTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public int updateFormID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_UID, MainApp.fc.getUID());

// Which row to update, based on the ID
        String selection = FormsTable.COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public Collection<FormsContract> getUnsyncedForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable.COLUMN_PROJECTNAME,
                FormsTable.COLUMN_SURVEYTYPE,
                FormsTable.COLUMN_ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_ISTATUS88X,
                FormsTable.COLUMN_SA,
                FormsTable.COLUMN_SNO,
                FormsTable.COLUMN_ENDINGDATETIME,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDT,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_GPSELEV,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_SYNCED_DATE,
                FormsTable.COLUMN_APPVERSION

        };
        String whereClause = FormsTable.COLUMN_SYNCED + " is null OR " + FormsTable.COLUMN_SYNCED + " = '' ";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " ASC";

        Collection<FormsContract> allFC = new ArrayList<FormsContract>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                allFC.add(fc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<FormsContract> getTodayForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                //ChildTable.COLUMN_DSSID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_SYNCED,

        };
        String whereClause = FormsTable.COLUMN_FORMDATE + " Like ? ";
        String[] whereArgs = new String[]{"%" + spDateT.substring(0, 8).trim() + "%"};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " ASC";

        Collection<FormsContract> allFC = new ArrayList<>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                fc.set_ID(c.getString(c.getColumnIndex(FormsTable._ID)));
                fc.setFormDate(c.getString(c.getColumnIndex(FormsTable.COLUMN_FORMDATE)));
                fc.setIstatus(c.getString(c.getColumnIndex(FormsTable.COLUMN_ISTATUS)));
                fc.setSynced(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYNCED)));
                allFC.add(fc);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    // ANDROID DATABASE MANAGER
    public ArrayList<Cursor> getData(String Query) {
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[]{"message"};
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try {
            String maxQuery = Query;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[]{"Success"});

            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0, c);
                c.moveToFirst();

                return alc;
            }
            return alc;
        } catch (SQLException sqlEx) {
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + sqlEx.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        } catch (Exception ex) {

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + ex.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }
    }

    public int updatesA() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SA, MainApp.fc.getsA());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateEnding() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_ISTATUS, MainApp.fc.getIstatus());
        values.put(FormsTable.COLUMN_ISTATUS88X, MainApp.fc.getIstatus88x());
        values.put(FormsTable.COLUMN_ENDINGDATETIME, MainApp.fc.getEndingdatetime());


// Which row to update, based on the ID
        String selection = FormsTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }
}
