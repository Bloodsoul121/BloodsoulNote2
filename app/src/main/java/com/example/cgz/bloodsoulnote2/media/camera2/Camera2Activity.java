package com.example.cgz.bloodsoulnote2.media.camera2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.example.cgz.bloodsoulnote2.R;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *  参考官方demo https://github.com/googlesamples/android-Camera2Video
 */
public class Camera2Activity extends AppCompatActivity {

    private static final String TAG = "Camera2Activity";

    /**
     * Conversion from screen rotation to JPEG orientation.
     */
    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();

    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 90);
        ORIENTATIONS.append(Surface.ROTATION_90, 0);
        ORIENTATIONS.append(Surface.ROTATION_180, 270);
        ORIENTATIONS.append(Surface.ROTATION_270, 180);
    }

    /**
     * Max preview width that is guaranteed by Camera2 API
     */
    private static final int MAX_PREVIEW_WIDTH = 1920;

    /**
     * Max preview height that is guaranteed by Camera2 API
     */
    private static final int MAX_PREVIEW_HEIGHT = 1080;

    /**
     * 相机状态：显示相机预览。
     */
    private static final int STATE_PREVIEW = 0x01;
    /**
     * 相机状态：等待焦点被锁定。
     */
    private static final int STATE_WAITING_LOCK = 0x02;
    /**
     * 等待曝光被Precapture状态。
     */
    private static final int STATE_WAITING_PRECAPTURE = 0x03;
    /**
     * 相机状态：等待曝光的状态是不是Precapture。
     */
    private static final int STATE_WAITING_NON_PRECAPTURE = 0x04;
    /**
     * 相机状态：拍照。
     */
    private static final int STATE_PICTURE_TAKEN = 0x05;

    @BindView(R.id.surface_view)
    SurfaceView mSurfaceView;

    private SurfaceHolder mHolder;
    private CameraManager mCameraManager;
    private CameraDevice mCameraDevice;
    private CameraCaptureSession mCameraCaptureSession;
    private CaptureRequest mPreviewRequest;
    private CameraInfo mFrontCameraInfo;
    private CameraInfo mBackCameraInfo;
    private CameraInfo mCameraInfo;

    private Handler mHandler;
    private int mState;
    private CaptureRequest.Builder mPreviewRequestBuilder;
    private ImageReader mImageReader;
    private Integer mSensorOrientation;
    //拍照储存文件
    private File mFile;

    /**
     *
     * 一个信号量以防止应用程序在关闭相机之前退出。
     */
    private Semaphore mCameraOpenCloseLock = new Semaphore(1);
    private HandlerThread mBackgroundThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera2);
        ButterKnife.bind(this);
        mHolder = mSurfaceView.getHolder();
        mFrontCameraInfo = new CameraInfo(CameraInfo.DIRECTION_FRONT);
        mBackCameraInfo = new CameraInfo(CameraInfo.DIRECTION_BACK);
        //创建文件
        mFile = new File(getExternalFilesDir(null), "pic-self.jpg");
        mHolder.addCallback(mSurfaceHolderCallback);
        mHolder.setKeepScreenOn(true);
    }

    private SurfaceHolder.Callback mSurfaceHolderCallback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            Log.i(TAG, "surfaceCreated");
            openCamera();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            closeCamera();
            stopBackgroundThread();
        }
    };

    private void closeCamera() {
        try {
            mCameraOpenCloseLock.acquire();
            if (null != mCameraCaptureSession) {
                mCameraCaptureSession.close();
                mCameraCaptureSession = null;
            }
            if (null != mCameraDevice) {
                mCameraDevice.close();
                mCameraDevice = null;
            }
            if (null != mImageReader) {
                mImageReader.close();
                mImageReader = null;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted while trying to lock camera closing.", e);
        } finally {
            mCameraOpenCloseLock.release();
        }
    }

    private void stopBackgroundThread() {
        mBackgroundThread.quitSafely();
        try {
            mBackgroundThread.join();
            mBackgroundThread = null;
            mHandler = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void startBackgroundThread() {
        mBackgroundThread = new HandlerThread("CameraBackground");
        mBackgroundThread.start();
        mHandler = new Handler(mBackgroundThread.getLooper());
    }

    private void initConfig() {
        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            // 获取可用摄像头
            assert mCameraManager != null;
            for (String cameraId : mCameraManager.getCameraIdList()) {
                // 获取相机的相关参数
                CameraCharacteristics cameraCharacteristics = mCameraManager.getCameraCharacteristics(cameraId);

                StreamConfigurationMap streamConfigurationMap = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                if (streamConfigurationMap == null) {
                    continue;
                }
                // 对于静态图像拍摄，使用最大的可用尺寸。
                Size largest = Collections.max(Arrays.asList(streamConfigurationMap.getOutputSizes(ImageFormat.JPEG)), new CompareSizesByArea());
                mImageReader = ImageReader.newInstance(largest.getWidth(), largest.getHeight(), ImageFormat.JPEG, /*maxImages*/2);
                //添加ImageAvailableListener事件，当图片可得到的时候回到，也就是点击拍照的时候
                mImageReader.setOnImageAvailableListener(mOnImageAvailableListener, mHandler);

                // 获取手机旋转的角度以调整图片的方向
                int displayRotation = this.getWindowManager().getDefaultDisplay().getRotation();
                //noinspection ConstantConditions
                mSensorOrientation = cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
                boolean swappedDimensions = false;
                switch (displayRotation) {
                    case Surface.ROTATION_0:
                    case Surface.ROTATION_180:
                        // 横屏
                        if (mSensorOrientation == 90 || mSensorOrientation == 270) {
                            swappedDimensions = true;
                        }
                        break;
                    case Surface.ROTATION_90:
                    case Surface.ROTATION_270:
                        // 竖屏
                        if (mSensorOrientation == 0 || mSensorOrientation == 180) {
                            swappedDimensions = true;
                        }
                        break;
                    default:
                        Log.e(TAG, "Display rotation is invalid: " + displayRotation);
                }

                // 摄像头
                Integer facing = cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
                if (facing == null) {
                    continue;
                }

                if (facing == CameraCharacteristics.LENS_FACING_FRONT) {
                    mFrontCameraInfo.mCameraId = cameraId;
                    mFrontCameraInfo.mAvailable = true;
                    checkCameraConfig(cameraCharacteristics, mFrontCameraInfo);
                } else if (facing == CameraCharacteristics.LENS_FACING_BACK) {
                    mBackCameraInfo.mCameraId = cameraId;
                    mBackCameraInfo.mAvailable = true;
                    checkCameraConfig(cameraCharacteristics, mBackCameraInfo);
                } else {
                    Log.i(TAG, "其他摄像头");
                }
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        initCameraId();

        Log.i(TAG, mFrontCameraInfo + " / " + mBackCameraInfo);
    }

    private void initCameraId() {
        if (mBackCameraInfo.mCameraId != null) {
            mCameraInfo = mBackCameraInfo;
            return;
        }
        if (mFrontCameraInfo.mCameraId != null) {
            mCameraInfo = mFrontCameraInfo;
            return;
        }
        Log.e(TAG, "相机不可用");
    }

    private void checkCameraConfig(CameraCharacteristics cameraCharacteristics, CameraInfo cameraInfo) {
        // 检查闪光灯是否支持
        Boolean available = cameraCharacteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
        cameraInfo.mFlashSupported = available == null ? false : available;
    }

    private ImageReader.OnImageAvailableListener mOnImageAvailableListener = new ImageReader.OnImageAvailableListener() {
        @Override
        public void onImageAvailable(ImageReader reader) {
            //当图片可得到的时候获取图片并保存
            mHandler.post(new ImageSaver(reader.acquireNextImage(), mFile));
        }
    };

    /**
     *  打开摄像头
     */
    public void openCamera() {
        startBackgroundThread();
        initConfig();
        preview();
    }

    private void preview() {
        try {
            if (!mCameraOpenCloseLock.tryAcquire(2500, TimeUnit.MILLISECONDS)) {
                throw new RuntimeException("Time out waiting to lock camera opening.");
            }
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Log.i(TAG, "preview mCameraId : " + mCameraInfo.mCameraId + " , thread : " + Thread.currentThread().getName());
            mCameraManager.openCamera(mCameraInfo.mCameraId, mCameraDeviceStateCallback, mHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private CameraDevice.StateCallback mCameraDeviceStateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            Log.i(TAG, "CameraDevice 开始连接");
            mCameraOpenCloseLock.release();
            mCameraDevice = camera;
            try {
                //创建一个CameraCaptureSession来进行相机预览。
                camera.createCaptureSession(Arrays.asList(mHolder.getSurface(), mImageReader.getSurface()), mCameraCaptureSessionStateCallback, mHandler);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {
            Log.i(TAG, "CameraDevice 断开连接");
            mCameraOpenCloseLock.release();
            camera.close();
            mCameraDevice = null;
        }

        @Override
        public void onError(@NonNull CameraDevice camera, int error) {
            Log.e(TAG, "CameraDevice 出现错误 " + error);
            mCameraOpenCloseLock.release();
            camera.close();
            mCameraDevice = null;
            finish();
        }
    };

    private CameraCaptureSession.StateCallback mCameraCaptureSessionStateCallback = new CameraCaptureSession.StateCallback() {
        @Override
        public void onConfigured(@NonNull CameraCaptureSession session) {
            if (mCameraDevice == null) {
                return;
            }
            // 会话准备好后，我们开始显示预览
            mCameraCaptureSession = session;
            // 发送请求
            try {
                mState = STATE_PREVIEW;
                createPreviewRequest();
                mCameraCaptureSession.setRepeatingRequest(mPreviewRequest, mCaptureCallback, mHandler);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onConfigureFailed(@NonNull CameraCaptureSession session) {
            Log.e(TAG, "会话配置错误，连接失败");
        }

    };

    private void createPreviewRequest() throws CameraAccessException {
        //设置了一个具有输出Surface的CaptureRequest.Builder。
        mPreviewRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
        mPreviewRequestBuilder.addTarget(mHolder.getSurface());
        // 自动对焦
        mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
        // 闪光灯
        setAutoFlash(mPreviewRequestBuilder);
        // 开启相机预览并添加事件
        mPreviewRequest = mPreviewRequestBuilder.build();
    }

    private CameraCaptureSession.CaptureCallback mCaptureCallback = new CameraCaptureSession.CaptureCallback() {
        @Override
        public void onCaptureProgressed(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull CaptureResult partialResult) {
            super.onCaptureProgressed(session, request, partialResult);
            process(partialResult);
        }

        @Override
        public void onCaptureCompleted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull TotalCaptureResult result) {
            super.onCaptureCompleted(session, request, result);
            process(result);
        }

        private void process(@NonNull CaptureResult result) {
            switch (mState) {
                case STATE_PREVIEW: {
                    //预览状态
                    break;
                }
                case STATE_WAITING_LOCK: {
                    Log.i(TAG, "CameraCaptureSession.CaptureCallback process STATE_WAITING_LOCK");
                    //等待对焦
                    Integer afState = result.get(CaptureResult.CONTROL_AF_STATE);
                    if (afState == null) {
                        captureStillPicture();
                    } else if (CaptureResult.CONTROL_AF_STATE_FOCUSED_LOCKED == afState ||
                            CaptureResult.CONTROL_AF_STATE_NOT_FOCUSED_LOCKED == afState) {
                        // CONTROL_AE_STATE can be null on some devices
                        Integer aeState = result.get(CaptureResult.CONTROL_AE_STATE);
                        if (aeState == null || aeState == CaptureResult.CONTROL_AE_STATE_CONVERGED) {
                            mState = STATE_PICTURE_TAKEN;
                            captureStillPicture();
                        } else {
                            runPrecaptureSequence();
                        }
                    }
                    break;
                }
                case STATE_WAITING_PRECAPTURE: {
                    Log.i(TAG, "CameraCaptureSession.CaptureCallback process STATE_WAITING_PRECAPTURE");
                    // CONTROL_AE_STATE can be null on some devices
                    Integer aeState = result.get(CaptureResult.CONTROL_AE_STATE);
                    if (aeState == null ||
                            aeState == CaptureResult.CONTROL_AE_STATE_PRECAPTURE ||
                            aeState == CaptureRequest.CONTROL_AE_STATE_FLASH_REQUIRED) {
                        mState = STATE_WAITING_NON_PRECAPTURE;
                    }
                    break;
                }
                case STATE_WAITING_NON_PRECAPTURE: {
                    Log.i(TAG, "CameraCaptureSession.CaptureCallback process STATE_WAITING_NON_PRECAPTURE");
                    // CONTROL_AE_STATE can be null on some devices
                    Integer aeState = result.get(CaptureResult.CONTROL_AE_STATE);
                    if (aeState == null || aeState != CaptureResult.CONTROL_AE_STATE_PRECAPTURE) {
                        mState = STATE_PICTURE_TAKEN;
                        captureStillPicture();
                    }
                    break;
                }
            }
        }
    };

    /**
     *  切换摄像头
     */
    public void switchCamera(View view) {
        if (mCameraInfo == null) {
            return;
        }
        switch (mCameraInfo.mDirection) {
            case CameraInfo.DIRECTION_FRONT:
                if (mBackCameraInfo.mAvailable) {
                    mCameraInfo = mBackCameraInfo;
                }
                break;
            case CameraInfo.DIRECTION_BACK:
                if (mFrontCameraInfo.mAvailable) {
                    mCameraInfo = mFrontCameraInfo;
                }
                break;
        }

        preview();
    }

    /**
     *  拍照
     */
    public void capture(View view) {
        lockFocus();
    }

    /**
     * 将焦点锁定为静态图像捕获的第一步。（对焦）
     */
    private void lockFocus() {
        try {
            // 修改状态
            mState = STATE_WAITING_LOCK;
            // 相机对焦
            mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, CameraMetadata.CONTROL_AF_TRIGGER_START);
            CaptureRequest captureRequest = mPreviewRequestBuilder.build();
            mCameraCaptureSession.capture(captureRequest, mCaptureCallback, mHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解锁焦点
     */
    private void unlockFocus() {
        try {
            // 重置自动对焦
            mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER,
                    CameraMetadata.CONTROL_AF_TRIGGER_CANCEL);
            setAutoFlash(mPreviewRequestBuilder);
            mCameraCaptureSession.capture(mPreviewRequestBuilder.build(), mCaptureCallback, mHandler);
            // 将相机恢复正常的预览状态。
            mState = STATE_PREVIEW;
            // 打开连续取景模式
            mCameraCaptureSession.setRepeatingRequest(mPreviewRequest, mCaptureCallback, mHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * 拍摄静态图片。
     */
    private void captureStillPicture() {
        try {
            if ( null == mCameraDevice) {
                return;
            }
            // 这是用来拍摄照片的CaptureRequest.Builder。
            final CaptureRequest.Builder captureBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
            captureBuilder.addTarget(mImageReader.getSurface());

            // 使用相同的AE和AF模式作为预览。
            captureBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
            setAutoFlash(captureBuilder);
            // 方向
            int rotation = this.getWindowManager().getDefaultDisplay().getRotation();
            int realOrientation = getOrientation(rotation);
            Log.i(TAG, "realOrientation " + realOrientation + " / rotation " + rotation);
            captureBuilder.set(CaptureRequest.JPEG_ORIENTATION, realOrientation);

            CameraCaptureSession.CaptureCallback CaptureCallback = new CameraCaptureSession.CaptureCallback() {
                @Override
                public void onCaptureCompleted(@NonNull CameraCaptureSession session,
                                               @NonNull CaptureRequest request,
                                               @NonNull TotalCaptureResult result) {
                    Log.i(TAG, "Saved: " + mFile);
                    unlockFocus();
                }
            };
            //停止连续取景
            mCameraCaptureSession.stopRepeating();
            mCameraCaptureSession.abortCaptures();
            //捕获图片
            mCameraCaptureSession.capture(captureBuilder.build(), CaptureCallback, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 运行捕获静止图像的预捕获序列。 当我们从{@link #lockFocus（）}的{@link #mCaptureCallback}中得到响应时，应该调用此方法。
     */
    private void runPrecaptureSequence() {
        try {
            // 这是如何告诉相机触发的。
            mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER_START);
            // 告诉 mCaptureCallback 等待preapture序列被设置.
            mState = STATE_WAITING_PRECAPTURE;
            mCameraCaptureSession.capture(mPreviewRequestBuilder.build(), mCaptureCallback, mHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void setAutoFlash(CaptureRequest.Builder captureBuilder) {
        if (mCameraInfo.mFlashSupported) {
            captureBuilder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
        }
    }

    /**
     * Retrieves the JPEG orientation from the specified screen rotation.
     *
     * @param rotation The screen rotation.
     * @return The JPEG orientation (one of 0, 90, 270, and 360)
     */
    private int getOrientation(int rotation) {
        // Sensor orientation is 90 for most devices, or 270 for some devices (eg. Nexus 5X)
        // We have to take that into account and rotate JPEG properly.
        // For devices with orientation of 90, we simply return our mapping from ORIENTATIONS.
        // For devices with orientation of 270, we need to rotate the JPEG 180 degrees.
        return (ORIENTATIONS.get(rotation) + mSensorOrientation + 270) % 360;
    }

    public void open(View view) {
        openCamera();
    }
}
