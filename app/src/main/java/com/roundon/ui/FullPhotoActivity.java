package com.roundon.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.roundon.AppSplash;
import com.roundon.Config;
import com.roundon.R;
import com.roundon.model.Photo;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import uk.co.senab.photoview.PhotoViewAttacher;

public class FullPhotoActivity extends AppCompatActivity {

    @Bind(R.id.iv_photo)
    ImageView photoView;

    Photo photo;
    PhotoViewAttacher attacher;

    public static void openFullPhotoActivity(Activity activity, Photo photo) {
        Intent intent = new Intent(activity, FullPhotoActivity.class);
        intent.putExtra("Photo", photo);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_photo);
        ButterKnife.bind(this);

        photo = getIntent().getParcelableExtra("Photo");
        Glide.with(this).load(photo.urls.small).into(photoView);

        attacher=new PhotoViewAttacher(photoView);

        getPhotoDetail(photo.photo_id);

    }

    public void getPhotoDetail(String id){
       Call<Photo> photoCall= AppSplash.getSplashService().getPhoto(id, Config.aapID);
        photoCall.enqueue(new Callback<Photo>() {
            @Override
            public void onResponse(Response<Photo> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    Logger.i(response.body().toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }
}
