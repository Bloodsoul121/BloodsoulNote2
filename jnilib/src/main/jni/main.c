//
// Created by cgz on 18-3-27.
//

#include "com_example_jnilib_JniLib.h"

JNIEXPORT jstring JNICALL Java_com_example_jnilib_JniLib_getStringFromC
  (JNIEnv *env, jclass jclz) {
    return (*env)->NewStringUTF(env, "string from JNI ");
  }