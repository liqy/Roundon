package com.roundon.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.orhanobut.logger.Logger;
import com.roundon.AppSplash;
import com.roundon.R;
import com.roundon.model.Photo;
import com.roundon.ui.adapter.CirclePhotoAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends BaseActivity implements XRecyclerView.LoadingListener {

    @Bind(R.id.recyclerview)
    XRecyclerView recyclerView;

    CirclePhotoAdapter photoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLaodingMoreProgressStyle(ProgressStyle.BallRotate);
        recyclerView.setLoadingListener(this);
        photoAdapter = new CirclePhotoAdapter(this);
        recyclerView.setAdapter(photoAdapter);




        getPhotos(1);

    }

    public void getPhotos(final int page) {
        Call<List<Photo>> readCall = AppSplash.getSplashService().getPhotos();

        readCall.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Response<List<Photo>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    List<Photo> photos = response.body();
                    photoAdapter.addPhotos(photos);
                } else {
                    Logger.i(response.message());
                }

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
        getPhotos(photoAdapter.getItemCount() / 10);
    }
}
