package com.softdesign.gameofthrones.data.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.softdesign.gameofthrones.data.model.House;

import static com.softdesign.gameofthrones.data.database.GotDbSchema.*;

public class HousesCursorWrapper extends CursorWrapper{

    public HousesCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public House getHouse() {

        House house = new House(getColumnIndex(HousesTable.Fields.ID));

        house.setUrl(getString(getColumnIndex(HousesTable.Fields.URL)));
        house.setWords(getString(getColumnIndex(HousesTable.Fields.WORDS)));
        house.setName(getString(getColumnIndex(HousesTable.Fields.NAME)));
        house.setRegion(getString(getColumnIndex(HousesTable.Fields.REGION)));

        return house;
    }
}
