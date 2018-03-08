package com.example.cgz.bloodsoulnote2.cache.disklrucache.photowall;

import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.GridView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoWallActivity extends BaseActivity {

    @BindView(R.id.gridview)
    GridView mGridview;

    private PhotoWallAdapter mAdapter;

    private int mImageThumbSize;
    private int mImageThumbSpacing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_wall);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        List<String> datas = Arrays.asList(Images.imageThumbUrls);
        mAdapter = new PhotoWallAdapter(this, datas, mGridview);
        mGridview.setAdapter(mAdapter);

        mImageThumbSize = getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size);
        mImageThumbSpacing = getResources().getDimensionPixelSize(R.dimen.image_thumbnail_spacing);

        mGridview.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                final int numColumns = (int) Math.floor(mGridview.getWidth() / (mImageThumbSize + mImageThumbSpacing));
                if (numColumns > 0) {
                    int columnWidth = (mGridview.getWidth() / numColumns) - mImageThumbSpacing;
                    mAdapter.setItemHeight(columnWidth);
                    mGridview.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAdapter.fluchCache();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cancelAllTasks();
    }
}
