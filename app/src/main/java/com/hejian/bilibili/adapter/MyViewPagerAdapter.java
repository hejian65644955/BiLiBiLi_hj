package com.hejian.bilibili.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hejian.bilibili.fragment.BaseFragment;

import java.util.List;

/**
 * Created by 何健 on 2017/3/21.
 */

public class MyViewPagerAdapter extends FragmentPagerAdapter {
    private final List<BaseFragment> fragments;
    private String[] titles;


    public MyViewPagerAdapter(FragmentManager supportFragmentManager, List<BaseFragment> fragments, String[] titles) {
        super(supportFragmentManager);
        this.fragments =fragments;
        this.titles =titles;
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
