package com.hejian.bilibili;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hejian.bilibili.adapter.MyViewPagerAdapter;
import com.hejian.bilibili.fragment.BaseFragment;
import com.hejian.bilibili.fragment.DiscoverFragment;
import com.hejian.bilibili.fragment.LiveFragment;
import com.hejian.bilibili.fragment.PartitionFragment;
import com.hejian.bilibili.fragment.RecommendFragment;
import com.hejian.bilibili.fragment.ToThemFragment;
import com.hejian.bilibili.headactivity.MemberActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @InjectView(R.id.iv_click)
    ImageView ivClick;
    @InjectView(R.id.tablayout)
    TabLayout tablayout;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    //@InjectView(R.id.toolBar)
    Toolbar toolBar1;
    //@InjectView(R.id.appbar)
    AppBarLayout appbar;
    @InjectView(R.id.ll_click)
    LinearLayout llClick;
    private List<BaseFragment> fragments;
    private boolean isOpen = true;

    private String[] titles = new String[]{"直播", "推荐","追番","分区","发现"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        llClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        initData();
    }

    private void initData() {
        initFragment();
        //设置适配器
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager(), fragments,titles);
        viewPager.setAdapter(adapter);

        //关联ViewPager
        tablayout.setupWithViewPager(viewPager);
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tablayout.setTabsFromPagerAdapter(adapter);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new LiveFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new ToThemFragment());
        fragments.add(new PartitionFragment());
        fragments.add(new DiscoverFragment());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home_main) {//首页
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {//我的大会员
            Intent intent = new Intent(MainActivity.this,MemberActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {//会员积分

        } else if (id == R.id.nav_manage) {//离线缓存

        } else if (id == R.id.nav_share) {//主题选择

        } else if (id == R.id.nav_send) {//设置与帮助

        }else if (id == R.id.see_later) {//稍后再看

        } else if (id == R.id.my_collect) {//我的收藏

        } else if (id == R.id.nav_history) {//历史纪录

        } else if (id == R.id.my_attention) {//我的关注

        }else if (id == R.id.nav_purse) {//B币钱包

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

 /*   private int startY;
    private int startX;
    private boolean isScrollY;
    private boolean isFirst;*/

   /* //tollBar 回弹效果
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int eventY = (int) ev.getY();
        int eventX = (int) ev.getX();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = eventY;
                startX = eventX;
                isFirst = true;
                break;
            case MotionEvent.ACTION_MOVE:
                if (isFirst) {
                    if (Math.abs(eventX - startX) > Math.abs(eventY - startY) && Math.abs(eventX - startX) > toolBar1.getHeight() * 0.30) {
                        isScrollY = false;
                        isFirst = false;
                        appbar.setExpanded(isOpen);
                    } else if (Math.abs(eventY - startY) > Math.abs(eventX - startX) && Math.abs(eventY - startY) > toolBar1.getHeight() * 0.30) {
                        isScrollY = true;
                        isFirst = false;
                    }
                }
                if (isOpen) {
                    if (startY < eventY) {
                        startY = eventY;
                    }
                } else {
                    if (startY > eventY) {
                        startY = eventY;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (isScrollY) {
                    if (isOpen) {
                        if (startY - eventY > toolBar1.getHeight() * 0.36) {
                            appbar.setExpanded(false);
                            isOpen = false;
                        } else {
                            appbar.setExpanded(true);
                            isOpen = true;
                        }
                    } else {
                        if (eventY - startY > toolBar1.getHeight() * 0.36) {
                            appbar.setExpanded(true);
                            isOpen = true;
                        } else {
                            appbar.setExpanded(false);
                            isOpen = false;
                        }
                    }
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
*/
}
