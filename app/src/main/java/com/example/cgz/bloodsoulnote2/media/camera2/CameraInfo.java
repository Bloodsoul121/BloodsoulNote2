package com.example.cgz.bloodsoulnote2.media.camera2;

public class CameraInfo {

    public static final String DIRECTION_FRONT = "FRONT";
    public static final String DIRECTION_BACK = "BACK";

    public String mDirection;
    public String mCameraId;
    public boolean mAvailable;
    public boolean mFlashSupported;

    public CameraInfo(String direction) {
        mDirection = direction;
    }

    @Override
    public String toString() {
        return "CameraInfo{" +
                "mDirection='" + mDirection + '\'' +
                ", mCameraId='" + mCameraId + '\'' +
                ", mAvailable=" + mAvailable +
                ", mFlashSupported=" + mFlashSupported +
                '}';
    }
}
