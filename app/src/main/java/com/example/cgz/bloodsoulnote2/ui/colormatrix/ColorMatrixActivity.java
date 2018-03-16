package com.example.cgz.bloodsoulnote2.ui.colormatrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;

public class ColorMatrixActivity extends BaseActivity {

    private SeekBar sb_red, sb_green, sb_blue, sb_alpha;
    private ImageView iv_show;
    private Bitmap afterBitmap;
    private Paint paint;
    private Canvas canvas;
    private Bitmap baseBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matrix);
        initView();
    }

    private void initView() {
        iv_show = (ImageView) findViewById(R.id.iv_color_show);
        sb_red = (SeekBar) findViewById(R.id.sb_red);
        sb_green = (SeekBar) findViewById(R.id.sb_green);
        sb_blue = (SeekBar) findViewById(R.id.sb_blue);
        sb_alpha = (SeekBar) findViewById(R.id.sb_alpha);

        sb_red.setOnSeekBarChangeListener(seekBarChange);
        sb_green.setOnSeekBarChangeListener(seekBarChange);
        sb_blue.setOnSeekBarChangeListener(seekBarChange);
        sb_alpha.setOnSeekBarChangeListener(seekBarChange);

        baseBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.abc_btn_radio_on2);
        // 1.获取一个与baseBitmap大小一致的可编辑的空图片
        afterBitmap = Bitmap.createBitmap(baseBitmap.getWidth(),
                baseBitmap.getHeight(), baseBitmap.getConfig());
        // 2.使用Bitmap对象创建画布Canvas, 然后创建画笔Paint。
        canvas = new Canvas(afterBitmap);
        paint = new Paint();
    }

    private SeekBar.OnSeekBarChangeListener seekBarChange = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if(seekBar.getId() == R.id.sb_alpha){
                iv_show.getDrawable().setAlpha(sb_alpha.getProgress());

            }else{
                float progressR = sb_red.getProgress();
                float progressG = sb_green.getProgress();
                float progressB = sb_blue.getProgress();
                // 根据SeekBar定义RGBA的矩阵, 通过修改矩阵第五列颜色的偏移量改变图片的颜色
                float[] src = new float[]{
                        1, 0, 0, 0, progressR,
                        0, 1, 0, 0, progressG,
                        0, 0, 1, 0, progressB,
                        0, 0, 0, 1, 0};

                // 3.定义ColorMatrix，并指定RGBA矩阵
                ColorMatrix colorMatrix = new ColorMatrix();
                colorMatrix.set(src);
                // 4.使用ColorMatrix创建一个ColorMatrixColorFilter对象, 作为画笔的滤镜, 设置Paint的颜色
                paint.setColorFilter(new ColorMatrixColorFilter(src));
                // 5.通过指定了RGBA矩阵的Paint把原图画到空白图片上
                canvas.drawBitmap(baseBitmap, new Matrix(), paint);
                iv_show.setImageBitmap(afterBitmap);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
        }
    };
}
