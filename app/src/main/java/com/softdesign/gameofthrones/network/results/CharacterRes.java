package com.softdesign.gameofthrones.network.results;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CharacterRes {

    @SerializedName("url")
    @Expose
    public String url;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("born")
    @Expose
    public String born;

    @SerializedName("died")
    @Expose
    public String died;

    @SerializedName("titles")
    @Expose
    public List<String> titles = new ArrayList<String>();

    @SerializedName("aliases")
    @Expose
    public List<String> aliases = new ArrayList<String>();

    @SerializedName("father")
    @Expose
    public String father;

    @SerializedName("mother")
    @Expose
    public String mother;

    @SerializedName("allegiances")
    @Expose
    public List<String> allegiances = new ArrayList<String>();

//    @SerializedName("gender")
//    @Expose
//    public String gender;
//
//    @SerializedName("culture")
//    @Expose
//    public String culture;
//
//    @SerializedName("spouse")
//    @Expose
//    public String spouse;
//
//    @SerializedName("books")
//    @Expose
//    public List<String> books = new ArrayList<String>();
//
//    @SerializedName("povBooks")
//    @Expose
//    public List<String> povBooks = new ArrayList<String>();
//
//    @SerializedName("tvSeries")
//    @Expose
//    public List<String> tvSeries = new ArrayList<String>();
//
//    @SerializedName("playedBy")
//    @Expose
//    public List<String> playedBy = new ArrayList<String>();
}