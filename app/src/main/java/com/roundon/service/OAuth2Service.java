package com.roundon.service;

import com.roundon.model.AccessToken;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by liqy on 16/1/10.
 */
public interface OAuth2Service {

    @GET("/authorize")
    Call<String> authorize(@Query("client_id") String client_id, @Query("redirect_uri") String redirect_uri, @Query("response_type") String response_type, @Query("scope") String scope);

    @POST("/token")
    Call<AccessToken> token(@Query("client_id") String client_id, @Query("redirect_uri") String redirect_uri, @Query("client_secret") String client_secret, @Query("code") String code, @Query("grant_type") String grant_type);
}
