package com.example.cgz.bloodsoulnote2.inject.annotation;

/**
 * Created by cgz on 18-3-31.
 */

public class Person {

    @Name("莫莫")
    private String name;

    @Gender(gender = Gender.GenderType.Other)
    private String gender;

    @Profile(id = 41, height = 200, nativePlace = "纳萨里克")
    private String profile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
