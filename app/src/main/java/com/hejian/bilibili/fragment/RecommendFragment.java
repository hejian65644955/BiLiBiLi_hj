package com.hejian.bilibili.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hejian.bilibili.R;
import com.hejian.bilibili.adapter.MyRecommendAdapter;
import com.hejian.bilibili.fragment.recommend.DynamicFragment;
import com.hejian.bilibili.fragment.recommend.SynthesizeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 何健 on 2017/3/21.
 */

public class RecommendFragment extends BaseFragment {


    @InjectView(R.id.tablayout)
    TabLayout tablayout;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    private List<BaseFragment> fragments;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.recommend_fragment, null);
        ButterKnife.inject(this,view);
        return view;
    }

    @Override
    public void initData() {
        initFragments();
        MyRecommendAdapter adapter = new MyRecommendAdapter(getFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

        //关联viewpager
        tablayout.setupWithViewPager(viewPager);
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);


    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new SynthesizeFragment());
        fragments.add(new DynamicFragment());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
