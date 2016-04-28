package com.roundon.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.flyco.tablayout.SlidingTabLayout;
import com.orhanobut.logger.Logger;
import com.roundon.AppSplash;
import com.roundon.Config;
import com.roundon.R;
import com.roundon.client.OAuth2Client;
import com.roundon.ui.fragment.BatchFragment;
import com.roundon.ui.fragment.CategoryFragment;
import com.roundon.ui.fragment.GalleryFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.tl_1)
    SlidingTabLayout tabLayout;

    @Bind(R.id.vp)
    ViewPager viewPager;

    private final String[] titles = {
            "热门", "批次", "标签"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        viewPager.setAdapter(new SplashPagerAdapter(getSupportFragmentManager()));
        tabLayout.setViewPager(viewPager);

        authorize();
    }

    private class SplashPagerAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> fragments;

        public SplashPagerAdapter(FragmentManager fm) {
            super(fm);
            fragments = new ArrayList<>();
            fragments.add(GalleryFragment.newInstance("", 0));
            fragments.add(BatchFragment.newInstance("", ""));
            fragments.add(CategoryFragment.newInstance("", ""));
        }


        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }
    }

    void authorize() {
        String url = "https://unsplash.com/oauth/authorize?client_id=%1$s&redirect_uri=%2$s&scope=%3$s&response_type=code";
        StringRequest request = new StringRequest(Request.Method.GET,
                String.format(url, Config.aapID, Config.callbackURL, "public+read_user+write_user+read_photos+write_photos+write_likes"),
                createOAuthSuccessListener(),
                createReqErrorListener());
        OAuth2Client.getRequestQueue().add(request);
    }

    private Response.Listener<String> createOAuthSuccessListener() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Logger.i(response);
            }
        };
    }

    protected Response.ErrorListener createReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.i(error.getMessage());
            }
        };
    }


}
