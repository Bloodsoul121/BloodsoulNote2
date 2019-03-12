package com.example.cgz.bloodsoulnote2.media;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.media.draw.DrawPictureActivity;

public class MediaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
    }

    public void drawPicture(View view) {
        startActivity(new Intent(this, DrawPictureActivity.class));
    }
}
