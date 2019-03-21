package com.example.cgz.bloodsoulnote2.media.opengl;


import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import com.example.cgz.bloodsoulnote2.media.opengl.draw.CameraTriangle;
import com.example.cgz.bloodsoulnote2.media.opengl.draw.Square;
import com.example.cgz.bloodsoulnote2.media.opengl.draw.Triangle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class CustomGLRenderer implements GLSurfaceView.Renderer {

    private static final String TAG = "OpenGL";
    private Triangle mTriangle;
    private Square mSquare;
    private CameraTriangle mCameraTriangle;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Log.i(TAG, "onSurfaceCreated");
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        mTriangle = new Triangle();
        mSquare = new Square();
        mCameraTriangle = new CameraTriangle();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Log.i(TAG, "onSurfaceChanged " + width + " " + height);
//        mSquare.onSurfaceChanged(width, height);
        mCameraTriangle.onSurfaceChanged(width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        Log.i(TAG, "onDrawFrame");
//        mTriangle.draw();
//        mSquare.draw();
        mCameraTriangle.draw();
    }

}
