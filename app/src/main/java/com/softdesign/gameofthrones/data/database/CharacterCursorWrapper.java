package com.softdesign.gameofthrones.data.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.softdesign.gameofthrones.data.model.Character;
import com.softdesign.gameofthrones.data.model.House;

import static com.softdesign.gameofthrones.data.database.GotDbSchema.*;
import static com.softdesign.gameofthrones.data.database.GotDbSchema.HousesTable;

public class CharacterCursorWrapper extends CursorWrapper{

    public CharacterCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Character getCharacter() {

        Character character = new Character(getColumnIndex(CharactersTable.Fields.ID));

        character.setUrl(getString(getColumnIndex(CharactersTable.Fields.URL)));
        character.setName(getString(getColumnIndex(CharactersTable.Fields.NAME)));
        character.setBorn(getString(getColumnIndex(CharactersTable.Fields.BORN)));
        character.setDied(getString(getColumnIndex(CharactersTable.Fields.DIED)));
        character.setTitles(getString(getColumnIndex(CharactersTable.Fields.TITLES)));
        character.setAliases(getString(getColumnIndex(CharactersTable.Fields.ALIASES)));
        character.setFatherId(getColumnIndex(CharactersTable.Fields.FATHERID));
        character.setMotherId(getColumnIndex(CharactersTable.Fields.MOTHERID));

        return character;
    }
}
