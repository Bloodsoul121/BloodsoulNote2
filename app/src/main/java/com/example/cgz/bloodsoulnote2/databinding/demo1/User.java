package com.example.cgz.bloodsoulnote2.databinding.demo1;

/**
 * Created by cgz on 18-4-20.
 */

public class User {
    private String name;
    private String age;

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public String getAge() {
        return this.age;
    }
}
