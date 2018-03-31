package com.example.cgz.bloodsoulnote2.inject.annotation;

import android.util.Log;

import java.lang.reflect.Field;

/**
 * Created by cgz on 18-3-31.
 */

public class CustomUtils {

    private static final String TAG = "CustomUtils";

    public static void getInfo(Class<?> clazz) {

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Name.class)) {
                Name name = field.getAnnotation(Name.class);
                String value = name.value();
                Log.i(TAG, "name --> " + value);
            }

            if (field.isAnnotationPresent(Gender.class)) {
                Gender gender = field.getAnnotation(Gender.class);
                Gender.GenderType value = gender.gender();
                Log.i(TAG, "gender --> " + value);
            }

            if (field.isAnnotationPresent(Profile.class)) {
                Profile profile = field.getAnnotation(Profile.class);
                int id = profile.id();
                int height = profile.height();
                String place = profile.nativePlace();
                Log.i(TAG, "profile --> " + id + ", " + height + ", " + place);
            }
        }

    }

}
