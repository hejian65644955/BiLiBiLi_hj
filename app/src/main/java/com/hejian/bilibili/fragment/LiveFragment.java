package com.hejian.bilibili.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.hejian.bilibili.R;
import com.hejian.bilibili.adapter.LiveRecycleAdapter;
import com.hejian.bilibili.bean.HomeBean;
import com.hejian.bilibili.utils.Utils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 何健 on 2017/3/21.
 */

public class LiveFragment extends BaseFragment {


    @InjectView(R.id.recyclerview_live)
    RecyclerView recyclerviewLive;
    private HomeBean homeBean;


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.live_fragment, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        getDataFromNet(Utils.LIVE_DATA);


    }


    private void getDataFromNet(String neturl) {
        OkHttpUtils.get()
                .url(neturl)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网失败了" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //Log.e("TAG", "GoodsListActivity的数据联网成功了=="+response);
                        processData(response);
                    }


                });
    }

    private void processData(String json) {
        homeBean = JSON.parseObject(json, HomeBean.class);
        LiveRecycleAdapter adapter = new LiveRecycleAdapter(homeBean,mContext);
        recyclerviewLive.setAdapter(adapter);
       // recyclerviewLive.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        GridLayoutManager manager = new GridLayoutManager(mContext, 1);
        recyclerviewLive.setLayoutManager(manager);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
            @Override
            public int getSpanSize(int position) {
                return 1;
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
