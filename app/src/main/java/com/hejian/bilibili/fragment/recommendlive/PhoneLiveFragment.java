package com.hejian.bilibili.fragment.recommendlive;

import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hejian.bilibili.R;
import com.hejian.bilibili.fragment.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by 何健 on 2017/3/24.
 * 手机直播页面
 */

public class PhoneLiveFragment extends BaseFragment {

    @InjectView(R.id.iv_phonelive_up)
    ImageView ivPhoneliveUp;
    @InjectView(R.id.iv_phonelive_down)
    ImageView ivPhoneliveDown;
    @InjectView(R.id.btn_recommend)
    Button btnRecommend;
    @InjectView(R.id.btn_most_hot)
    Button btnMostHot;
    @InjectView(R.id.btn_most_new)
    Button btnMostNew;
    @InjectView(R.id.btn_click)
    Button btnClick;
    @InjectView(R.id.fl_botton)
    FrameLayout flBotton;
    @InjectView(R.id.ll_botton)
    LinearLayout llBotton;
    @InjectView(R.id.tv_all)
    TextView tvAll;
    @InjectView(R.id.tv_trip)
    TextView tvTrip;
    @InjectView(R.id.foreign_stu)
    TextView foreignStu;
    @InjectView(R.id.tv_show)
    TextView tvShow;
    @InjectView(R.id.tv_photos)
    TextView tvPhotos;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.phonelive_fragment, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    @OnClick({R.id.tv_all, R.id.tv_trip, R.id.foreign_stu, R.id.tv_show, R.id.tv_photos, R.id.iv_phonelive_up, R.id.iv_phonelive_down, R.id.btn_recommend, R.id.btn_most_hot, R.id.btn_most_new, R.id.ll_botton, R.id.btn_click, R.id.fl_botton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_all:
                break;
            case R.id.tv_trip:
                break;
            case R.id.foreign_stu:
                break;
            case R.id.tv_show:
                break;
            case R.id.tv_photos:
                break;
            case R.id.iv_phonelive_up:
                llBotton.setVisibility(View.VISIBLE);
                flBotton.setVisibility(View.VISIBLE);
                ivPhoneliveDown.setVisibility(View.VISIBLE);
                ivPhoneliveUp.setVisibility(View.GONE);
                btnClick.setVisibility(View.VISIBLE);


                break;
            case R.id.iv_phonelive_down:
                llBotton.setVisibility(View.GONE);
                flBotton.setVisibility(View.GONE);
                ivPhoneliveDown.setVisibility(View.GONE);
                ivPhoneliveUp.setVisibility(View.VISIBLE);


                break;
            case R.id.btn_recommend:
                break;
            case R.id.btn_most_hot:
                break;
            case R.id.btn_most_new:
                break;
            case R.id.ll_botton:
                break;
            case R.id.btn_click:
                btnClick.setVisibility(View.GONE);
                break;
            case R.id.fl_botton:
                break;
        }
    }
}
