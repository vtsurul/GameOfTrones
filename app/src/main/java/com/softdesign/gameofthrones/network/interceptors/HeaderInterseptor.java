package com.softdesign.gameofthrones.network.interceptors;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterseptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

/*
        PreferencesManager pm = DataManager.getInstance().getPreferencesManager();

        Request.Builder requestBuilder = chain.request().newBuilder()
                .header("X-Access-Token", pm.getAuthToken())
                .header("Request-User-Id", pm.getUserId())
                .header("User-Agent", "DevIntensiveApp");
*/

        Request.Builder requestBuilder = chain.request().newBuilder();

        return chain.proceed(requestBuilder.build());
    }
}
