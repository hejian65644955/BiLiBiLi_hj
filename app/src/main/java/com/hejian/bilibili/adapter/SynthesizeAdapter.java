package com.hejian.bilibili.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hejian.bilibili.R;
import com.hejian.bilibili.bean.SynthesizeBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 何健 on 2017/3/23.
 */

public class SynthesizeAdapter extends RecyclerView.Adapter<SynthesizeAdapter.ViewHolder> {

    private final List<SynthesizeBean.DataBean> datas;
    private final Context mContext;


    public SynthesizeAdapter(List<SynthesizeBean.DataBean> data, Context mContext) {
        this.datas = data;
        this.mContext = mContext;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.adapter_synthesize, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SynthesizeBean.DataBean dataBean = datas.get(position);
        Glide.with(mContext)
                .load(dataBean.getCover())
                .into(holder.ivSynthesize);
        holder.tvTitle.setText(dataBean.getTitle());
        holder.tvTitle1.setText(dataBean.getName());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_synthesize)
        ImageView ivSynthesize;
        @InjectView(R.id.tv_title)
        TextView tvTitle;
        @InjectView(R.id.tv_title1)
        TextView tvTitle1;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemClickListener!=null){
                        itemClickListener.onItemClickListener(v,getLayoutPosition(),datas);
                    }
                }
            });
        }
    }

    //回调点击事件的监听
    private OnItemClickListener itemClickListener;

    /**
     * 点击item的监听
     */
    public interface OnItemClickListener {
        void onItemClickListener(View view, int position, List<SynthesizeBean.DataBean> datas);
    }

    /**
     * 设置item的监听
     * @param l
     */
    public void setOnItemClickListener(OnItemClickListener l) {
        this.itemClickListener = l;
    }


}
