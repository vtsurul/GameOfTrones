package com.softdesign.gameofthrones.data.managers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.softdesign.gameofthrones.R;
import com.softdesign.gameofthrones.data.database.DataBaseHelper;
import com.softdesign.gameofthrones.data.database.GotDbSchema;
import com.softdesign.gameofthrones.data.model.Character;
import com.softdesign.gameofthrones.data.model.House;
import com.softdesign.gameofthrones.data.repository.CharacterRepository;
import com.softdesign.gameofthrones.data.repository.HouseRepository;
import com.softdesign.gameofthrones.network.RestService;
import com.softdesign.gameofthrones.network.ServiceGenerator;
import com.softdesign.gameofthrones.network.results.CharacterRes;
import com.softdesign.gameofthrones.network.results.HouseRes;
import com.softdesign.gameofthrones.utils.ConstantManager;
import com.softdesign.gameofthrones.utils.GameofthronesApplication;
import com.softdesign.gameofthrones.utils.NetworkStatusChecker;
import com.softdesign.gameofthrones.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softdesign.gameofthrones.data.database.GotDbSchema.*;

public class DataManager {


    //region Singleton

    private static DataManager sInstance = null;


    public static DataManager getInstance() {
        if (sInstance == null) {
            sInstance = new DataManager();
        }
        return sInstance;
    }

    //endregion

    //region General

    private Context mContext;
    private PreferencesManager mPreferencesManager;
    private RestService mRestService;
    private SQLiteDatabase mDataBase;

    private HouseRepository mHouseRepository;
    private CharacterRepository mCharacterRepository;

    private List<House> mHouses = new ArrayList<>();
    private Character mDetailData;

    private DataManager() {

        this.mContext = GameofthronesApplication.getContext();
        this.mPreferencesManager = new PreferencesManager();
        this.mRestService = ServiceGenerator.createService(RestService.class);
        this.mDataBase = new DataBaseHelper(mContext).getWritableDatabase();
        this.mHouseRepository = new HouseRepository(mDataBase);
        this.mCharacterRepository = new CharacterRepository(mDataBase);
    }


    public Context getContext() {
        return mContext;
    }


    public PreferencesManager getPreferencesManager() {
        return mPreferencesManager;
    }

    //endregion

    //region Model


    public List<House> getHouses() {
        return mHouses;
    }


    public Character getDetailData() {
        return mDetailData;
    }


    public void setDetailData(Character detailData) {
        mDetailData = detailData;
    }

    //endregion

    //region Network

    public boolean loadDataFromNetwork() {

        boolean result = false;

        for (int houseId : ConstantManager.HOUSES_ARRAY) {

            result = false;
            Response<HouseRes> response = null;
            Call<HouseRes>call = mRestService.getHouse(houseId);

            try {
                response = call.execute();
                if (!response.isSuccessful()) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

            HouseRes houseRes = response.body();
            House house = mHouseRepository.convertFromResponse(houseRes);
            mHouseRepository.addHouse(house);
            result = true;
        }

        if (!result) {
            return result;
        }

        boolean doWhile = true;
        int pageNum = 1;
        Response<List<CharacterRes>> response = null;

        do {

            result = false;
            Call<List<CharacterRes>>call = mRestService.getCharacters(pageNum, 100);

            try {
                response = call.execute();
                if (!response.isSuccessful()) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

            List<CharacterRes> list = response.body();
            for (CharacterRes item : list) {
                Character character = mCharacterRepository.convertFromResponse(item);
                mCharacterRepository.addCharacter(character);
            }

            pageNum++;
            if (list.isEmpty()) {
                doWhile = false;
            }
            result = true;
        } while (doWhile);

        return result;
    }

    //endregion

    //region Database

    public boolean checkHousesData() {

        Cursor c = null;
        boolean result = false;

        try {
            c = mDataBase.rawQuery("select " + HousesTable.Fields.ID + " from " + HousesTable.NAME + " limit 1", null);
            if (c != null) {
                if (c.getCount() > 0) {
                    result = true;
                }
            }
        }  catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (c != null && !c.isClosed()) {
                c.close();
            }
        }
        return result;
    }


    public void loadDataFromDb() {

        Cursor c_house = null;
        Cursor c_character = null;

        try {

            c_house = mDataBase.rawQuery("select * from houses", null);

            if(c_house != null && c_house.moveToFirst()) {

                do {

                    House house = new House(c_house.getInt(c_house.getColumnIndex(HousesTable.Fields.ID)));
                    house.setName(c_house.getString(c_house.getColumnIndex(HousesTable.Fields.NAME)));
                    house.setWords(c_house.getString(c_house.getColumnIndex(HousesTable.Fields.WORDS)));
                    house.setRegion(c_house.getString(c_house.getColumnIndex(HousesTable.Fields.REGION)));

                    String queryText =
                            "select " +
                                    "   c.* " +
                                    ",	h.name as housename " +
                                    ",	h.words as housewords " +
                                    ",	ifnull(f.name, '') as fathername " +
                                    ",	ifnull(m.name, '') as mothername " +
                                    "from Characters as c " +
                                    "   inner join houses as h on c.houseid = h.id " +
                                    "   left outer join characters as f on c.fatherid = f.id " +
                                    "   left outer join characters as m on c.motherid = m.id " +
                                    "where " +
                                    "   c.houseid = ? " +
                                    "order by " +
                                    "   c.name ";

                    c_character = mDataBase.rawQuery(queryText, new String[] {String.valueOf(house.getId())});

                    if (c_character != null && c_character.moveToFirst() ) {

                        do  {

                            Character character = new Character(c_character.getInt(c_character.getColumnIndex(CharactersTable.Fields.ID)));
                            character.setHouseId(c_character.getInt(c_character.getColumnIndex(CharactersTable.Fields.HOUSEID)));
                            character.setHouseWords(c_character.getString(c_character.getColumnIndex("housewords")));
                            character.setName(c_character.getString(c_character.getColumnIndex(CharactersTable.Fields.NAME)));
                            character.setBorn(c_character.getString(c_character.getColumnIndex(CharactersTable.Fields.BORN)));
                            character.setDied(c_character.getString(c_character.getColumnIndex(CharactersTable.Fields.DIED)));
                            character.setTitles(c_character.getString(c_character.getColumnIndex(CharactersTable.Fields.TITLES)));
                            character.setAliases(c_character.getString(c_character.getColumnIndex(CharactersTable.Fields.ALIASES)));
                            character.setFatherId(c_character.getInt(c_character.getColumnIndex(CharactersTable.Fields.FATHERID)));
                            character.setMotherId(c_character.getInt(c_character.getColumnIndex(CharactersTable.Fields.MOTHERID)));
                            character.setFatherName(c_character.getString(c_character.getColumnIndex("fathername")));
                            character.setMotherName(c_character.getString(c_character.getColumnIndex("mothername")));

                            house.getSwornMembers().add(character);

                        } while (c_character.moveToNext());
                    }

                    mHouses.add(house);

                } while (c_house.moveToNext());
            }
        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (c_house != null && !c_house.isClosed()) {
                c_house.close();
            }

            if (c_character != null && !c_character.isClosed()) {
                c_character.close();
            }
        }
    }

    //endregion
}
