package com.hejian.bilibili.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hejian.bilibili.R;
import com.hejian.bilibili.bean.DrawBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 何健 on 2017/3/22.
 */

public class MyDrawGridViewAdapter extends BaseAdapter {

    private final List<DrawBean.DataBean> datas;
    private final Context mContext;

    public MyDrawGridViewAdapter(List<DrawBean.DataBean> data, Context mContext) {
        this.datas = data;
        this.mContext = mContext;

    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_draw, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //设置数据
        Glide.with(mContext)
                .load(datas.get(position).getCover().getSrc())
                .into(viewHolder.ivDraw);
       viewHolder.tvDraw.setText(datas.get(position).getTitle());
        viewHolder.tvName.setText(datas.get(position).getOwner().getName());
        viewHolder.tvOnline.setText(datas.get(position).getOnline()+"");
        return convertView;
    }


     class ViewHolder {
        @InjectView(R.id.iv_draw)
        ImageView ivDraw;
        @InjectView(R.id.tv_draw)
        TextView tvDraw;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_online)
        TextView tvOnline;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

}
