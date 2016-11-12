package com.softdesign.gameofthrones.data.model;

import java.util.ArrayList;
import java.util.List;

public class House {

    private int mId;
    private String mUrl;
    private String mWords;
    private String mName;
    private String mRegion;

    private List<Character> mSwornMembers = new ArrayList<>();

    public House(int id) {
        mId = id;
    }


    public int getId() {
        return mId;
    }


    public String getName() {
        return mName;
    }


    public void setName(String name) {
        mName = name;
    }


    public String getRegion() {
        return mRegion;
    }


    public void setRegion(String region) {
        mRegion = region;
    }


    public String getUrl() {
        return mUrl;
    }


    public void setUrl(String url) {
        mUrl = url;
    }


    public String getWords() {
        return mWords;
    }


    public void setWords(String words) {
        mWords = words;
    }

    public List<Character> getSwornMembers() {
        return mSwornMembers;
    }
}
