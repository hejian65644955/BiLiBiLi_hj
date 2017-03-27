package com.hejian.bilibili.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.hejian.bilibili.R;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class QRCodeActivity extends AppCompatActivity {

    @InjectView(R.id.iv_qr_image)
    ImageView ivQrImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        ButterKnife.inject(this);

        final String url_qr = getIntent().getStringExtra("url_qr");
        //final String contentString = Constants.BASE_URL_IMAGE+url_qr;
        if (!url_qr.equals("")) {
            //根据字符串生成二维码图片并显示在界面上，第二个参数为图片的大小（350*350）
            Glide.with(this)
                    .load(url_qr).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    Bitmap qrCodeBitmap = EncodingUtils.createQRCode(url_qr, 350, 350,
                            resource);
                    ivQrImage.setImageBitmap(qrCodeBitmap);
                }
            });


        } else {
            Toast.makeText(this, "Text can not be empty", Toast.LENGTH_SHORT).show();
        }



    }
}
