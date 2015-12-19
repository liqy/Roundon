package com.roundon.service;

import com.roundon.model.Photo;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;

/**
 * Created by liqy on 15/12/18.
 */
public interface SplashService {

    @Headers("Accept-Version:v1")
    @GET("/photos?client_id=7fe698afc796df668de033e8b096ab1d63ea64ce8b92a3f13b89a439db9ccfd1")
    Call<List<Photo>> getPhotos();

}
