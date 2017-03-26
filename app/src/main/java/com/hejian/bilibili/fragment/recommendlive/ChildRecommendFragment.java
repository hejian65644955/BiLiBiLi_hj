package com.hejian.bilibili.fragment.recommendlive;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.hejian.bilibili.R;
import com.hejian.bilibili.adapter.HomeLiveRecycleViewAdapter;
import com.hejian.bilibili.bean.LiveTopBean;
import com.hejian.bilibili.fragment.BaseFragment;
import com.hejian.bilibili.utils.Utils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 何健 on 2017/3/24.
 * 推荐页面
 */

public class ChildRecommendFragment extends BaseFragment {

    @InjectView(R.id.rv_homelive)
    RecyclerView rvHomelive;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.cdrc_fragment, null);
        ButterKnife.inject(this,view);
        return view;
    }

    @Override
    public void initData() {
        getDataFromNet(Utils.LIVE_TOP);

    }

    private void getDataFromNet(String liveTop) {
        OkHttpUtils.get()
                .url(liveTop)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        processData(response);

                    }
                });

    }

    private void processData(String json) {
        LiveTopBean liveTopBean = JSON.parseObject(json, LiveTopBean.class);
        HomeLiveRecycleViewAdapter adapter = new HomeLiveRecycleViewAdapter(liveTopBean.getData(), mContext);
        rvHomelive.setAdapter(adapter);
        rvHomelive.setLayoutManager(new GridLayoutManager(mContext,1));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
