package com.hejian.bilibili.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.anye.greendao.gen.UserDao;
import com.bumptech.glide.Glide;
import com.hejian.bilibili.MyApplication;
import com.hejian.bilibili.R;
import com.hejian.bilibili.bean.GoodsBean;
import com.hejian.bilibili.bean.SynthesizeBean;
import com.hejian.bilibili.bean.User;
import com.hejian.bilibili.utils.VirtualkeyboardHeight;
import com.hejian.bilibili.view.AddSubView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class GoodsInfoActivity extends AppCompatActivity {

    @InjectView(R.id.ib_good_info_back)
    ImageButton ibGoodInfoBack;
    @InjectView(R.id.ib_good_info_more)
    ImageButton ibGoodInfoMore;
    @InjectView(R.id.iv_good_info_image)
    ImageView ivGoodInfoImage;
    @InjectView(R.id.tv_good_info_name)
    TextView tvGoodInfoName;
    @InjectView(R.id.tv_good_info_desc)
    TextView tvGoodInfoDesc;
    @InjectView(R.id.tv_good_info_price)
    TextView tvGoodInfoPrice;
    @InjectView(R.id.tv_good_info_store)
    TextView tvGoodInfoStore;
    @InjectView(R.id.tv_good_info_style)
    TextView tvGoodInfoStyle;
    @InjectView(R.id.wb_good_info_more)
    WebView wbGoodInfoMore;
    @InjectView(R.id.progressbar)
    ProgressBar progressbar;
    @InjectView(R.id.tv_good_info_callcenter)
    TextView tvGoodInfoCallcenter;
    @InjectView(R.id.tv_good_info_collection)
    TextView tvGoodInfoCollection;
    @InjectView(R.id.tv_good_info_cart)
    TextView tvGoodInfoCart;
    @InjectView(R.id.btn_good_info_addcart)
    Button btnGoodInfoAddcart;
    @InjectView(R.id.ll_goods_root)
    LinearLayout llGoodsRoot;
    @InjectView(R.id.tv_more_share)
    TextView tvMoreShare;
    @InjectView(R.id.tv_more_search)
    TextView tvMoreSearch;
    @InjectView(R.id.tv_more_home)
    TextView tvMoreHome;
    @InjectView(R.id.btn_more)
    Button btnMore;
    @InjectView(R.id.activity_good_sinfo)
    LinearLayout activityGoodSinfo;
    @InjectView(R.id.ll_root)
    LinearLayout llRoot;
    private GoodsBean goodsBean;
    private boolean isExist;
    private int position;
    private UserDao userDao;
    private User mUser;
    private GoodsBean tempGoodsBean = new GoodsBean();
    ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_sinfo);
        ButterKnife.inject(this);
        userDao = MyApplication.getInstances().getDaoSession().getUserDao();
        getData();
    }

    private void getData() {
        goodsBean = new GoodsBean();
        SynthesizeBean.DataBean dataBean = (SynthesizeBean.DataBean) getIntent().getSerializableExtra("datas");
        if (dataBean != null) {
            goodsBean.setFace(dataBean.getFace());
            goodsBean.setName(dataBean.getName());
            goodsBean.setCover(dataBean.getCover());
            goodsBean.setTid(dataBean.getTid());
            position = getIntent().getIntExtra("position", -1);
            if (goodsBean != null && position != -1) {
                setData();
            }
        }
    }

    private void setData() {
        //设置图片
        Glide.with(this)
                .load(goodsBean.getCover())
                .into(ivGoodInfoImage);
        //设置名称和价格
        tvGoodInfoName.setText(goodsBean.getName());
        tvGoodInfoPrice.setText("￥" + (position * 100 + 10));
        //设置加载页面
        loadWeb("http://app.bilibili.com/");
    }

    private void loadWeb(String url) {
        WebSettings webSettings = wbGoodInfoMore.getSettings();
        //设置支持js
        webSettings.setJavaScriptEnabled(true);
        //设置添加缩放按钮
        webSettings.setBuiltInZoomControls(true);
        //设置双击单击
        webSettings.setUseWideViewPort(true);
        //设置WebViewClient,如果没有设置，调起系统的浏览器打开新的连接
        wbGoodInfoMore.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressbar.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                }
                return true;
            }
        });
        wbGoodInfoMore.loadUrl(url);
    }

    @OnClick({R.id.ib_good_info_back, R.id.ib_good_info_more, R.id.tv_good_info_callcenter, R.id.tv_good_info_collection, R.id.tv_good_info_cart, R.id.btn_good_info_addcart, R.id.tv_more_share, R.id.tv_more_home, R.id.btn_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_good_info_back:
                finish();
                break;
            case R.id.ib_good_info_more:
                if (llRoot.isShown()) {
                    llRoot.setVisibility(View.GONE);
                } else {
                    llRoot.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_good_info_callcenter:
                Intent intent = new Intent(GoodsInfoActivity.this, CallCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_good_info_collection:
                Toast.makeText(GoodsInfoActivity.this, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_good_info_cart:
                intent = new Intent(GoodsInfoActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_good_info_addcart:
                showPopwindow();
                break;
            case R.id.tv_more_share:
                Toast.makeText(GoodsInfoActivity.this, "分享====", Toast.LENGTH_SHORT).show();
                if (!TextUtils.isEmpty(goodsBean.getFace())) {
                    Intent intent1 = new Intent(this, QRCodeActivity.class);
                    intent1.putExtra("url_qr", goodsBean.getFace());
                    startActivity(intent1);
                }

                break;
            case R.id.tv_more_home:
                Toast.makeText(GoodsInfoActivity.this, "主页", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_more:
                llRoot.setVisibility(View.GONE);
                break;
        }
    }

    User oldUser = null;

    private void showPopwindow() {
        //从数据库查找
        List<User> users = userDao.loadAll();
        Log.e("TAG", "ccccccccccccccccccccccccccc" + users.size());
        isExist = false;
        for (int i = 0; i < users.size(); i++) {
            oldUser = users.get(i);
            if (oldUser.getTId() == goodsBean.getTid()) {
                isExist = true;

            }
        }
        tempGoodsBean = goodsBean;
        /*if (!isExist) {
            tempGoodsBean.setName(goodsBean.getName());
            tempGoodsBean.setCover(goodsBean.getCover());
            tempGoodsBean.setNumber(goodsBean.getNumber());
            tempGoodsBean.setTid(goodsBean.getTid());
        }*/


        // 1 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popupwindow_add_product, null);

        // 2下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        final PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        // 3 参数设置
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xFFFFFFFF);
        window.setBackgroundDrawable(dw);

        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);


        // 4 控件处理
        ImageView iv_goodinfo_photo = (ImageView) view.findViewById(R.id.iv_goodinfo_photo);
        TextView tv_goodinfo_name = (TextView) view.findViewById(R.id.tv_goodinfo_name);
        TextView tv_goodinfo_price = (TextView) view.findViewById(R.id.tv_goodinfo_price);
        AddSubView nas_goodinfo_num = (AddSubView) view.findViewById(R.id.nas_goodinfo_num);
        Button bt_goodinfo_cancel = (Button) view.findViewById(R.id.bt_goodinfo_cancel);
        Button bt_goodinfo_confim = (Button) view.findViewById(R.id.bt_goodinfo_confim);

        // 加载图片
        Glide.with(GoodsInfoActivity.this).load(goodsBean.getCover()).into(iv_goodinfo_photo);

        // 名称
        tv_goodinfo_name.setText(goodsBean.getName());
        // 显示价格
        tv_goodinfo_price.setText(position * 100 + 10 + "");

        // 设置最大值和当前值
        nas_goodinfo_num.setMaxValue(100);//库存100

        nas_goodinfo_num.setValue(goodsBean.getNumber());


        nas_goodinfo_num.setOnNumberChangerListener(new AddSubView.OnNumberChangerListener() {
            @Override
            public void onNumberChanger(int value) {
                tempGoodsBean.setNumber(value);
            }
        });

        bt_goodinfo_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
                if (!isExist && tempGoodsBean.getNumber() == 1) {
                    tempGoodsBean.setNumber(tempGoodsBean.getNumber() + 1);
                }
            }
        });

        bt_goodinfo_confim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
                //添加购物车
                //CartStorage.getInstance(GoodsInfoActivity.this).addData(goodsBean);
                //添加到数据库
                int number = tempGoodsBean.getNumber();
                String imageUrl = tempGoodsBean.getCover();
                String name = tempGoodsBean.getName();
                double price = position * 100 + 10;
                double tId = tempGoodsBean.getTid();
//                Log.e("TAG", "user1111111111111111111111112222222111111" + user.getName());
                if (isExist) {
                    oldUser.setNum(number);
                    oldUser.setName(name);
                    oldUser.setPrice(price);
                    oldUser.setTId(tId);
                    oldUser.setImageUrl(imageUrl);
                    userDao.update(oldUser);
                } else {
                    User user = new User(null, number, imageUrl, name, price, tId);
                    userDao.insert(user);
                }


                Log.e("TAG", "66:" + goodsBean.toString());
                Toast.makeText(GoodsInfoActivity.this, "添加购物车成功", Toast.LENGTH_SHORT).show();
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                window.dismiss();
            }
        });

        // 5 在底部显示
        window.showAtLocation(GoodsInfoActivity.this.findViewById(R.id.ll_goods_root),
                Gravity.BOTTOM, 0, VirtualkeyboardHeight.getBottomStatusHeight(GoodsInfoActivity.this));

    }
}

