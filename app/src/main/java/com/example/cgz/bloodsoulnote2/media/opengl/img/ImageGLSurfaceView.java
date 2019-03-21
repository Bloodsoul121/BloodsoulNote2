package com.example.cgz.bloodsoulnote2.media.opengl.img;

import android.content.Context;
import android.opengl.GLSurfaceView;

import java.io.IOException;

public class ImageGLSurfaceView extends GLSurfaceView {

    private ImageRenderer mRenderer;

    public ImageGLSurfaceView(Context context) throws IOException {
        super(context);

        setEGLContextClientVersion(2);
        mRenderer = new ImageRenderer(context);
        setRenderer(mRenderer);
    }

}
