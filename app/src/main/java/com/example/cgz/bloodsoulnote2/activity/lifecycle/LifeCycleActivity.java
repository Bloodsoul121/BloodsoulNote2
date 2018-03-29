package com.example.cgz.bloodsoulnote2.activity.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.View;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;

public class LifeCycleActivity extends BaseActivity {

    private static final String TAG = "Activity-LifeCycle";

    /**
     * 表示Activity正在被创建，这是生命周期的第一个方法
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        log(TAG, "onCreate");
    }

    /**
     * 调用场景: (1)启动模式为singleTask时, 复用该Activity, 调用
     *          (2)启动模式为singleTop时, 复用该Activity, 调用
     * 执行顺序: onNewIntent()---->onResart()------>onStart()----->onResume()
     * 重用时，会让该实例回到栈顶，因此在它上面的实例将会被移出栈。如果栈中不存在该实例，将会创建新的实例放入栈中。
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);  // 必须set, 否则, 后续的getIntent()都是得到老的Intent
        log(TAG, "onNewIntent");
    }

    /**
     * 表示Activity正在重新启动, 一般是由于用户的行为所导致的
     * 1. 跳转到另一个Activity, 然后回来这个Activity
     * 2. 按Home键后, 重新回到该Activity
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        log(TAG, "onRestart");
    }

    /**
     * 表示Activity正在被启动，并且即将开始
     * 但是onStart时Activity还正在加载其他内容，正在向我们展示，用户还无法看到，即无法交互。
     */
    @Override
    protected void onStart() {
        super.onStart();
        log(TAG, "onStart");
    }

    /**
     * 这个时候用户已经可以看到界面了
     */
    @Override
    protected void onResume() {
        super.onResume();
        log(TAG, "onResume");
    }

    /**
     * 可以做一些存储数据、停止动画的工作，但是不能太耗时
     * 如果是由于启动新的Activity而唤醒的该状态，那会影响到新Activity的显示，
     * 原因是onPause必须执行完，新的Activity的onResume才会执行。 !!!!!!!!!!!!!!!!!!!!!!!!
     */
    @Override
    protected void onPause() {
        super.onPause();
        log(TAG, "onPause");
    }

    /**
     * 可以做一些稍微重量级的回收工作
     */
    @Override
    protected void onStop() {
        super.onStop();
        log(TAG, "onStop");
    }

    /**
     * 表示Activity即将被销毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        log(TAG, "onDestroy");
    }

    /**
     * 1. 横竖屏调用
     * 2. Home键退出
     * 与onPause方法没有先后之分!!!
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        log(TAG, "onSaveInstanceState 1");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        log(TAG, "onSaveInstanceState 2");
    }

    /**
     * 1. 横竖屏调用
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        log(TAG, "onRestoreInstanceState 1");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        log(TAG, "onRestoreInstanceState 2");
    }

    /**
     * 验证, 当前Activity弹出的dialog, 并不影响当前Activity的生命周期
     */
    public void showDialog(View view) {
        showSimpleDialog("AlertDialog");
    }

    public void startSecondActivity(View view) {
        startActivity(SecondActivity.class);
    }


    /*
     * *** 横竖屏调用 日志:
     *
     * 03-01 11:31:42.379 29979-29979/com.example.cgz.bloodsoulnote2 I/Activity-LifeCycle: onPause
     * 03-01 11:31:42.379 29979-29979/com.example.cgz.bloodsoulnote2 I/Activity-LifeCycle: onSaveInstanceState 1
     * 03-01 11:31:42.379 29979-29979/com.example.cgz.bloodsoulnote2 I/Activity-LifeCycle: onStop
     * 03-01 11:31:42.380 29979-29979/com.example.cgz.bloodsoulnote2 I/Activity-LifeCycle: onDestroy
     * 03-01 11:31:42.447 29979-29979/com.example.cgz.bloodsoulnote2 I/Activity-LifeCycle: onCreate
     * 03-01 11:31:42.449 29979-29979/com.example.cgz.bloodsoulnote2 I/Activity-LifeCycle: onStart
     * 03-01 11:31:42.449 29979-29979/com.example.cgz.bloodsoulnote2 I/Activity-LifeCycle: onRestoreInstanceState 1
     * 03-01 11:31:42.450 29979-29979/com.example.cgz.bloodsoulnote2 I/Activity-LifeCycle: onResume
     *
     *
     * *** Home键退出和重新进入 日志:
     *
     * 03-01 13:41:00.123 13342-13342/com.example.cgz.bloodsoulnote2 I/Activity-LifeCycle: onPause
     * 03-01 13:41:00.610 13342-13342/com.example.cgz.bloodsoulnote2 I/Activity-LifeCycle: onSaveInstanceState 1
     * 03-01 13:41:00.610 13342-13342/com.example.cgz.bloodsoulnote2 I/Activity-LifeCycle: onStop
     *
     * 03-01 13:41:20.434 13342-13342/com.example.cgz.bloodsoulnote2 I/Activity-LifeCycle: onRestart
     * 03-01 13:41:20.435 13342-13342/com.example.cgz.bloodsoulnote2 I/Activity-LifeCycle: onStart
     * 03-01 13:41:20.435 13342-13342/com.example.cgz.bloodsoulnote2 I/Activity-LifeCycle: onResume
     *
     *
     * *** onStart和onResume可以这么理解 :
     *
     * onStart里面要做一些初始化的操作，载入资源，onResume就与用户交互展示，当我们打开另外一个activity，再返回
     * 这个页面的时候如果前一个activity还没被finish掉，那么他只需要执行onResume这个，减少了第二次初始化资源的操作。
     *
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        log(TAG, "onCreateOptionsMenu");  // 直接不调用啦, api太低
        return super.onCreateOptionsMenu(menu);
    }
}
