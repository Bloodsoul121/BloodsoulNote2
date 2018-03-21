package com.example.cgz.bloodsoulnote2.photoframe.glide.group;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;
import com.example.cgz.bloodsoulnote2.cache.disklrucache.photowall.Images;

import java.util.ArrayList;
import java.util.List;

public class GlideImageGroupActivity extends BaseActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_image_group);

        mImageView = (ImageView) findViewById(R.id.iv);

        List<String> iconList = new ArrayList<>();
        iconList.add(Images.imageThumbUrls[0]);
        iconList.add(Images.imageThumbUrls[1]);
        iconList.add(Images.imageThumbUrls[2]);
        Glide.with(this).load(iconList).into(mImageView);
    }
}
