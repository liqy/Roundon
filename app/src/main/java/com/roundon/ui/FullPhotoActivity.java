package com.roundon.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.roundon.R;
import com.roundon.model.Photo;

import butterknife.Bind;
import butterknife.ButterKnife;
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

    }
}
