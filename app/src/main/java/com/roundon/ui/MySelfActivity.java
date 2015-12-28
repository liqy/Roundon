package com.roundon.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flyco.tablayout.SlidingTabLayout;
import com.orhanobut.logger.Logger;
import com.roundon.AppSplash;
import com.roundon.Config;
import com.roundon.R;
import com.roundon.model.User;
import com.roundon.ui.fragment.GalleryFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MySelfActivity extends AppCompatActivity {

    public User user;

    @Bind(R.id.profile_image)
    CircleImageView profile_image;

    @Bind(R.id.username)
    TextView username;

    @Bind(R.id.tl_1)
    SlidingTabLayout tabLayout;

    @Bind(R.id.vp)
    ViewPager viewPager;

    private final String[] titles = {
            "Me", "Like"
    };

    public static void openSelfActivity(Activity activity, User user) {
        Intent intent = new Intent(activity, MySelfActivity.class);
        intent.putExtra("User", user);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_self);
        ButterKnife.bind(this);
        user = getIntent().getParcelableExtra("User");

        username.setText(user.name);
        Glide.with(MySelfActivity.this).load(user.profile_image.large).into(profile_image);

        viewPager.setAdapter(new SplashPagerAdapter(getSupportFragmentManager()));
        tabLayout.setViewPager(viewPager);

    }


    public void getInfo() {
        Call<User> userCall = AppSplash.getSplashService().getProfile(user.username, Config.aapID);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    user = response.body();
                    Logger.i(user.toString());
                    username.setText(user.first_name+" "+user.last_name);
                    Glide.with(MySelfActivity.this).load(user.profile_image.large).into(profile_image);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private class SplashPagerAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> fragments;

        public SplashPagerAdapter(FragmentManager fm) {
            super(fm);
            fragments = new ArrayList<>();
            fragments.add(GalleryFragment.newInstance(user.username, 1));
            fragments.add(GalleryFragment.newInstance(user.username, 2));
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
}
