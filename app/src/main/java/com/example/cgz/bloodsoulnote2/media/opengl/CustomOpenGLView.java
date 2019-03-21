package com.example.cgz.bloodsoulnote2.media.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class CustomOpenGLView extends GLSurfaceView{

    private final CustomGLRenderer mRenderer;

    public CustomOpenGLView(Context context) {
        super(context);

        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);

        mRenderer = new CustomGLRenderer();

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(mRenderer);
    }

    /*
     *  你可以通过设置GLSurfaceView.RENDERMODE_WHEN_DIRTY来让你的GLSurfaceView监听到数据变化的时候再去刷新，
     *  即修改GLSurfaceView的渲染模式。这个设置可以防止重绘GLSurfaceView，直到你调用了requestRender()，
     *  这个设置在默写层面上来说，对你的APP是更有好处的。
     */

}
