package com.example.cgz.bloodsoulnote2.media.camera2;


import android.util.Size;

import java.util.Comparator;

class CompareSizesByArea implements Comparator<Size> {
    @Override
    public int compare(Size o1, Size o2) {
        // 我们在这里投放，以确保乘法不会溢出
        return Long.signum((long) o1.getWidth() * o1.getHeight() - (long) o2.getWidth() * o2.getHeight());
    }
}
