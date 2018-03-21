package com.example.cgz.bloodsoulnote2.photoframe.glide.group;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

/**
 * Created by cgz on 18-3-20.
 */

public class GroupAvatarBitmaps {
    public static Bitmap createMaskBitmap(Bitmap newBitmap, int width, int height, int i, float v) {

        Bitmap creBitmap = Bitmap.createBitmap(width, height, android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(creBitmap);

        Paint paint = new Paint();
        float roundPx = i;
        RectF rectF = new RectF(0, 0, width - i, height - i);
        paint.setAntiAlias(true);

        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        canvas.drawBitmap(newBitmap, 0, 0, paint);
        if (!newBitmap.isRecycled())
            newBitmap.recycle();

        return creBitmap;
    }
}
