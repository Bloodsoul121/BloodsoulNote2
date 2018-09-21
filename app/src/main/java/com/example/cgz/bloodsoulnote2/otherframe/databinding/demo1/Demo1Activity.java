package com.example.cgz.bloodsoulnote2.otherframe.databinding.demo1;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.databinding.ActivityBindingdemo1Binding;

public class Demo1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bindingdemo1);

        ActivityBindingdemo1Binding mBinding = DataBindingUtil.setContentView(this, R.layout.activity_bindingdemo1);
        UserViewModel viewModel = new UserViewModel(this, mBinding);
    }

}
