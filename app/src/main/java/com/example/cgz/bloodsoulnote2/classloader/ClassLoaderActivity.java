package com.example.cgz.bloodsoulnote2.classloader;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.ListViewBaseActivity;
import com.example.cgz.bloodsoulnote2.classloader.demo1.SubClass;
import com.example.cgz.bloodsoulnote2.classloader.demo2.StaticTest;
import com.example.cgz.bloodsoulnote2.classloader.demo3.Demo3;
import com.example.cgz.bloodsoulnote2.classloader.demo4.Demo4;

public class ClassLoaderActivity extends ListViewBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_loader);
    }

    @Override
    protected void initData() {
        // 对于静态字段，只有直接定义这个字段的类才会被初始化，因此通过其子类来引用父类中定义的静态字段，
        // 只会触发父类的初始化而不会触发子类的初始化。
        mDatas.add("类加载初始化, 看log");
        mDatas.add("有趣的实验, 看log");
        mDatas.add("静态代码块，会在类被加载时自动执行？, 看log");
        mDatas.add("局部代码块, 看log");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                System.out.println(SubClass.value);

                /**
                 *  I/System.out: SSClass
                 *  I/System.out: SuperClass init!
                 *  I/System.out: 123
                 */

                break;
            case 1:
                StaticTest.staticFunction();
                break;
            case 2:
                Demo3.done();
                break;
            case 3:
                Demo4.fun();
                break;
        }
    }
}
