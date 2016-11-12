package com.softdesign.gameofthrones.network;

import com.softdesign.gameofthrones.network.interceptors.HeaderInterseptor;
import com.softdesign.gameofthrones.utils.AppConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder sBuilder = new Retrofit.Builder()
            .baseUrl(AppConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());


    public static <S> S createService(Class<S> serviceClass) {

        HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();
        loggin.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(new HeaderInterseptor());
        httpClient.addInterceptor(loggin);

        Retrofit retrofit = sBuilder.client(httpClient.build()).build();

        return retrofit.create(serviceClass);
    }
}
