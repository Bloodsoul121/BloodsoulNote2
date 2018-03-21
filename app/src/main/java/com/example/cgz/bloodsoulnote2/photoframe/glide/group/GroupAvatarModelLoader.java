package com.example.cgz.bloodsoulnote2.photoframe.glide.group;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;

import java.io.InputStream;
import java.util.List;

/**
 * Created by cgz on 18-3-20.
 */

public class GroupAvatarModelLoader implements ModelLoader<List<String>, InputStream> {

    private final Context context;

    private GroupAvatarModelLoader(Context context) {
        this.context = context;
        Log.d("GroupAvatarModelLoader", "GroupAvatarModelLoader init success.");
    }

    @Override
    public DataFetcher<InputStream> getResourceFetcher(List<String> model, int width, int height) {
        return new GroupAvatarFetcher(this.context, model, width, height);
    }

    public static class Factory implements ModelLoaderFactory<List<String>, InputStream> {

        @Override
        public ModelLoader<List<String>, InputStream> build(Context context, GenericLoaderFactory factories) {
            return new GroupAvatarModelLoader(context);
        }

        @Override
        public void teardown() {
            // Do nothing.
        }
    }
}