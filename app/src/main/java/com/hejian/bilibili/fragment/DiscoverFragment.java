package com.hejian.bilibili.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.hejian.bilibili.R;
import com.hejian.bilibili.activity.GoodSInfoActivity;
import com.hejian.bilibili.activity.TopicCenterActivity;
import com.hejian.bilibili.bean.DiscoverBean;
import com.hejian.bilibili.utils.Utils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by 何健 on 2017/3/21.
 */

public class DiscoverFragment extends BaseFragment {

    @InjectView(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;
    @InjectView(R.id.btn_lookmore)
    TextView btnLookmore;
    @InjectView(R.id.scrollview)
    ScrollView scrollview;
    @InjectView(R.id.id_flowlayout1)
    TagFlowLayout idFlowlayout1;
    @InjectView(R.id.scrollview1)
    ScrollView scrollview1;
    @InjectView(R.id.iv_lookmore)
    ImageView ivLookmore;
    @InjectView(R.id.tv_topic)
    TextView tvTopic;
    @InjectView(R.id.action_center)
    TextView actionCenter;
    @InjectView(R.id.tv_shopping)
    TextView tvShopping;
    @InjectView(R.id.ic_quanzi_layout)
    RelativeLayout icQuanziLayout;
    private boolean flag = false;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.discover_fragment, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils.get()
                .url(Utils.DISCOVER_DATA)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网失败了" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "TagFragment的数据联网成功了==");
                        processData(response);

                    }
                });
    }

    private void processData(String json) {
        DiscoverBean discoverBean = JSON.parseObject(json, DiscoverBean.class);
        final List<DiscoverBean.DataBean.ListBean> list = discoverBean.getData().getList();
        if (list != null && list.size() > 0 && idFlowlayout != null && idFlowlayout1 != null) {
            idFlowlayout.setAdapter(new TagAdapter(list) {
                @Override
                public View getView(FlowLayout parent, int position, Object o) {
                    TextView tv = (TextView) View.inflate(mContext, R.layout.tv,
                            null);
                    tv.setText(list.get(position).getKeyword());
                    return tv;
                }
            });
            idFlowlayout1.setAdapter(new TagAdapter(list) {
                @Override
                public View getView(FlowLayout parent, int position, Object o) {
                    TextView tv = (TextView) View.inflate(mContext, R.layout.tv,
                            null);
                    tv.setText(list.get(position).getKeyword());
                    return tv;
                }
            });


            idFlowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    Toast.makeText(getActivity(), position + "", Toast.LENGTH_SHORT).show();
                    return true;

                }
            });
            idFlowlayout1.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    Toast.makeText(getActivity(), position + "", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });

        }

        //让scrollview不能划
        if (scrollview1 != null) {
            scrollview1.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });

        }

        if (btnLookmore != null) {


            btnLookmore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag) {
                        scrollview.setVisibility(View.GONE);
                        scrollview1.setVisibility(View.VISIBLE);
                        flag = !flag;
                        btnLookmore.setText("查看更多");
                        ivLookmore.setImageResource(R.drawable.ic_arrow_down_gray_round);
                    } else {
                        scrollview1.setVisibility(View.GONE);
                        scrollview.setVisibility(View.VISIBLE);
                        flag = !flag;
                        btnLookmore.setText("收起");
                        ivLookmore.setImageResource(R.drawable.ic_arrow_up_gray_round);
                    }
                }
            });

        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    @OnClick({R.id.action_center, R.id.topic_center_layout, R.id.ic_quanzi_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.topic_center_layout:
                startActivity(new Intent(mContext, TopicCenterActivity.class));
                break;
            case R.id.action_center:
                break;
            case R.id.ic_quanzi_layout:
                startActivity(new Intent(mContext, GoodSInfoActivity.class));
                break;
        }
    }

}
