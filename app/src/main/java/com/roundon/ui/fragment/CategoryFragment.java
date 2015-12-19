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
import com.roundon.model.Category;
import com.roundon.ui.adapter.CategoryAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class CategoryFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    @Bind(R.id.recyclerview)
    XRecyclerView recyclerView;

    CategoryAdapter categoryAdapter;

    public CategoryFragment() {
    }

    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gategory, container, false);
        ButterKnife.bind(this, view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLaodingMoreProgressStyle(ProgressStyle.BallRotate);
        categoryAdapter = new CategoryAdapter(getActivity());
        recyclerView.setAdapter(categoryAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getCategories();
    }

    public void getCategories(){
        Call<List<Category>> readCall= AppSplash.getSplashService().getCategories(Config.aapID);
        readCall.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Response<List<Category>> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    List<Category> categories=response.body();
                    categoryAdapter.addList(categories);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
