package com.example.cgz.bloodsoulnote2.media.opengl.img;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cgz.bloodsoulnote2.R;

import java.io.IOException;

public class OpenGLImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(new ImageGLSurfaceView(this));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
