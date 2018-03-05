package com.example.cgz.bloodsoulnote2.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";

    protected final static String NULL = "";

    private Toast mToast;

    private Handler mHandler = new Handler();

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void Log(String msg) {
        Log(TAG, msg);
    }

    protected void Log(String tag, String msg) {
        if (Config.DEBUG) {
            Log.i(tag, msg);
        }
    }

    protected void logCurrentThread() {
        Log("current thread --> " + Thread.currentThread().getName());
    }

    protected void toast(final String msg) {
        try {
            runOnMain(new Runnable() {
                @Override
                public void run() {
                    if (mToast == null) {
                        mToast = Toast.makeText(BaseActivity.this, NULL, Toast.LENGTH_SHORT);
                    }
                    mToast.setText(msg);
                    mToast.show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startActivity(Class<? extends Activity> clazz) {
        startActivity(clazz, null);
    }

    protected void startActivity(Class<? extends Activity> clazz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clazz);
        if (bundle != null)
            intent.putExtra(this.getPackageName(), bundle);
        startActivity(intent);
    }

    protected void runOnMain(Runnable runnable) {
        runOnUiThread(runnable);
    }

    protected void postDelay(Runnable runnable, long delayTime) {
        mHandler.postDelayed(runnable, delayTime);
    }

    protected void showSimpleDialog(String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.show();
    }

}
