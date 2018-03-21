package com.example.cgz.bloodsoulnote2.photoframe.glide.group;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgz on 18-3-20.
 */

public class GroupAvatarFetcher implements DataFetcher<InputStream> {

    private final Context context;

    private final List<String> avatarList;

    private final int width, height;

    private InputStream resultStream;

    public GroupAvatarFetcher(Context context, @NonNull List<String> avatarList, int width, int height) {
        this.context = context;
        this.avatarList = new ArrayList<>();
        this.avatarList.addAll(avatarList);
        this.width = width;
        this.height = height;
    }

    @Override
    public InputStream loadData(Priority priority) throws Exception {
        List<Bitmap> bitmaps = new ArrayList<>();
        for (String string : avatarList) {
            bitmaps.add(Glide.with(context).load(string).asBitmap().into(width, height).get());
        }

        if (bitmaps.size() == 0) {
            return null;
        }

        int dimension = width;

        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        int count = Math.min(bitmaps.size(), GroupAvatarJoinLayout.max());
        float[] size = GroupAvatarJoinLayout.size(count);
        // 旋转角度
        float[] rotation = GroupAvatarJoinLayout.rotation(count);

        if (null == size || null == rotation) {
            return null;
        }

        // paint
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        Matrix matrixJoin = new Matrix();
        // scale as join size
        matrixJoin.postScale(size[0], size[0]);

        canvas.save();
        canvas.drawColor(Color.TRANSPARENT);

        for (int index = 0; index < bitmaps.size(); index++) {
            Bitmap bitmap = bitmaps.get(index);
            //如何该bitmap被回收了,则跳过
            if (bitmap == null || bitmap.isRecycled()) {
                continue;
            }
            // MATRIX
            Matrix matrix = new Matrix();
            // scale as destination
            matrix.postScale((float) dimension / bitmap.getWidth(),
                    (float) dimension / bitmap.getHeight());

            canvas.save();

            matrix.postConcat(matrixJoin);

            float[] offset = GroupAvatarJoinLayout.offset(count, index, dimension, size);
            canvas.translate(offset[0], offset[1]);

            // 缩放
            Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), matrix, true);
            // 裁剪
            Bitmap bitmapOk = GroupAvatarBitmaps.createMaskBitmap(newBitmap, newBitmap.getWidth(), newBitmap.getHeight(), (int) rotation[index], 0.15f);

            canvas.drawBitmap(bitmapOk, 0, 0, paint);
            canvas.restore();
        }
        return bitmap2InputStream(output);
    }

    private InputStream bitmap2InputStream(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        resultStream = new ByteArrayInputStream(baos.toByteArray());
        return resultStream;
    }

    @Override
    public void cleanup() {
        if (resultStream != null) {
            try {
                resultStream.close();
            } catch (IOException e) {
                // Ignore
            }
        }
    }

    @Override
    public String getId() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : avatarList) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }

    @Override
    public void cancel() {
        // Do nothing.
    }
}
