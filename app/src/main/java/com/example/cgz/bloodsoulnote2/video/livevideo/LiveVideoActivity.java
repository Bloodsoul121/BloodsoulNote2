package com.example.cgz.bloodsoulnote2.video.livevideo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.main.MainActivity;

public class LiveVideoActivity extends AppCompatActivity {

    public static final String RTMPURL_MESSAGE = "rtmppush.hx.com.rtmppush.rtmpurl";
    private Button _startRtmpPushButton = null;
    private EditText _rtmpUrlEditText = null;

    private View.OnClickListener _startRtmpPushOnClickedEvent = new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
            Intent i = new Intent(LiveVideoActivity.this, PushStreamActivity.class);
            String rtmpUrl = _rtmpUrlEditText.getText().toString();
            i.putExtra(RTMPURL_MESSAGE, rtmpUrl);
            LiveVideoActivity.this.startActivity(i);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_video);
        InitUI();
    }

    private void InitUI(){
        _rtmpUrlEditText = (EditText)findViewById(R.id.rtmpUrleditText);
        _startRtmpPushButton = (Button)findViewById(R.id.startRtmpButton);
        _rtmpUrlEditText.setText("rtmp://192.168.1.104:1935/live/12345");
        _startRtmpPushButton.setOnClickListener(_startRtmpPushOnClickedEvent);
    }

}
