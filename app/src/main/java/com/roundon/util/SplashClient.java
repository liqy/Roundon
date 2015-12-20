package com.roundon.util;

import com.roundon.service.SplashService;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by liqy on 15/12/18.
 */
public class SplashClient {
    public static final String API_URL = "https://api.unsplash.com/";
    public SplashService splashService;

    public SplashClient() {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(1, TimeUnit.MINUTES);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        splashService =retrofit.create(SplashService.class);
    }

    public SplashService getSplashService() {
        return splashService;
    }
}
