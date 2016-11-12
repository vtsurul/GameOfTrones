package com.softdesign.gameofthrones.data.model;

public class Character {

    private int mId;
    private int mHouseId;
    private String mHouseWords;
    private String mUrl;
    private String mName;
    private String mBorn;
    private String mDied;
    private String mTitles;
    private String mAliases;
    private int mFatherId;
    private int mMotherId;
    private String mFatherName;
    private String mMotherName;


    public Character(int id) {
        mId = id;
    }

    public String getAliases() {
        return mAliases;
    }


    public void setAliases(String aliases) {
        mAliases = aliases;
    }


    public String getBorn() {
        return mBorn;
    }


    public void setBorn(String born) {
        mBorn = born;
    }


    public String getDied() {
        return mDied;
    }


    public void setDied(String died) {
        mDied = died;
    }


    public int getFatherId() {
        return mFatherId;
    }


    public void setFatherId(int father) {
        mFatherId = father;
    }


    public int getHouseId() {
        return mHouseId;
    }


    public void setHouseId(int houseId) {
        mHouseId = houseId;
    }


    public String getHouseWords() {
        return mHouseWords;
    }


    public void setHouseWords(String houseWords) {
        mHouseWords = houseWords;
    }


    public int getId() {
        return mId;
    }


    public int getMotherId() {
        return mMotherId;
    }


    public void setMotherId(int motherId) {
        mMotherId = motherId;
    }


    public String getName() {
        return mName;
    }


    public void setName(String name) {
        mName = name;
    }


    public String getTitles() {
        return mTitles;
    }


    public void setTitles(String titles) {
        mTitles = titles;
    }


    public String getUrl() {
        return mUrl;
    }


    public void setUrl(String url) {
        mUrl = url;
    }


    public String getFatherName() {
        return mFatherName;
    }


    public String getMotherName() {
        return mMotherName;
    }


    public void setFatherName(String fatherName) {
        mFatherName = fatherName;
    }


    public void setMotherName(String motherName) {
        mMotherName = motherName;
    }
}
