package com.softdesign.gameofthrones.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.softdesign.gameofthrones.data.database.GotDbSchema.*;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "got.db";

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + HousesTable.NAME +
                "(" +
                    HousesTable.Fields.ID + " INTEGER PRIMARY KEY, " +
                    HousesTable.Fields.URL + " TEXT, " +
                    HousesTable.Fields.WORDS + " TEXT, " +
                    HousesTable.Fields.NAME + " TEXT, " +
                    HousesTable.Fields.REGION + " TEXT" +
                ")");

        db.execSQL("create table " + CharactersTable.NAME +
            "(" +
                CharactersTable.Fields.ID + " INTEGER PRIMARY KEY, " +
                CharactersTable.Fields.HOUSEID + " INTEGER, " +
                CharactersTable.Fields.URL + " TEXT, " +
                CharactersTable.Fields.NAME + " TEXT, " +
                CharactersTable.Fields.BORN + " TEXT, " +
                CharactersTable.Fields.DIED + " TEXT, " +
                CharactersTable.Fields.TITLES + " TEXT, " +
                CharactersTable.Fields.ALIASES + " TEXT, " +
                CharactersTable.Fields.FATHERID + " INTEGER, " +
                CharactersTable.Fields.MOTHERID + " INTEGER " +
        ")");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
