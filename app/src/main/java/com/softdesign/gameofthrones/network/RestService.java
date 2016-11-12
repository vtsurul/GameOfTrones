package com.softdesign.gameofthrones.network;

import com.softdesign.gameofthrones.network.results.CharacterRes;
import com.softdesign.gameofthrones.network.results.HouseRes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestService {

    @GET("houses/{houseId}")
    Call<HouseRes> getHouse(@Path("houseId") int houseId);

    @GET("characters/{characterId}")
    Call<CharacterRes> getCharacter(@Path("characterId") int characterId);

    @GET("characters")
    Call<List<CharacterRes>> getCharacters(@Query("page") int page, @Query("pageSize") int pageSize);

}
