package com.softdesign.gameofthrones.utils;

public interface ConstantManager {

    String TAG_PREFIX = "GoT ";

    String HOUSE_ID_KEY = "HOUSE_ID_KEY";
    String HOUSE_ICON_KEY = "HOUSE_ICON_KEY";

    int HOUSE_ID_STARK = 362;
    int HOUSE_ID_LANNISTER = 229;
    int HOUSE_ID_TARGARYEN = 378;

    int[] HOUSES_ARRAY = {HOUSE_ID_STARK, HOUSE_ID_LANNISTER, HOUSE_ID_TARGARYEN};

    int HTTP_CODE_OK = 200;

    public static final String BROADCAST_ACTION = "com.softdesign.gameofthrones.BROADCAST";
    public static final String EXTENDED_DATA_STATUS = "com.softdesign.gameofthrones.STATUS";
    public static final String EXTENDED_STATUS_LOG = "com.softdesign.gameofthrones.LOG";

    long SPLASH_TIME_OUT = 3000;
}
