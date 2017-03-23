package com.hejian.bilibili.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hejian.bilibili.R;
import com.hejian.bilibili.bean.PartitionBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 何健 on 2017/3/23.
 */

public class PartitionListAdapter extends BaseAdapter {
    private final List<PartitionBean.DataBean> datas;
    private final Context mContext;

    private int[] images = {R.drawable.ic_category_t13, R.drawable.ic_category_t13,
            R.drawable.ic_category_t1, R.drawable.ic_category_t3,
            R.drawable.ic_category_t129, R.drawable.ic_category_t4,
            R.drawable.ic_category_t36, R.drawable.ic_category_t160,
            R.drawable.ic_category_t119, R.drawable.ic_category_t155,
            R.drawable.ic_category_t165, R.drawable.ic_category_t5,
            R.drawable.ic_category_t23, R.drawable.ic_category_t11,
            R.drawable.ic_category_game_center, R.drawable.ic_category_t119, R.drawable.ic_category_t155,
            R.drawable.ic_category_t165, R.drawable.ic_category_t5,
            R.drawable.ic_category_t23, R.drawable.ic_category_t11,};

    public PartitionListAdapter(List<PartitionBean.DataBean> data, Context mContext) {
        this.datas = data;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return datas.size();
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.adapter_partition, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.ivDrawPt.setImageResource(images[position]);
        viewHolder.tvDrawPt.setText(datas.get(position).getTitle());
        if (datas.get(position).getType().equals("topic")) {
            viewHolder.llOne.setVisibility(View.VISIBLE);
            viewHolder.llMore.setVisibility(View.GONE);
            Glide.with(mContext)
                    .load(datas.get(position).getBody().get(0).getCover())
                    .into(viewHolder.ivOnemage);
        } else {

            viewHolder.llOne.setVisibility(View.GONE);
            viewHolder.llMore.setVisibility(View.VISIBLE);


            Glide.with(mContext)
                    .load(datas.get(position).getBody().get(0).getCover())
                    .into(viewHolder.ivPartition1);
            viewHolder.tvName1.setText(datas.get(position).getBody().get(0).getTitle());
            viewHolder.tvSee1.setText(datas.get(position).getBody().get(0).getPlay() + "");
            viewHolder.tvCollect1.setText(datas.get(position).getBody().get(0).getDanmaku() + "");

            Glide.with(mContext)
                    .load(datas.get(position).getBody().get(1).getCover())
                    .into(viewHolder.ivPartition2);
            viewHolder.tvName2.setText(datas.get(position).getBody().get(1).getTitle());
            viewHolder.tvSee2.setText(datas.get(position).getBody().get(1).getPlay() + "");
            viewHolder.tvCollect2.setText(datas.get(position).getBody().get(1).getDanmaku() + "");

            Glide.with(mContext)
                    .load(datas.get(position).getBody().get(2).getCover())
                    .into(viewHolder.ivPartition3);
            viewHolder.tvName3.setText(datas.get(position).getBody().get(2).getTitle());
            viewHolder.tvSee3.setText(datas.get(position).getBody().get(2).getPlay() + "");
            viewHolder.tvCollect3.setText(datas.get(position).getBody().get(2).getDanmaku() + "");

            if(datas.get(position).getBody().size()<4){
                viewHolder.llFour.setVisibility(View.GONE);

            }else{
                Glide.with(mContext)
                        .load(datas.get(position).getBody().get(3).getCover())
                        .into(viewHolder.ivPartition4);
                viewHolder.tvName4.setText(datas.get(position).getBody().get(3).getTitle());
                viewHolder.tvSee4.setText(datas.get(position).getBody().get(3).getPlay() + "");
                viewHolder.tvCollect4.setText(datas.get(position).getBody().get(3).getDanmaku() + "");
                viewHolder.llFour.setVisibility(View.VISIBLE);
            }
        }
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_draw_pt)
        ImageView ivDrawPt;
        @InjectView(R.id.tv_draw_pt)
        TextView tvDrawPt;
        @InjectView(R.id.btn_lookin)
        Button btnLookin;
        @InjectView(R.id.iv_partition1)
        ImageView ivPartition1;
        @InjectView(R.id.tv_name1)
        TextView tvName1;
        @InjectView(R.id.tv_see1)
        TextView tvSee1;
        @InjectView(R.id.tv_collect1)
        TextView tvCollect1;
        @InjectView(R.id.iv_partition2)
        ImageView ivPartition2;
        @InjectView(R.id.tv_name2)
        TextView tvName2;
        @InjectView(R.id.tv_see2)
        TextView tvSee2;
        @InjectView(R.id.tv_collect2)
        TextView tvCollect2;
        @InjectView(R.id.iv_partition3)
        ImageView ivPartition3;
        @InjectView(R.id.tv_name3)
        TextView tvName3;
        @InjectView(R.id.tv_see3)
        TextView tvSee3;
        @InjectView(R.id.tv_collect3)
        TextView tvCollect3;
        @InjectView(R.id.iv_partition4)
        ImageView ivPartition4;
        @InjectView(R.id.tv_name4)
        TextView tvName4;
        @InjectView(R.id.tv_see4)
        TextView tvSee4;
        @InjectView(R.id.tv_collect4)
        TextView tvCollect4;
        @InjectView(R.id.iv_refresh)
        ImageView ivRefresh;
        @InjectView(R.id.ll_more)
        LinearLayout llMore;
        @InjectView(R.id.iv_oneimage)
        ImageView ivOnemage;
        @InjectView(R.id.ll_one)
        LinearLayout llOne;
        @InjectView(R.id.ll_four)
        LinearLayout llFour;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

}
