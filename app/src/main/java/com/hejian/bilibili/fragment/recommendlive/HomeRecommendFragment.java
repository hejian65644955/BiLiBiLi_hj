package com.hejian.bilibili.fragment.recommendlive;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.hejian.bilibili.fragment.BaseFragment;

/**
 * Created by 何健 on 2017/3/24.
 * 萌宅推荐页面
 */

public class HomeRecommendFragment extends BaseFragment {

    TextView textView;
    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);
        return textView;
    }

    @Override
    public void initData() {
        textView.setText("萌宅推荐页面");

    }
}
