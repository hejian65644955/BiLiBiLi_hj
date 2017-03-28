package com.hejian.bilibili.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.anye.greendao.gen.UserDao;
import com.bumptech.glide.Glide;
import com.hejian.bilibili.MyApplication;
import com.hejian.bilibili.R;
import com.hejian.bilibili.bean.GoodsBean;
import com.hejian.bilibili.bean.User;
import com.hejian.bilibili.view.AddSubView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 何健 on 2017/3/28.
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.Viewholder> {

    private final Context mContext;
    private final TextView tvShopcartTotal;
    private final CheckBox checkboxAll;
    private final List<GoodsBean> datas;
    private final UserDao userDao;


    public ShoppingCartAdapter(Context context, List<GoodsBean> list, TextView tvShopcartTotal, CheckBox checkboxAll) {
        this.mContext = context;
        this.datas = list;
        this.tvShopcartTotal = tvShopcartTotal;
        this.checkboxAll = checkboxAll;
        showTotalPrice();
        userDao = MyApplication.getInstances().getDaoSession().getUserDao();

    }

    public void showTotalPrice() {
        //显示总价格
        tvShopcartTotal.setText("合计：" + getTotalPrice());
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                if (goodsBean.isChecked()) {
                    totalPrice += (i * 100 + 10)*goodsBean.getNumber();
                }
            }

        }
        return totalPrice;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Viewholder(View.inflate(mContext, R.layout.item_shop_cart, null));
    }

    @Override
    public void onBindViewHolder(Viewholder holder, final int position) {
        final GoodsBean goodsBean = datas.get(position);
        Glide.with(mContext).load(goodsBean.getCover()).into(holder.ivGov);
        //设置名称

        holder.tvDescGov.setText(goodsBean.getName());
        //设置价格
        holder.tvPriceGov.setText("￥" + (position*100+10));
        holder.cbGov.setChecked(goodsBean.isChecked());


        //设置数量
        holder.addSubView.setValue(goodsBean.getNumber());

        holder.addSubView.setMinValue(1);
        //设置库存-来自服务器-
        holder.addSubView.setMaxValue(100);

        holder.addSubView.setOnNumberChangerListener(new AddSubView.OnNumberChangerListener() {
            @Override
            public void onNumberChanger(int value) {
                //1.回调数量
                goodsBean.setNumber(value);

                //CartStorage.getInstance(mContext).updateData(goodsBean);

                List<User> users = userDao.loadAll();
                for(int i=0;i<users.size();i++){
                    if(users.get(i).getTId()==goodsBean.getTid()){
                        users.get(i).setNum(goodsBean.getNumber());
                    }
                }
                showTotalPrice();

            }
        });


    }

    //校验是否全选
    public void checkAll() {
        if (datas.size() > 0 && datas != null) {
            int number = 0;
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                if (!goodsBean.isChecked()) {
                    //只要有一个不勾选
                    checkboxAll.setChecked(false);
                } else {
                    number++;
                }
            }
            if (datas.size() == number) {
                checkboxAll.setChecked(true);

            }
        } else {
            checkboxAll.setChecked(false);
        }
    }



    public void check_none(boolean isChecked) {
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                //设置是否勾选状态
                goodsBean.setChecked(isChecked);
                checkboxAll.setChecked(isChecked);
                //更新视图
                notifyItemChanged(i);
            }

        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {
        @InjectView(R.id.cb_gov)
        CheckBox cbGov;
        @InjectView(R.id.iv_gov)
        ImageView ivGov;
        @InjectView(R.id.tv_desc_gov)
        TextView tvDescGov;
        @InjectView(R.id.tv_price_gov)
        TextView tvPriceGov;
        @InjectView(R.id.addSubView)
        AddSubView addSubView;

        public Viewholder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClickListener(v, getLayoutPosition());
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
        public void onItemClickListener(View view, int position);
    }

    /**
     * 设置item的监听
     *
     * @param l
     */
    public void setOnItemClickListener(OnItemClickListener l) {
        this.itemClickListener = l;
    }
}
