package com.hejian.bilibili.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hejian.bilibili.R;
import com.hejian.bilibili.bean.TopicCenterBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 何健 on 2017/3/24.
 */

public class TopicCenterAdapter extends RecyclerView.Adapter<TopicCenterAdapter.ViewHolder> {

    private final List<TopicCenterBean.ListBean> datas;
    private final Context mContext;


    public TopicCenterAdapter(List<TopicCenterBean.ListBean> list, Context context) {
        this.datas = list;
        this.mContext = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_toptic, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(datas.get(position));

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_topic_image)
        ImageView ivTopicImage;
        @InjectView(R.id.tv_topic1)
        TextView tvTopic1;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }

        public void setData(TopicCenterBean.ListBean datas) {
            Glide.with(mContext)
                    .load(datas.getCover())
                    .into(ivTopicImage);
            tvTopic1.setText(datas.getTitle());

        }
    }
}
