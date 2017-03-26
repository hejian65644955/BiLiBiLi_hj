package com.hejian.bilibili.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.hejian.bilibili.R;
import com.hejian.bilibili.adapter.TopicCenterAdapter;
import com.hejian.bilibili.bean.TopicCenterBean;
import com.hejian.bilibili.utils.Utils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

public class TopicCenterActivity extends AppCompatActivity {

    @InjectView(R.id.rv_topic_center)
    RecyclerView rvTopicCenter;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.ll_topic_center)
    LinearLayout llTopicCenter;
    @InjectView(R.id.activity_topic_center)
    RelativeLayout activityTopicCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_center);
        ButterKnife.inject(this);

        initData();
    }

    private void initData() {
        getDataFromNet();

    }

    private void getDataFromNet() {
        OkHttpUtils.get()
                .url(Utils.TOPICCENTER_DATA)
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
        TopicCenterBean topicCenterBean = JSON.parseObject(json, TopicCenterBean.class);
        TopicCenterAdapter adapter = new TopicCenterAdapter(topicCenterBean.getList(), this);
        rvTopicCenter.setAdapter(adapter);
        rvTopicCenter.setLayoutManager(new GridLayoutManager(this, 1));
    }

    @OnClick({R.id.iv_back, R.id.ll_topic_center, R.id.activity_topic_center})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_topic_center:
                break;
            case R.id.activity_topic_center:
                break;
        }
    }
}
