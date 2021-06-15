package com.nicholaswoodproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBTracker extends SQLiteOpenHelper {

    public static final String DBTracker = "Weight.db";

    public DBTracker(Context context) {
        super(context, "Weight.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase WDB) {
        WDB.execSQL("create Table Weights(date TEXT primary key, weight TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase WDB, int oldVersion, int newVersion) {
        WDB.execSQL("drop Table if exists Weights");
    }

    public Boolean insertUserData(String weight, String date){
        SQLiteDatabase WDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("weight", weight);
        contentValues.put("date", date);

        long result = WDB.insert("Weights", null, contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public Boolean updateUserData(String weight, String date) {
        SQLiteDatabase WDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("weight", weight);
        Cursor cursor = WDB.rawQuery("Select * from Weights where date = ?", new String[]{date});
        if (cursor.getCount() > 0) {

            long result = WDB.update("Weights", contentValues, "date=?", new String[]{date});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deleteUserData(String date){
            SQLiteDatabase WDB = this.getWritableDatabase();
            Cursor cursor = WDB.rawQuery("Select * from Weights where date = ?", new String[] {date});
            if(cursor.getCount()>0) {

                long result = WDB.delete("Weights", "date=?", new String[]{date});
                if (result == -1) {
                    return false;
                } else {
                    return true;
                }
            }
            else{
                return false;
            }

    }

    public Cursor getData(){
        SQLiteDatabase WDB = this.getWritableDatabase();
        Cursor cursor = WDB.rawQuery("Select * from Weights" , null);
        return cursor;

    }

}
