package com.hejian.bilibili.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hankkin.gradationscroll.GradationScrollView;
import com.hejian.bilibili.R;
import com.hejian.bilibili.adapter.MyViewPagerAdapter;
import com.hejian.bilibili.fragment.BaseFragment;
import com.hejian.bilibili.fragment.recommendlive.ChildRecommendFragment;
import com.hejian.bilibili.fragment.recommendlive.DarwPlaceFragment;
import com.hejian.bilibili.fragment.recommendlive.HomeRecommendFragment;
import com.hejian.bilibili.fragment.recommendlive.NetGamesFragment;
import com.hejian.bilibili.fragment.recommendlive.PhoneGamesFragment;
import com.hejian.bilibili.fragment.recommendlive.PhoneLiveFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class RecommendLiveActivity extends AppCompatActivity implements GradationScrollView.ScrollViewListener {


    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    @InjectView(R.id.activity_recommend_live)
    LinearLayout activityRecommendLive;
    @InjectView(R.id.tablayout)
    TabLayout tablayout;
    @InjectView(R.id.rl_top)
    RelativeLayout rlTop;
    @InjectView(R.id.scrollview)
    GradationScrollView scrollview;
    @InjectView(R.id.iv_back_live)
    ImageView ivBackLive;
    @InjectView(R.id.iv_search_recommend)
    ImageView ivSearchRecommend;
    private List<BaseFragment> fragments;
    private String[] titles = new String[]{"推荐", "手机直播", "手游直播", "萌宅推荐", "绘画专区", "网络游戏"};
    private int height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_live);
        ButterKnife.inject(this);
        initData();


    }

    private void initData() {
        initFragments();

        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);

        tablayout.setupWithViewPager(viewPager);
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tablayout.setTabsFromPagerAdapter(adapter);

        ViewTreeObserver vto = rlTop.getViewTreeObserver();
        //添加监听
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //移除视图树的监听
                rlTop.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);

                height = rlTop.getHeight();

                scrollview.setScrollViewListener(RecommendLiveActivity.this);
            }
        });

    }

    private void initFragments() {
        fragments = new ArrayList<>();
        //fragments.add(new LiveFragment());
        fragments.add(new ChildRecommendFragment());
        fragments.add(new PhoneLiveFragment());
        fragments.add(new PhoneGamesFragment());
        fragments.add(new HomeRecommendFragment());
        fragments.add(new DarwPlaceFragment());
        fragments.add(new NetGamesFragment());
    }

    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {

    }

    @OnClick(R.id.iv_back_live)
    public void onClick() {
        finish();
    }
}
