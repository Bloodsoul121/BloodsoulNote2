package com.example.cgz.bloodsoulnote2.otherframe.databinding.demo3;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.databinding.ActivityDemo3Binding;

public class Demo3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDemo3Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_demo3);
        ManViewModel vm = new ManViewModel(this, binding);
    }
}
