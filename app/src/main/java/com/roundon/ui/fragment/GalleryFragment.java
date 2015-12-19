package com.roundon.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.orhanobut.logger.Logger;
import com.roundon.AppSplash;
import com.roundon.Config;
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


public class GalleryFragment extends Fragment implements XRecyclerView.LoadingListener {

    private static final String ARG_ID = "id";
    private static final String ARG_FROM = "from";

    private String id;
    private int from;

    @Bind(R.id.recyclerview)
    XRecyclerView recyclerView;

    CirclePhotoAdapter photoAdapter;

    public GalleryFragment() {
    }

    public static GalleryFragment newInstance(String id, int from) {
        GalleryFragment fragment = new GalleryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ID, id);
        args.putInt(ARG_FROM, from);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getString(ARG_ID);
            from = getArguments().getInt(ARG_FROM, 0);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        ButterKnife.bind(this, view);
        if (from == 0) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
        } else {
            GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
            recyclerView.setLayoutManager(layoutManager);
        }
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLaodingMoreProgressStyle(ProgressStyle.BallRotate);
        recyclerView.setLoadingListener(this);
        photoAdapter = new CirclePhotoAdapter(getActivity(), from);
        recyclerView.setAdapter(photoAdapter);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getPhotos(1);
    }

    public void getPhotos(final int page) {

        Call<List<Photo>> readCall = null;
        if (from == 1) {//获取用户自己的照片
            readCall = AppSplash.getSplashService().getUserPhotos(id, Config.aapID);
        } else if (from == 2) {//获取用户点赞的照片
            readCall = AppSplash.getSplashService().getLikePhotos(id, Config.aapID);
        } else if (from == 3) {//搜索

        } else if (from == 4) {//获取一个类型的照片
            readCall = AppSplash.getSplashService().getCategoryPhotos(id, Config.aapID);
        } else if (from == 5) {//获取一个批次的照片
            readCall = AppSplash.getSplashService().getCuratedBatchPhotos(id, Config.aapID);
        } else {//获取照片
            readCall = AppSplash.getSplashService().getPhotos(Config.aapID);
        }

        readCall.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Response<List<Photo>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    List<Photo> photos = response.body();
                    photoAdapter.addPhotos(photos);
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
        getPhotos(photoAdapter.getItemCount() / 10);
    }
}
