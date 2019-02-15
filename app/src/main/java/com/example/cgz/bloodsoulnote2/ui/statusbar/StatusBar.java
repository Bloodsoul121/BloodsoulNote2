package com.example.cgz.bloodsoulnote2.ui.statusbar;


import android.app.Activity;
import android.os.Build;

public class StatusBar {

    public static void setStatusBarColor(Activity activity, int statusColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // 5.0
            StatusbarLollipop.setStatusBarColor(activity, statusColor);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { // 4.4
            StatusbarKitKat.setStatusBarColor(activity, statusColor);
        }
    }
}
