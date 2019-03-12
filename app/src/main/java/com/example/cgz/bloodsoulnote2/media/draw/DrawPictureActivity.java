package com.example.cgz.bloodsoulnote2.media.draw;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.widget.ImageView;

import com.example.cgz.bloodsoulnote2.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawPictureActivity extends AppCompatActivity {

    @BindView(R.id.img)
    ImageView mImg;
    @BindView(R.id.img_custom)
    CustomImgView mImgCustom;
    @BindView(R.id.surface_view)
    CustomSurfaceView mSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_picture);
        ButterKnife.bind(this);

        initImg();
        initSurfaceView();
    }

    private void initImg() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.d4);
        mImg.setImageBitmap(bitmap);
    }

    private void initSurfaceView() {

    }

}
