package com.hejian.bilibili.fragment;

import android.view.View;
import android.widget.ImageView;

import com.hejian.bilibili.R;
import com.hejian.bilibili.utils.Utils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 何健 on 2017/3/21.
 */

public class ToThemFragment extends BaseFragment {


    @InjectView(R.id.fl_image1)
    ImageView flImage1;
    @InjectView(R.id.fl_image2)
    ImageView flImage2;
    @InjectView(R.id.fl_image3)
    ImageView flImage3;
    @InjectView(R.id.iv_bigimage)
    ImageView ivBigimage;
    @InjectView(R.id.fl_image11)
    ImageView flImage11;
    @InjectView(R.id.fl_image22)
    ImageView flImage22;
    @InjectView(R.id.fl_image33)
    ImageView flImage33;
    @InjectView(R.id.iv_bigimage1)
    ImageView ivBigimage1;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.tothem_fragment, null);
        //ButterKnife.inject(this,view);
        return view;
    }

    @Override
    public void initData() {
        getImageFromNet();

    }

    private void getImageFromNet() {
        OkHttpUtils.get()
                .url(Utils.TOTHEM_IMAGE1)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                            processImages(response);
                    }
                });
    }

    private void processImages(String json) {
   /*     ToThemImageBean toThemImageBean = JSON.parseObject(json, ToThemImageBean.class);
        if(toThemImageBean!=null){
            Glide.with(mContext).load(toThemImageBean.getResult().get(0).getCover()).into(flImage1);
            Glide.with(mContext).load(toThemImageBean.getResult().get(1).getCover()).into(flImage2);
            Glide.with(mContext).load(toThemImageBean.getResult().get(2).getCover()).into(flImage3);
            Glide.with(mContext).load(toThemImageBean.getResult().get(3).getCover()).into(flImage11);
            Glide.with(mContext).load(toThemImageBean.getResult().get(4).getCover()).into(flImage22);
            Glide.with(mContext).load(toThemImageBean.getResult().get(5).getCover()).into(flImage33);

        }*/
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
