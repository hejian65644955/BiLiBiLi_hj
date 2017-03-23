package com.hejian.bilibili.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hejian.bilibili.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 何健 on 2017/3/22.
 */

public class PartitionAdapter extends BaseAdapter {

    private final int[] datas;
    private final Context mContext;
    private final String[] names;

    public PartitionAdapter(int[] images, String[] names, Context mContext) {
        this.datas = images;
        this.mContext = mContext;
        this.names=names;

    }

    @Override
    public int getCount() {
        return datas.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.adatper_partition, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.ivImages.setImageResource(datas[position]);
        viewHolder.tvImages.setText(names[position]);
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_images)
        ImageView ivImages;
        @InjectView(R.id.tv_images)
        TextView tvImages;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
