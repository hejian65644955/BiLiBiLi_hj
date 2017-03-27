package com.hejian.bilibili.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
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
    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;
    private HomeBean homeBean;
    private boolean isLoadMore = false;


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.live_fragment, null);
        ButterKnife.inject(this, view);
        refresh.setMaterialRefreshListener(new MyMaterialRefreshListener());
        return view;
    }



    class MyMaterialRefreshListener extends MaterialRefreshListener {
        @Override
        public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
//            Toast.makeText(mContext, "下拉刷新", Toast.LENGTH_SHORT).show();
            isLoadMore = false;
            getDataFromNet(Utils.LIVE_DATA);
        }

        /**
         * 加载更多的回调
         *
         * @param materialRefreshLayout
         */
        @Override
        public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
            super.onRefreshLoadMore(materialRefreshLayout);
            //Toast.makeText(mContext, "加载更多", Toast.LENGTH_SHORT).show();
            isLoadMore = true;
            getDataFromNet(Utils.LIVE_DATA);

        }

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
                            if (!isLoadMore) {
                                //完成刷新

                                if(refresh!=null){
                                    refresh.finishRefresh();
                                    refresh.finishRefreshLoadMore();
                                }




                            } else {
                                //把上拉的隐藏

                                if(refresh!=null){
                                    refresh.finishRefresh();
                                    refresh.finishRefreshLoadMore();
                                }



                            }

                            processData(response);
                        }


                    });
        }

        private void processData(String json) {
            homeBean = JSON.parseObject(json, HomeBean.class);
            LiveRecycleAdapter adapter = new LiveRecycleAdapter(homeBean, mContext);
            if (recyclerviewLive == null && homeBean.getData().getBanner().size() > 0) {
                return;
            }
            recyclerviewLive.setAdapter(adapter);
            // recyclerviewLive.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
            GridLayoutManager manager = new GridLayoutManager(mContext, 1);
            recyclerviewLive.setLayoutManager(manager);
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
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
