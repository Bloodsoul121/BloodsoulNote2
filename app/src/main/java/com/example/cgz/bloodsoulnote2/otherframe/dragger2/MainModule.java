package com.example.cgz.bloodsoulnote2.otherframe.dragger2;

import dagger.Module;
import dagger.Provides;

//第一步 添加@Module 注解
@Module(includes = CModule.class)
public class MainModule {

    //第二步 使用Provider 注解 实例化对象
    @Provides
    A providerA(C c) {
        return new A(c);
    }

    /***
     * 构造方法需要其他参数时候
     */
    @Provides
    B providerB() {
        return new B();
    }

}
