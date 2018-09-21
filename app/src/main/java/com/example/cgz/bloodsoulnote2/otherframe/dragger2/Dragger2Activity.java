package com.example.cgz.bloodsoulnote2.otherframe.dragger2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.cgz.bloodsoulnote2.R;

import javax.inject.Inject;

public class Dragger2Activity extends AppCompatActivity {

    /***
     * 第二步  使用Inject 注解，获取到A 对象的实例
     */
    @Inject
    A a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragger2);

        /***
         * 第一步 添加依赖关系
         */
        //第一种方式
        DaggerMainComponent.create().inject(this);

        //第二种方式
//        DaggerMainComponent.builder().build().inject(this);

    }

    public void clickBtn1(View view) {
        /***
         * 第三步  调用A 对象的方法
         */
        a.eat();
    }
}
