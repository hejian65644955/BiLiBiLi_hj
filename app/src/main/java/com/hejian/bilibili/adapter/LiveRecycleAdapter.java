package com.hejian.bilibili.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.hejian.bilibili.R;
import com.hejian.bilibili.bean.DrawBean;
import com.hejian.bilibili.bean.HomeBean;
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
 * Created by 何健 on 2017/3/21.
 */

public class LiveRecycleAdapter extends RecyclerView.Adapter {

    private static final int BANNER = 0;

    private static final int DRAW = 1;
    private final HomeBean datas;
    private final Context mContext;
    private int CurrentType = BANNER;
    private final LayoutInflater inflater;
    private DrawBean drawBean;
    private DrawViewHolder drawViewHolder;


    public LiveRecycleAdapter(HomeBean datas, Context mContext) {
        this.datas = datas;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                CurrentType = BANNER;
                break;
            case DRAW:
                CurrentType = DRAW;
                break;
        }
        return CurrentType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BANNER:
                return new BannerViewHolder(inflater.inflate(R.layout.banner_viewpager, null), mContext);
            case DRAW:
                return new DrawViewHolder(inflater.inflate(R.layout.draw_adapter, null), mContext);
        }
        return null;
    }

    class DrawViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.gv_draw)
        GridView gvDraw;
        private final Context mContext;

        public DrawViewHolder(View itemView, Context mContext) {
            super(itemView);
            ButterKnife.inject(this,itemView);
            this.mContext = mContext;
        }

        public void setData(List<DrawBean.DataBean> data) {
            MyDrawGridViewAdapter adapter = new MyDrawGridViewAdapter(data, mContext);
            gvDraw.setAdapter(adapter);

        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        private Context context;
        private Banner banner1;

        public BannerViewHolder(View itemView, Context mContext) {
            super(itemView);
            this.context = mContext;
            banner1 = (Banner) itemView.findViewById(R.id.banner);
        }

        public void setData(List<HomeBean.DataBean.BannerBean> banner) {
            //得到数据
            List<String> images = new ArrayList<>();
            //设置Banner的数据
            for (int i = 0; i < banner.size(); i++) {
                images.add(banner.get(i).getImg());
            }
            banner1.setImages(images)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            Glide.with(context)
                                    .load(path)
                                    .crossFade()
                                    .into(imageView);
                        }
                    }).start();
            banner1.setBannerAnimation(BackgroundToForegroundTransformer.class);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case BANNER:
                BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
                bannerViewHolder.setData(datas.getData().getBanner());
                break;

            case DRAW:
                drawViewHolder = (DrawViewHolder) holder;
                getDataFromNet(Utils.DRAW_DATA);


                break;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
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
                        Log.e("TAG", "chenggong!" + response);

                        processData(response);
                    }
                });
    }

    private void processData(String json) {
        drawBean = JSON.parseObject(json, DrawBean.class);
        drawViewHolder.setData(drawBean.getData());
    }
}
