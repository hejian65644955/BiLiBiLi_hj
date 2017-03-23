package com.hejian.bilibili.fragment.recommend;

import android.view.View;

import com.hejian.bilibili.R;
import com.hejian.bilibili.fragment.BaseFragment;

/**
 * Created by 何健 on 2017/3/22.
 */

public class DynamicFragment extends BaseFragment {
    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.dynamic_fragment, null);
        return view;
    }

    @Override
    public void initData() {

    }
}
