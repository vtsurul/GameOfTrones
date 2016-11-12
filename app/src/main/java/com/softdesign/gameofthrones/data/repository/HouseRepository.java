package com.softdesign.gameofthrones.data.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.softdesign.gameofthrones.data.database.GotDbSchema.HousesTable;
import com.softdesign.gameofthrones.data.database.HousesCursorWrapper;
import com.softdesign.gameofthrones.data.model.House;
import com.softdesign.gameofthrones.network.results.HouseRes;
import com.softdesign.gameofthrones.utils.Utils;

import static com.softdesign.gameofthrones.data.database.GotDbSchema.HousesTable.Fields.ID;
import static com.softdesign.gameofthrones.data.database.GotDbSchema.HousesTable.Fields.NAME;
import static com.softdesign.gameofthrones.data.database.GotDbSchema.HousesTable.Fields.REGION;
import static com.softdesign.gameofthrones.data.database.GotDbSchema.HousesTable.Fields.URL;
import static com.softdesign.gameofthrones.data.database.GotDbSchema.HousesTable.Fields.WORDS;

public class HouseRepository {

    private SQLiteDatabase mDatabase;


    public HouseRepository(SQLiteDatabase database) {
        mDatabase = database;
    }


    public int getCount() {
        return queryHouses(null, null).getCount();
    }


    public House convertFromResponse(HouseRes response) {
        House house = new House(Utils.getIdFromUrl(response.url));
        house.setUrl(response.url);
        house.setWords(response.words);
        house.setName(response.name);
        house.setRegion(response.region);

        return house;
    }


    public boolean addHouse(House house) {

        boolean result = false;

        ContentValues values = getContentValues(house);
        try {
            mDatabase.insertOrThrow(HousesTable.NAME, null, values);
            result = true;
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return result;
    }


    private ContentValues getContentValues(House house) {
        ContentValues values = new ContentValues();

        values.put(ID, house.getId());
        values.put(URL, house.getUrl());
        values.put(WORDS, house.getWords());
        values.put(NAME, house.getName());
        values.put(REGION, house.getRegion());

        return values;
    }


    private HousesCursorWrapper queryHouses(String whereClause, String[] whereArgs) {
        Cursor c = mDatabase.query(
                HousesTable.NAME,
                null, // all columns
                whereClause,
                whereArgs,
                null, // group by
                null, // having
                null // order by
        );

        return new HousesCursorWrapper(c);
    }
}
