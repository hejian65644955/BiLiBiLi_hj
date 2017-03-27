package com.hejian.bilibili.fragment.recommend;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.hejian.bilibili.R;
import com.hejian.bilibili.activity.GoodsInfoActivity;
import com.hejian.bilibili.adapter.SynthesizeAdapter;
import com.hejian.bilibili.bean.SynthesizeBean;
import com.hejian.bilibili.fragment.BaseFragment;
import com.hejian.bilibili.utils.Utils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 何健 on 2017/3/22.
 */

public class SynthesizeFragment extends BaseFragment {
    @InjectView(R.id.rv_sts)
    RecyclerView rvSts;
    private SynthesizeAdapter adapter;
    private SynthesizeBean synthesizeBean;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.synthesize_fragment, null);
        ButterKnife.inject(this, view);
        return view;

    }

    private void getDataFromNet(String url) {
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "联网成功");
                        processData(response);
                    }
                });
    }

    private void processData(String json) {
        synthesizeBean = JSON.parseObject(json, SynthesizeBean.class);
        adapter = new SynthesizeAdapter(synthesizeBean.getData(), mContext);
        if (adapter != null && rvSts != null) {
            rvSts.setAdapter(adapter);
            rvSts.setLayoutManager(new GridLayoutManager(mContext, 2));
            initListener();
        }

    }

    @Override
    public void initData() {
        getDataFromNet(Utils.SYNTHESIZE_DATA);
    }


    private void initListener() {
        adapter.setOnItemClickListener(new SynthesizeAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position, List<SynthesizeBean.DataBean> datas) {
               // Toast.makeText(mContext, "position===" + position, Toast.LENGTH_SHORT).show();
                Log.e("TAG", "2222222222222222222"+datas.get(position));
               if(datas.size()>0){
                    SynthesizeBean.DataBean dataBean = datas.get(position);
                    Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                    intent.putExtra("datas",dataBean);
                    intent.putExtra("position",position);
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
