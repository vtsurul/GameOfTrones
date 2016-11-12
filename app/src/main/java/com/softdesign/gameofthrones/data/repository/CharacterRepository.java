package com.softdesign.gameofthrones.data.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.LinearLayout;

import com.softdesign.gameofthrones.data.database.CharacterCursorWrapper;
import com.softdesign.gameofthrones.data.model.Character;
import com.softdesign.gameofthrones.network.results.CharacterRes;
import com.softdesign.gameofthrones.utils.Utils;

import okhttp3.Request;

import static com.softdesign.gameofthrones.data.database.GotDbSchema.*;
import static com.softdesign.gameofthrones.data.database.GotDbSchema.CharactersTable.Fields.ALIASES;
import static com.softdesign.gameofthrones.data.database.GotDbSchema.CharactersTable.Fields.BORN;
import static com.softdesign.gameofthrones.data.database.GotDbSchema.CharactersTable.Fields.DIED;
import static com.softdesign.gameofthrones.data.database.GotDbSchema.CharactersTable.Fields.FATHERID;
import static com.softdesign.gameofthrones.data.database.GotDbSchema.CharactersTable.Fields.HOUSEID;
import static com.softdesign.gameofthrones.data.database.GotDbSchema.CharactersTable.Fields.MOTHERID;
import static com.softdesign.gameofthrones.data.database.GotDbSchema.CharactersTable.Fields.TITLES;
import static com.softdesign.gameofthrones.data.database.GotDbSchema.HousesTable.Fields.ID;
import static com.softdesign.gameofthrones.data.database.GotDbSchema.HousesTable.Fields.NAME;
import static com.softdesign.gameofthrones.data.database.GotDbSchema.HousesTable.Fields.URL;

public class CharacterRepository {

    private SQLiteDatabase mDatabase;


    public CharacterRepository(SQLiteDatabase database) {
        mDatabase = database;
    }


    public int getCount() {
        return queryCharacters(null, null).getCount();
    }


    public Character convertFromResponse(CharacterRes response) {

        Character character = new Character(Utils.getIdFromUrl(response.url));
        character.setUrl(response.url);
        character.setName(response.name);
        character.setBorn(response.born);
        character.setDied(response.died);
        character.setTitles(response.titles.toString().replace("[", "").replace("]", ""));
        character.setAliases(response.aliases.toString().replace("[", "").replace("]", ""));
        character.setFatherId(Utils.getIdFromUrl(response.father));
        character.setMotherId(Utils.getIdFromUrl(response.mother));
        if (!response.allegiances.isEmpty()){
            character.setHouseId(Utils.getIdFromUrl(response.allegiances.get(0)));
        }

        return character;
    }


    public boolean addCharacter(Character character) {

        boolean result = false;

        ContentValues values = getContentValues(character);
        try {
            mDatabase.insertOrThrow(CharactersTable.NAME, null, values);
            result = true;
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return result;
    }


    private ContentValues getContentValues(Character character) {
        ContentValues values = new ContentValues();

        values.put(ID, character.getId());
        values.put(URL, character.getUrl());
        values.put(NAME, character.getName());
        values.put(BORN, character.getBorn());
        values.put(DIED, character.getDied());
        values.put(TITLES, character.getTitles());
        values.put(ALIASES, character.getAliases());
        values.put(FATHERID, character.getFatherId());
        values.put(MOTHERID, character.getMotherId());
        values.put(HOUSEID, character.getHouseId());

        return values;
    }


    private CharacterCursorWrapper queryCharacters(String whereClause, String[] whereArgs) {
        Cursor c = mDatabase.query(
                CharactersTable.NAME,
                null, // all columns
                whereClause,
                whereArgs,
                null, // group by
                null, // having
                null // order by
        );

        return new CharacterCursorWrapper(c);
    }

}
