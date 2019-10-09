package com.example.cgz.bloodsoulnote2.media.camera;

import android.Manifest;
import android.annotation.SuppressLint;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import com.example.cgz.bloodsoulnote2.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private static final String TAG = CameraActivity.class.getSimpleName();

    @BindView(R.id.surface_view)
    SurfaceView mSurfaceView;
//    @BindView(R.id.textureview)
//    TextureView mTextureView;

    private SurfaceHolder mHolder;
    private Camera mCamera;
    private boolean mIsBack = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
        requestPermissions();
    }

    @SuppressLint("CheckResult")
    public void requestPermissions() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CAMERA).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) {
                if (aBoolean) {
                    Toast.makeText(CameraActivity.this, "accept", Toast.LENGTH_SHORT).show();
                    init();
                } else {
                    Toast.makeText(CameraActivity.this, "deny", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void init() {
        mHolder = mSurfaceView.getHolder();
        mHolder.addCallback(this);
        openCamera(0);
    }

    private void openCamera(int i) {
        mCamera = Camera.open(i); // 0为后置 1为前置
        mCamera.setDisplayOrientation(90);
    }

    private void startPreview() {
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void releaseCamera() {
        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;
    }

    public void switchCamera(View view) {
        mIsBack = !mIsBack;
        if (mIsBack) {
            releaseCamera();
            openCamera(0);
            startPreview();
        } else {
            releaseCamera();
            openCamera(1);
            startPreview();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        startPreview();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        releaseCamera();
    }

    public void takePicture(View view) {
        mCamera.takePicture(null, null, mPicture);
    }

    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File pictureFile = new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), System.currentTimeMillis() + "_picture.jpg");
            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {
                Log.d(TAG, "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.d(TAG, "Error accessing file: " + e.getMessage());
            }

            startPreview();
        }
    };
}
