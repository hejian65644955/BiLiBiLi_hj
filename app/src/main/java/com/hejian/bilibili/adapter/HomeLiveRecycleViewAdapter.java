package com.hejian.bilibili.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.hejian.bilibili.R;
import com.hejian.bilibili.bean.LiveBodyBean;
import com.hejian.bilibili.bean.LiveTopBean;
import com.hejian.bilibili.utils.Utils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.transformer.BackgroundToForegroundTransformer;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 何健 on 2017/3/25.
 */

public class HomeLiveRecycleViewAdapter extends RecyclerView.Adapter {
    private final LiveTopBean.DataBean datas;
    private final Context mContext;

    private static final int BANNER = 0;
    private static final int RECOMMENDLIVE = 1;
    private static final int GRIDVIEWLIVE1 = 2;


    private int CURRENTTYPE = BANNER;

    private LayoutInflater inflater;
    private RecemmendLiveViewHolder recemmendLiveViewHolder;
    private LiveBodyBean liveBodyBean;

    public HomeLiveRecycleViewAdapter(LiveTopBean.DataBean data, Context mContext) {
        this.datas = data;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                CURRENTTYPE = BANNER;
                break;
            case RECOMMENDLIVE:
                CURRENTTYPE = RECOMMENDLIVE;
                break;
            case GRIDVIEWLIVE1:
                CURRENTTYPE =GRIDVIEWLIVE1;
                break;

        }
        return CURRENTTYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BANNER:
                return new BannerViewHolder(inflater.inflate(R.layout.banner_viewpager, null));
            case RECOMMENDLIVE:
                return new RecemmendLiveViewHolder(inflater.inflate(R.layout.recommendlive_adapter, null));
//            case GRIDVIEWLIVE1:
//                return new GridViewLive1ViewHolder(inflater.inflate(R.layout.gridviewlive1_adapter, null));
        }
        return null;
    }

    class GridViewLive1ViewHolder extends RecyclerView.ViewHolder{

        public GridViewLive1ViewHolder(View itemView) {
            super(itemView);
        }
    }

    class RecemmendLiveViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_recommend)
        ImageView ivRecommend;
        @InjectView(R.id.tv_recommend)
        TextView tvRecommend;
        @InjectView(R.id.iv_live_load)
        ImageView ivLiveLoad;
        @InjectView(R.id.tv_name1)
        TextView tvName1;
        @InjectView(R.id.tv_name2)
        TextView tvName2;
        @InjectView(R.id.tv_name3)
        TextView tvName3;
        @InjectView(R.id.tv_watch)
        TextView tvWatch;

        public RecemmendLiveViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData(List<LiveBodyBean.DataBean.RecommendDataBean.BannerDataBean> banner_data) {
            Glide.with(mContext).load(liveBodyBean.getData().getRecommend_data().getPartition().getSub_icon().getSrc()).into(ivRecommend);
            tvRecommend.setText(liveBodyBean.getData().getRecommend_data().getPartition().getName());
            Glide.with(mContext).load(banner_data.get(0).getCover().getSrc()).into(ivLiveLoad);
            tvName1.setText(banner_data.get(0).getArea());
            tvName2.setText(banner_data.get(0).getTitle());
            tvName3.setText(banner_data.get(0).getOwner().getName());
            tvWatch.setText(banner_data.get(0).getOnline() + "");
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        private Banner banner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.banner);
        }

        public void setData(List<LiveTopBean.DataBean.BannerBean> bannerData) {
            List<String> images = new ArrayList<>();
            for (int i = 0; i < bannerData.size(); i++) {
                images.add(bannerData.get(i).getImg());
            }
            banner.setImages(images)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            Glide.with(context)
                                    .load(path)
                                    .crossFade()
                                    .into(imageView);
                        }
                    }).start();
            banner.setBannerAnimation(BackgroundToForegroundTransformer.class);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case BANNER:
                BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
                bannerViewHolder.setData(datas.getBanner());
                break;
            case RECOMMENDLIVE:
                recemmendLiveViewHolder = (RecemmendLiveViewHolder) holder;
                getDataFromNet(Utils.LIVE_BODY);
                break;
//            case GRIDVIEWLIVE1:
//                GridViewLive1ViewHolder gridViewLive1ViewHolder = (GridViewLive1ViewHolder) holder;
//                break;
        }
    }

    private void getDataFromNet(String liveBody) {
        OkHttpUtils.get()
                .url(liveBody)
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
        liveBodyBean = JSON.parseObject(json, LiveBodyBean.class);
        recemmendLiveViewHolder.setData(liveBodyBean.getData().getRecommend_data().getBanner_data());

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
