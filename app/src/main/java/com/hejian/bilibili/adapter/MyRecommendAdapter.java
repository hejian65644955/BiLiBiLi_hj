package com.hejian.bilibili.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hejian.bilibili.fragment.BaseFragment;

import java.util.List;

/**
 * Created by 何健 on 2017/3/22.
 */

public class MyRecommendAdapter extends FragmentPagerAdapter {
    private final List<BaseFragment> fragments;
    private String[] titles = {"综合","动态"};
    public MyRecommendAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments =fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
