package com.example.cgz.bloodsoulnote2.photoframe.picasso;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

import com.squareup.picasso.Transformation;

/**
 * Created by cgz on 18-3-19.
 */

public class GrayTransformation implements Transformation{

    @Override
    public Bitmap transform(Bitmap source) {
        int width, height;
        height = source.getHeight();
        width = source.getWidth();

        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(source, 0, 0, paint);

        if(source!=null && source!=bmpGrayscale){
            source.recycle();
        }
        return bmpGrayscale;
    }

    @Override
    public String key() {
        return "gray";
    }

}
