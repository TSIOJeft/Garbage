package com.fan.garbage.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.fan.garbage.R;
import com.fan.garbage.data.ImgLoad;
import com.google.android.material.button.MaterialButton;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        initView();
    }

    public void initView() {
        ImageView photo1 = findViewById(R.id.photo1);
        ImageView photo2 = findViewById(R.id.photo2);
        ImageView photo3 = findViewById(R.id.photo3);
        Glide.with(this)
                .load("http://q1.qlogo.cn/g?b=qq&nk=1971214855&s=5")
                .apply(RequestOptions.circleCropTransform())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(photo1);
        Glide.with(this)
                .load("http://q1.qlogo.cn/g?b=qq&nk=552310594&s=5")
                .apply(RequestOptions.circleCropTransform())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(photo2);
        Glide.with(this)
                .load("http://q1.qlogo.cn/g?b=qq&nk=840430119&s=5")
                .apply(RequestOptions.circleCropTransform())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(photo3);
        MaterialButton chat1 = findViewById(R.id.chat1);
        chat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=1971214855&version=1")));
            }
        });
        MaterialButton chat2 = findViewById(R.id.chat2);
        chat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=552310594&version=1")));
            }
        });
        MaterialButton chat3 = findViewById(R.id.chat3);
        chat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=840430119&version=1")));
            }
        });
//        MaterialButton wechat = findViewById(R.id.wechat);
//        wechat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("wxp://f2f0b7TPdmrLplBhSpLqCGj-VlzeW1Ny1z4i\n")));
//            }
//        });
        MaterialButton alipay = findViewById(R.id.alipay);
        alipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://qr.alipay.com/tsx04229jk8mp25hxaecz6f\n")));
            }
        });
    }
}
