package com.hejian.bilibili.fragment.recommend;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.hejian.bilibili.fragment.BaseFragment;

/**
 * Created by 何健 on 2017/3/22.
 */

public class DynamicFragment extends BaseFragment {
    TextView textView;
    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setTextColor(Color.RED);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {
        textView.setText("我是动态页面");

    }
}
