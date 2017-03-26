package com.hejian.bilibili.headactivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hankkin.gradationscroll.GradationScrollView;
import com.hejian.bilibili.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MemberActivity extends AppCompatActivity implements GradationScrollView.ScrollViewListener {

    @InjectView(R.id.scrollview)
    GradationScrollView scrollview;
    @InjectView(R.id.rl_header)
    RelativeLayout rlHeader;
    @InjectView(R.id.tv_member)
    TextView tvMember;
    @InjectView(R.id.ib_back)
    ImageButton ibBack;
    @InjectView(R.id.btn_tovip)
    Button btnTovip;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        ButterKnife.inject(this);
        initData();
    }

    private void initData() {

        ViewTreeObserver vto = rlHeader.getViewTreeObserver();
        //添加监听
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //移除视图树的监听
                rlHeader.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);

                height = rlHeader.getHeight();

                scrollview.setScrollViewListener(MemberActivity.this);
            }
        });

        //设置默认是隐藏的 //设置标题的背景颜色  -透明
        tvMember.setBackgroundColor(Color.argb((int) 0, 255, 0, 0));
    }


    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= 0) {
            //设置标题的背景颜色  -透明
            tvMember.setBackgroundColor(Color.argb((int) 0, 255, 0, 0));

        } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / height;
            float alpha = (255 * scale);
            //滑动距离 ： 总距离 = 改变的透明度 ： 总透明度
            //改变的透明度 = 总透明度*(滑动距离 ：总距离)

            tvMember.setTextColor(Color.argb((int) alpha, 255, 255, 255));
            tvMember.setBackgroundColor(Color.argb((int) alpha, 255, 0, 0));
        } else {
            //滑动到banner下面设置普通颜色 - 非透明
            tvMember.setBackgroundColor(Color.argb((int) 255, 255, 0, 0));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btn_tovip, R.id.ib_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_tovip:
                View view1 = View.inflate(MemberActivity.this, R.layout.menber_item, null);
                new AlertDialog.Builder(MemberActivity.this)
                        .setView(view1)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("取消",null)
                        .show();

                break;
            case R.id.ib_back:
                finish();
                break;
        }
    }
}
