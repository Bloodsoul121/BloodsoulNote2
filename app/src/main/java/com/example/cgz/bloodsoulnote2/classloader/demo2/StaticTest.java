package com.example.cgz.bloodsoulnote2.classloader.demo2;

/**
 * Created by cgz on 18-4-17.
 */

public class StaticTest {

    static StaticTest st = new StaticTest();

    static
    {
        System.out.println("1");
    }

    {
        System.out.println("2");
    }

    StaticTest()
    {
        System.out.println("3");
        System.out.println("a="+a+",b="+b);
    }

    public static void staticFunction(){
        System.out.println("4");
    }

    int a=110;
    static int b =112;  // 还没来得及加载

    /**
     * 04-17 14:20:28.273 9650-9650/com.example.cgz.bloodsoulnote2 I/System.out: 2
     * 04-17 14:20:28.273 9650-9650/com.example.cgz.bloodsoulnote2 I/System.out: 3
     * 04-17 14:20:28.273 9650-9650/com.example.cgz.bloodsoulnote2 I/System.out: a=110,b=0
     * 04-17 14:20:28.273 9650-9650/com.example.cgz.bloodsoulnote2 I/System.out: 1
     * 04-17 14:20:28.273 9650-9650/com.example.cgz.bloodsoulnote2 I/System.out: 4
     */

    /**
     * static修饰的，是从上向下，依次执行的。static变量和代码块优先执行
     */

    /**
     * 对象的初始化是先初始化成员变量再执行构造方法
     */

}
