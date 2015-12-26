package com.roundon.service;

import com.roundon.model.Batch;
import com.roundon.model.Category;
import com.roundon.model.LikePhoto;
import com.roundon.model.Photo;
import com.roundon.model.Total;
import com.roundon.model.User;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by liqy on 15/12/18.
 */
public interface SplashService {

    //CURRENT USER

    /**
     * Get the user’s profile
     *
     * @param client_id
     * @return
     */
    @Headers("Accept-Version:v1")
    @GET("/me")
    Call<User> getCurrentProfile(@Query("client_id") String client_id);

    /**
     * Update the current user’s profile
     *
     * @param client_id
     * @return
     */
    @Headers("Accept-Version:v1")
    @PUT("/me")
    Call<User> updateCurrentProfile(@Query("client_id") String client_id);


    //USERS

    /**
     * Retrieve public details on a given user.
     *
     * @param username
     * @param client_id
     * @return
     */
    @Headers("Accept-Version:v1")
    @GET("/users/{username}")
    Call<User> getProfile(@Path("username") String username, @Query("client_id") String client_id);


    /**
     * Get a list of photos uploaded by a user
     *
     * @param username
     * @param client_id
     * @return
     */
    @Headers("Accept-Version:v1")
    @GET("/users/{username}/photos?per_page=24")
    Call<List<Photo>> getUserPhotos(@Path("username") String username, @Query("client_id") String client_id, @Query("page") int page);

    /**
     * Get a list of photos liked by a user
     *
     * @param username
     * @param client_id
     * @return
     */
    @Headers("Accept-Version:v1")
    @GET("/users/{username}/likes?per_page=24")
    Call<List<Photo>> getLikePhotos(@Path("username") String username, @Query("client_id") String client_id, @Query("page") int page);


    //PHOTOS

    /**
     * Get a single page from the list of all photos
     *
     * @param client_id
     * @return
     */
    @Headers("Accept-Version:v1")
    @GET("/photos?per_page=24")
    Call<List<Photo>> getPhotos(@Query("client_id") String client_id, @Query("page") int page);


    /**
     * Retrieve a single photo
     *
     * @param id
     * @param client_id
     * @return
     */
    @Headers("Accept-Version:v1")
    @GET("/photos/{id}")
    Call<Photo> getPhoto(@Path("id") String id, @Query("client_id") String client_id);

    /**
     * Retrieve a single random photo, given optional filters
     *
     * @param client_id
     * @return
     */
    @Headers("Accept-Version:v1")
    @GET("/photos/random")
    Call<Photo> getRandomPhoto(@Query("client_id") String client_id);

    //Like a photo

    /**
     * Like a photo on behalf of the logged-in user. This requires the write_likes scope
     *
     * @param id
     * @param client_id
     * @return
     */
    @Headers("Accept-Version:v1")
    @POST("/photos/{id}/like")
    Call<LikePhoto> likePhoto(@Path("id") String id, @Query("client_id") String client_id);


    //CATEGORIES

    /**
     * Get a list of all photo categories.
     *
     * @param client_id
     * @return
     */
    @Headers("Accept-Version:v1")
    @GET("/categories")
    Call<List<Category>> getCategories(@Query("client_id") String client_id);

    /**
     * Retrieve a single category.
     *
     * @param id
     * @param client_id
     * @return
     */
    @Headers("Accept-Version:v1")
    @GET("/categories/{id}")
    Call<Category> getCategory(@Path("id") String id, @Query("client_id") String client_id);

    /**
     * Retrieve a single category’s photos.
     *
     * @param id
     * @param client_id
     * @return
     */
    @Headers("Accept-Version:v1")
    @GET("/categories/{id}/photos?per_page=24")
    Call<List<Photo>> getCategoryPhotos(@Path("id") String id, @Query("client_id") String client_id, @Query("page") int page);

//CURATED BATCHES

    /**
     * Get a single page from the list of all curated batches.
     *
     * @param client_id
     * @return
     */
    @Headers("Accept-Version:v1")
    @GET("/curated_batches?per_page=24")
    Call<List<Batch>> getCuratedBatches(@Query("client_id") String client_id, @Query("page") int page);

    /**
     * Retrieve a single batch
     *
     * @param id
     * @param client_id
     * @return
     */
    @Headers("Accept-Version:v1")
    @GET("/curated_batches/{id}")
    Call<Batch> getCuratedBatch(@Path("id") String id, @Query("client_id") String client_id);


    /**
     * Retrieve a single batch’s ten photos.
     *
     * @param id
     * @param client_id
     * @return
     */
    @Headers("Accept-Version:v1")
    @GET("/curated_batches/{id}/photos")
    Call<List<Photo>> getCuratedBatchPhotos(@Path("id") String id, @Query("client_id") String client_id);

    //STATS

    /**
     * Get a list of download counts for all of Unsplash.
     *
     * @param client_id
     * @return
     */
    @Headers("Accept-Version:v1")
    @GET("/stats/total")
    Call<Total> getTotal(@Query("client_id") String client_id);

}
