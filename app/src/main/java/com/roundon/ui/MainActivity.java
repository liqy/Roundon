package com.roundon.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.roundon.R;
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

    }

    private class SplashPagerAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> fragments;

        public SplashPagerAdapter(FragmentManager fm) {
            super(fm);
            fragments=new ArrayList<>();
            fragments.add(GalleryFragment.newInstance("", 0));
            fragments.add(BatchFragment.newInstance("",""));
            fragments.add(CategoryFragment.newInstance("",""));
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
