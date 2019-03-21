package com.example.cgz.bloodsoulnote2.media.opengl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cgz.bloodsoulnote2.R;

public class OpenGLActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomOpenGLView customOpenGLView = new CustomOpenGLView(this);
        setContentView(customOpenGLView);
//        setContentView(R.layout.activity_open_gl);
    }
}
