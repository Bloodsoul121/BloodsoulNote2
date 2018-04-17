package com.example.cgz.bloodsoulnote2.classloader.demo3;

/**
 * Created by cgz on 18-4-17.
 */

public class Demo3 {

    static Class[] classArray = {
            MyClass1.class//这样引用该类，必然需要将该类加载到虚拟机中
    };

    public static void done(){
        System.out.println("hello word");
    }

    /*
     * I/System.out: hello word
     */


    /**
     * MyClass1.class 这种使用方式，并不会触发，MyClass1类的初始化。所以并不会执行，MyClass1的静态代码块。
     */

}
