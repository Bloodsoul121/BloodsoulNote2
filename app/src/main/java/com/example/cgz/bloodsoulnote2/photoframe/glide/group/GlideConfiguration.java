package com.example.cgz.bloodsoulnote2.photoframe.glide.group;

import android.content.Context;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;
import java.util.List;

/**
 * Created by cgz on 18-3-20.
 */

public class GlideConfiguration implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
//        ViewTarget.setTagId(R.id.glide_tag);
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        String downloadDirectoryPath = Environment.getDownloadCacheDirectory().getPath();
        builder.setDiskCache(new DiskLruCacheFactory(downloadDirectoryPath, 100 * 1024 * 1024));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        Class<List<String>> clazz = (Class) List.class;
        glide.register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
        glide.register(clazz, InputStream.class, new GroupAvatarModelLoader.Factory());
    }
}