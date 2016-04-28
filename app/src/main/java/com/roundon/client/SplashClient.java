package com.roundon.client;

import com.roundon.service.SplashService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;


/**
 * Created by liqy on 15/12/18.
 */
public class SplashClient {
    public static final String API_URL = "https://api.unsplash.com/";
    public SplashService splashService;

    public SplashClient() {
        OkHttpClient client = new OkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        splashService = retrofit.create(SplashService.class);
    }

    public SplashService getSplashService() {
        return splashService;
    }
}
