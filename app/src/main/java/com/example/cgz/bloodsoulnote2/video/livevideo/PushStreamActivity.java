package com.example.cgz.bloodsoulnote2.video.livevideo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.cgz.bloodsoulnote2.R;

public class PushStreamActivity extends AppCompatActivity {

    private String _rtmpUrl;
    private PowerManager.WakeLock _wakeLock;
    private SurfaceView _mSurfaceView;
    private Button _SwitchCameraBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_push_stream);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Intent intent = getIntent();
        _rtmpUrl = intent.getStringExtra(LiveVideoActivity.RTMPURL_MESSAGE);

//        InitAll();

        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        _wakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
    }

//    private void InitAll() {
//        WindowManager wm = this.getWindowManager();
//
//        int width = wm.getDefaultDisplay().getWidth();
//        int height = wm.getDefaultDisplay().getHeight();
//        int iNewWidth = (int) (height * 3.0 / 4.0);
//
//        RelativeLayout rCameraLayout = (RelativeLayout) findViewById(R.id.cameraRelative);
//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
//                RelativeLayout.LayoutParams.MATCH_PARENT);
//        int iPos = width - iNewWidth;
//        layoutParams.setMargins(iPos, 0, 0, 0);
//
//        _mSurfaceView = (SurfaceView) this.findViewById(R.id.surfaceViewEx);
//        _mSurfaceView.getHolder().setFixedSize(HEIGHT_DEF, WIDTH_DEF);
//        _mSurfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//        _mSurfaceView.getHolder().setKeepScreenOn(true);
//        _mSurfaceView.getHolder().addCallback(new SurceCallBack());
//        _mSurfaceView.setLayoutParams(layoutParams);
//
//        InitAudioRecord();
//
//        _SwitchCameraBtn = (Button) findViewById(R.id.SwitchCamerabutton);
//        _SwitchCameraBtn.setOnClickListener(_switchCameraOnClickedEvent);
//
//        RtmpStartMessage();//开始推流
//    }

}
