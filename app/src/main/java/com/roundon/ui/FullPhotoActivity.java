package com.roundon.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.roundon.AppSplash;
import com.roundon.Config;
import com.roundon.R;
import com.roundon.model.Category;
import com.roundon.model.Exif;
import com.roundon.model.Photo;
import com.roundon.ui.widget.SplashLabel;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import uk.co.senab.photoview.PhotoViewAttacher;

public class FullPhotoActivity extends BaseActivity implements View.OnClickListener{

    @Bind(R.id.iv_photo)
    ImageView photoView;

    @Bind(R.id.make)
    SplashLabel make;

    @Bind(R.id.model)
    SplashLabel model;

    @Bind(R.id.exposure_time)
    SplashLabel exposure_time;

    @Bind(R.id.aperture)
    SplashLabel aperture;

    @Bind(R.id.focal_length)
    SplashLabel focal_length;

    @Bind(R.id.downloads)
    SplashLabel downloads;

    @Bind(R.id.likes)
    SplashLabel likes;

    @Bind(R.id.cameraman)
    SplashLabel cameraman;

    @Bind(R.id.category)
    SplashLabel category;

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
        Glide.with(this).load(photo.urls.regular).into(photoView);

        attacher = new PhotoViewAttacher(photoView);
        cameraman.setOnClickListener(this);

        getPhotoDetail(photo.photo_id);

    }

    public void photoInfo(Photo photo) {
        downloads.setDesc(photo.downloads + "");
        likes.setDesc(photo.likes + "");
        cameraman.setDesc(photo.user.name);
        String str = "";
        for (Category temp : photo.categories) {
            str += temp.title + ",";
        }

        if (!TextUtils.isEmpty(str)){
            category.setDesc(str.substring(0, str.length() - 1));
        }

    }

    public void setExif(Exif exif) {
        if (exif == null) {
            return;
        }
        make.setDesc(exif.make);
        model.setDesc(exif.model);
        exposure_time.setDesc(exif.exposure_time);
        aperture.setDesc(exif.aperture);
        focal_length.setDesc(exif.focal_length);
    }

    public void getPhotoDetail(String id) {
        Call<Photo> photoCall = AppSplash.getSplashService().getPhoto(id, Config.aapID);
        photoCall.enqueue(new Callback<Photo>() {
            @Override
            public void onResponse(Response<Photo> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Logger.i(response.body().toString());

                    Photo photo = response.body();
                    photoInfo(photo);
                    setExif(photo.exif);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cameraman:
                MySelfActivity.openSelfActivity(FullPhotoActivity.this,photo.user);
                break;
            default:
                break;
        }
    }
}
