package com.example.cgz.bloodsoulnote2.otherframe.arouter;


import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

@Interceptor(priority = 1)
public class CustomIntercept implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        Log.i("ARouter", "CustomIntercept process");
        callback.onContinue(postcard); // 这里调用了，才能继续传递下去
    }

    @Override
    public void init(Context context) {
        Log.i("ARouter", "CustomIntercept init");
    }
}
