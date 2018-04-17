package com.example.cgz.bloodsoulnote2.classloader.demo1;

/**
 * Created by cgz on 18-4-17.
 */

public class SubClass extends SuperClass
{
    static
    {
        System.out.println("SubClass init");
    }

    static int a;

    public SubClass()
    {
        System.out.println("init SubClass");
    }
}
