package com.roundon.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.roundon.AppSplash;
import com.roundon.Config;
import com.roundon.R;
import com.roundon.model.Batch;
import com.roundon.ui.GalleryActivity;
import com.roundon.ui.adapter.BatchAdapter;
import com.roundon.util.RecyclerUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class BatchFragment extends Fragment implements XRecyclerView.LoadingListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    @Bind(R.id.recyclerview)
    XRecyclerView recyclerView;

    BatchAdapter batchAdapter;

    public BatchFragment() {
        // Required empty public constructor
    }

    public static BatchFragment newInstance(String param1, String param2) {
        BatchFragment fragment = new BatchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_batch, container, false);
        ButterKnife.bind(this, view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLaodingMoreProgressStyle(ProgressStyle.BallRotate);
        recyclerView.setLoadingListener(this);
        recyclerView.addOnItemTouchListener(new RecyclerUtils.RecyclerItemClickListener(
                getActivity(), new RecyclerUtils.RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Batch batch=batchAdapter.getData(position);
                GalleryActivity.openGalleryActivity(getActivity(), 5, String.valueOf(batch.id));
            }
        }
        ));

        batchAdapter = new BatchAdapter(getActivity());
        recyclerView.setAdapter(batchAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getCuratedBatches(1);
    }

    public void getCuratedBatches(final int page) {
        Call<List<Batch>> readCall = AppSplash.getSplashService().getCuratedBatches(Config.aapID, page);
        readCall.enqueue(new Callback<List<Batch>>() {
            @Override
            public void onResponse(Response<List<Batch>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    List<Batch> batches = response.body();
                    batchAdapter.addList(batches,page);
                }
                complete(page);
            }

            @Override
            public void onFailure(Throwable t) {
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
        getCuratedBatches(1);
    }

    @Override
    public void onLoadMore() {
        int page = (batchAdapter.getItemCount() / 24) + 1;
        getCuratedBatches(page);
    }
}
