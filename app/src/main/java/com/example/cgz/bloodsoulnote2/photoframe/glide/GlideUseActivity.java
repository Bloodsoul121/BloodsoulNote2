package com.example.cgz.bloodsoulnote2.photoframe.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;
import com.example.cgz.bloodsoulnote2.cache.disklrucache.photowall.Images;

import java.util.concurrent.ExecutionException;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class GlideUseActivity extends BaseActivity {

    private ImageView mImageView;

//    private String url = Images.imageThumbUrls[12];
    private String url = "http://ww3.sinaimg.cn/large/610dc034jw1fasakfvqe1j20u00mhgn2.jpg";
    private String url2 = Images.imageThumbUrls[30];
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_use);

        mContext = this;
        mImageView = (ImageView) findViewById(R.id.iv);
    }

    public void clickBtn1(View view) {
        GlideApp.with(this)
                .load(url)
//                .thumbnail(0.1f)
                .into(mImageView);
    }

    public void clickBtn2(View view) {
        Glide.get(mContext).clearMemory();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(mContext).clearDiskCache();
            }
        }).start();

    }

    public void clickBtn3(View view) {
        Glide.with(this)
                .load(url)
                .apply(RequestOptions.bitmapTransform(new CircleCrop())) // 有许多, 查询库
                .into(mImageView);
    }

    public void clickBtn4(View view) {
        Glide
//      .with(context) // could be an issue!
                .with(getApplicationContext()) // safer!
                .asBitmap() //强制返回一个Bitmap对象
                .load(url)
                .into(mSimpleTarget);

        // 该方式只能在子线程中获得
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Bitmap bitmap = Glide.with(GlideUseActivity.this).asBitmap().load(url).into(250, 250).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private SimpleTarget mSimpleTarget = new SimpleTarget<Bitmap>() {
        @Override
        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {

        }
    };

    public void clickBtn5(View view) {
        GlideApp.with(this)
                .asBitmap()
                .load(url)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(100)))
                .into(mImageView);
    }

    public void clickBtn6(View view) {
        GlideApp.with(this)
                .asBitmap()
                .load(url)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(mImageView);
    }
}
