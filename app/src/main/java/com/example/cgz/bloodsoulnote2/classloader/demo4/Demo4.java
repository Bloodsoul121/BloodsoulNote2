package com.example.cgz.bloodsoulnote2.classloader.demo4;

/**
 * Created by cgz on 18-4-17.
 */

public class Demo4 {

    public static void fun() {

        System.out.println("fun ");

        {
            int a = 10;
            System.out.println("a - " + a);
        }

        System.out.println("fun 2");

    }

    /**
     * 04-17 15:04:21.972 26084-26084/com.example.cgz.bloodsoulnote2 I/System.out: fun
     04-17 15:04:21.973 26084-26084/com.example.cgz.bloodsoulnote2 I/System.out: a - 10
     04-17 15:04:21.973 26084-26084/com.example.cgz.bloodsoulnote2 I/System.out: fun 2
     */

}
