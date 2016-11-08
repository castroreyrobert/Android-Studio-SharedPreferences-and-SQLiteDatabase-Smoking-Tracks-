package com.example.castroreyrobert.sharedpreferencesdemo;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBAdapter {

    private static final String DB_NAME = "smoke.db";
    private static final int DB_VERSION = 1;

    public static final String TB_NAME = "smoke_table";
    public static final String COL_ID = "ID";
    public static final String COL_DATE = "DATE";
    public static final String COL_STICKS = "STICKS";


    private String [] allColumn = {COL_ID, COL_DATE, COL_STICKS};

    public static final String CREATE_QUERY = "CREATE TABLE " + TB_NAME + "( "
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_DATE + " TEXT NOT NULL, "
            + COL_STICKS + " TEXT NOT NULL);";

    private SQLiteDatabase db;
    private Context context;
    private DBHandler dbHandler;

    public DBAdapter(Context context) {
        this.context = context;
    }

    public DBAdapter open() throws android.database.SQLException{
        dbHandler = new DBHandler(context);
        db = dbHandler.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHandler.close();
    }

    public SmokerModel addSmoker(String date, String sticks){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_DATE, date);
        contentValues.put(COL_STICKS, sticks);

        long insertID =  db.insert(TB_NAME, null, contentValues);


       Cursor cursor = db.query(TB_NAME, allColumn, COL_ID + " = " + insertID, null, null, null, null, null);

        cursor.moveToFirst();
        SmokerModel smokerModel = cursorToSmoke(cursor);
        cursor.close();
        return smokerModel;

    }

    public boolean updateSmoke(String date, String sticks){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_STICKS, sticks);
        db.update(TB_NAME, contentValues, "DATE = ?", new String[]{date});
        return true;
    }



    public long deleteSmoker(long idtoDelete){
        return db.delete(TB_NAME, COL_ID + " = " + idtoDelete, null);
    }

    public ArrayList<SmokerModel> getAllSmoke(){
        ArrayList<SmokerModel> arrayList = new ArrayList<SmokerModel>();

        Cursor cursor = db.query(TB_NAME, allColumn, null, null, null, null, null);

        for (cursor.moveToLast(); !cursor.isBeforeFirst(); cursor.moveToPrevious()){
            SmokerModel smokerModel = cursorToSmoke(cursor);
            arrayList.add(smokerModel);
        }

        cursor.close();
        return arrayList;
    }

    private SmokerModel cursorToSmoke(Cursor cursor){
        SmokerModel newSmoke = new SmokerModel(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
        return newSmoke;
    }
    private static class DBHandler extends SQLiteOpenHelper{
        public DBHandler(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXIST " + TB_NAME);
            onCreate(db);
        }
    }
}
