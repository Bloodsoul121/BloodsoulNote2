package com.example.cgz.bloodsoulnote2.databinding.demo1;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.databinding.ActivityBindingdemo1Binding;

public class Demo1Activity extends AppCompatActivity {

    private ActivityBindingdemo1Binding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bindingdemo1);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_bindingdemo1);
        User user = new User("user", "12");
        mBinding.setUser(user);
    }

    public void clickBtn1(View view) {
        mBinding.setUser(new User("blood", "18"));
    }
}
