package com.example.jnilib;

/**
 * Created by cgz on 18-3-27.
 */

public class JniLib {
    static {
        System.loadLibrary("nativeUtil");
    }

    public native static String getStringFromC();
}
