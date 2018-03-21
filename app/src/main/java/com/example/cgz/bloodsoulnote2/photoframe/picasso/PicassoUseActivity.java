package com.example.cgz.bloodsoulnote2.photoframe.picasso;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;
import com.example.cgz.bloodsoulnote2.cache.disklrucache.photowall.Images;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class PicassoUseActivity extends BaseActivity {

    private ImageView mImageView;

    private String url = Images.imageThumbUrls[8];
    private String url2 = Images.imageThumbUrls[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso_use);

        mImageView = (ImageView) findViewById(R.id.iv);
    }

    public void clickBtn1(View view) {
        Picasso.with(this).load(url).placeholder(R.drawable.d).into(mImageView);
    }

    public void clickBtn2(View view) {
        Picasso.with(this)
                .load(url)
                .placeholder(R.drawable.d1)
                .error(R.drawable.d3)
                .resize(200, 200)
                .centerCrop()
                .into(mImageView);
    }

    public void clickBtn3(View view) {
        Picasso.with(this)
                .load(url)
                .placeholder(R.drawable.d1)
                .resize(200, 200)
                .centerCrop()
                .rotate(180)
                .into(mImageView);
    }

    public void clickBtn4(View view) {
        Picasso.with(this)
                .load(url2)
                .into(mImageView);
    }

    public void clickBtn5(View view) {
        Picasso.with(this)
                .load(url2)
                .transform(new BlurTransformation(this))
                .into(mImageView);
    }

    public void clickBtn6(View view) {
        Picasso.with(this)
                .load(url2)
                .transform(new GrayTransformation())//度灰处理
                .transform(new BlurTransformation(this))//高斯模糊
                .into(mImageView);
    }

    public void clickBtn7(View view) {
        Picasso.with(this)
                .setIndicatorsEnabled(true);//显示指示器
        Picasso.with(this)
                .setLoggingEnabled(true);//开启日志打印
        Picasso.with(this)
                .load(url2)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(mImageView);
    }

    public void clickBtn8(View view) {
        Picasso.with(this)
                .setIndicatorsEnabled(true);//显示指示器
        Picasso.with(this)
                .setLoggingEnabled(true);//开启日志打印
        Picasso.with(this)
                .load(url2)
                .memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE)//跳过内存缓存
                .networkPolicy(NetworkPolicy.NO_CACHE)//跳过磁盘缓存
                .into(mImageView);
    }
}
