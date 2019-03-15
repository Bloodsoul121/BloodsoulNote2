package com.example.cgz.bloodsoulnote2.media.cameraAuthority;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cgz.bloodsoulnote2.R;

public class Camera2AuthorityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera2_authority);
        if (null == savedInstanceState) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, Camera2BasicFragment.newInstance())
                    .commit();
        }
    }
}
