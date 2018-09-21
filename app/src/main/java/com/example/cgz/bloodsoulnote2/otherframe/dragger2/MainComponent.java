package com.example.cgz.bloodsoulnote2.otherframe.dragger2;

import dagger.Component;

//第一步 添加@Component
//第二步 添加module
@Component(modules = {MainModule.class})
public interface MainComponent {
    //第三步  写一个方法 绑定Activity /Fragment
    void inject(Dragger2Activity activity); // 必须是指定的activity类型
}
