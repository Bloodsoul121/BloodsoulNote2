package com.example.cgz.bloodsoulnote2.classloader.demo1;

/**
 * Created by cgz on 18-4-17.
 */

public class SuperClass extends SSClass
{
    static
    {
        System.out.println("SuperClass init!");
    }

    public static int value = 123;

    public SuperClass()
    {
        System.out.println("init SuperClass");
    }
}
