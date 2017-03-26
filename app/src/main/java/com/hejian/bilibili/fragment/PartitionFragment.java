package com.hejian.bilibili.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.hejian.bilibili.R;
import com.hejian.bilibili.activity.RecommendLiveActivity;
import com.hejian.bilibili.adapter.PartitionAdapter;
import com.hejian.bilibili.adapter.PartitionListAdapter;
import com.hejian.bilibili.bean.PartitionBean;
import com.hejian.bilibili.utils.Utils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 何健 on 2017/3/21.
 */

public class PartitionFragment extends BaseFragment {


    private GridView gvPartition;
    @InjectView(R.id.lv_partition)
    ListView lvPartition;
    private int[] images = {R.drawable.ic_category_t13, R.drawable.ic_category_t13,
            R.drawable.ic_category_t1, R.drawable.ic_category_t3,
            R.drawable.ic_category_t129, R.drawable.ic_category_t4,
            R.drawable.ic_category_t36, R.drawable.ic_category_t160,
            R.drawable.ic_category_t119, R.drawable.ic_category_t155,
            R.drawable.ic_category_t165, R.drawable.ic_category_t5,
            R.drawable.ic_category_t23, R.drawable.ic_category_t11,
            R.drawable.ic_category_game_center};

    private String[] names = {"直播", "番剧", "动画", "音乐", "舞蹈", "游戏", "科技", "生活", "鬼畜"
            , "时尚", "广告", "娱乐", "电影", "电视剧", "游戏中心"};

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.partition_fragment, null);
        View view1 = View.inflate(mContext, R.layout.partition_fragment1, null);
        gvPartition = (GridView) view1.findViewById(R.id.gv_partition);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        PartitionAdapter adapter = new PartitionAdapter(images, names, mContext);
        gvPartition.setAdapter(adapter);
        gvPartition.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:
                        Intent intent = new Intent(mContext, RecommendLiveActivity.class);
                        mContext.startActivity(intent);
                        break;
                }
            }
        });

        getDataFromNet();


    }

    private void getDataFromNet() {
        OkHttpUtils.get()
                .url(Utils.PARTITION_DATA)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //Log.e("TAG", "数据解析成功"+response);
                        processData(response);
                    }
                });
    }

    private void processData(String json) {
        PartitionBean partitionBean = JSON.parseObject(json, PartitionBean.class);

        PartitionListAdapter adapter = new PartitionListAdapter(partitionBean.getData(), mContext);
        if (lvPartition != null && adapter!=null) {

            lvPartition.setAdapter(adapter);

            lvPartition.addHeaderView(gvPartition);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
