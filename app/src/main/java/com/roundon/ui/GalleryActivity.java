package com.roundon.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.orhanobut.logger.Logger;
import com.roundon.AppSplash;
import com.roundon.Config;
import com.roundon.R;
import com.roundon.model.Photo;
import com.roundon.ui.adapter.CardPhotoAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class GalleryActivity extends AppCompatActivity implements XRecyclerView.LoadingListener {

    /**
     * 1:获取用户自己的照片
     * 2:获取用户点赞的照片
     * 3:搜索
     * 4:获取一个类型的照片
     * 5:获取一个批次的照片
     *
     * @param activity
     * @param from
     * @param id
     */
    public static void openGalleryActivity(Activity activity, int from, String id) {
        Intent intent = new Intent(activity, GalleryActivity.class);
        intent.putExtra("ID", id);
        intent.putExtra("from", from);
        activity.startActivity(intent);
    }

    @Bind(R.id.recyclerview)
    XRecyclerView recyclerView;

    CardPhotoAdapter photoAdapter;

    String id;
    int from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        id = getIntent().getStringExtra("ID");
        from = getIntent().getIntExtra("from", 0);

        ButterKnife.bind(this);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLaodingMoreProgressStyle(ProgressStyle.BallRotate);
        recyclerView.setLoadingListener(this);
        photoAdapter = new CardPhotoAdapter(this, 1);
        recyclerView.setAdapter(photoAdapter);

        getPhotos(1);
    }

    public void getPhotos(final int page) {

        Call<List<Photo>> readCall = null;
        if (from == 1) {//获取用户自己的照片
            readCall = AppSplash.getSplashService().getUserPhotos(id, Config.aapID, page);
        } else if (from == 2) {//获取用户点赞的照片
            readCall = AppSplash.getSplashService().getLikePhotos(id, Config.aapID, page);
        } else if (from == 3) {//搜索

        } else if (from == 4) {//获取一个类型的照片
            readCall = AppSplash.getSplashService().getCategoryPhotos(id, Config.aapID, page);
        } else if (from == 5) {//获取一个批次的照片
            readCall = AppSplash.getSplashService().getCuratedBatchPhotos(id, Config.aapID);
        } else {//获取照片
            readCall = AppSplash.getSplashService().getPhotos(Config.aapID, page);
        }

        readCall.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Response<List<Photo>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    List<Photo> photos = response.body();
                    photoAdapter.addPhotos(photos,page);
                } else {
                    Logger.i(response.message());
                }

                complete(page);
            }

            @Override
            public void onFailure(Throwable t) {
                Logger.i(t.getMessage());
                complete(page);
            }
        });
    }

    public void complete(int page) {
        if (page == 1) {
            recyclerView.refreshComplete();
        } else {
            recyclerView.loadMoreComplete();
        }
    }

    @Override
    public void onRefresh() {
        getPhotos(1);
    }

    @Override
    public void onLoadMore() {
        int page = (photoAdapter.getItemCount() / 25) + 1;
        getPhotos(page);
    }
}
