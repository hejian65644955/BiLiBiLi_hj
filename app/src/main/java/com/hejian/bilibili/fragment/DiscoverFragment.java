package com.hejian.bilibili.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 何健 on 2017/3/21.
 */

public class DiscoverFragment extends BaseFragment {


    private TextView textView;

    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        textView.setText("Discover");

    }
}
