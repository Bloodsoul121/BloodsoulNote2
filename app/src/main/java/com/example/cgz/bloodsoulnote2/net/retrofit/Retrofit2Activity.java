package com.example.cgz.bloodsoulnote2.net.retrofit;

import android.os.Bundle;
import android.view.View;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;
import com.example.cgz.bloodsoulnote2.net.retrofit.request.IpService;
import com.orhanobut.logger.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit2Activity extends BaseActivity {

    private static final String TAG = "Retrofit2Activity";

    private String url = "http://xj3gosapi.3gtest2.gionee.com/comment/index/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit2);
    }

    public void clickBtn1(View view) {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
//                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            IpService ipService = retrofit.create(IpService.class);
            Call call = ipService.getUsers3("9dac6633be895da152187b9c1a5c0042", "topic", "dc13c4012caeb48a6b05a961397d3318");
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    Logger.i(TAG, "onResponse");
                }

                @Override
                public void onFailure(Call call, Throwable throwable) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
