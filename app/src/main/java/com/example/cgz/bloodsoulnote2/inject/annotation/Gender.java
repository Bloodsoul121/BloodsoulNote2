package com.example.cgz.bloodsoulnote2.inject.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by cgz on 18-3-31.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Gender {
    public enum GenderType {
        Male("男"),
        Female("女"),
        Other("中性");

        private String genderStr;

        GenderType(String genderStr) {
            this.genderStr = genderStr;
        }

        @Override
        public String toString() {
            return genderStr;
        }
    }

    GenderType gender() default GenderType.Male;
}
