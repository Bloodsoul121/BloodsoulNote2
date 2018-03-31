package com.example.cgz.bloodsoulnote2.net.retrofit;

import android.os.Bundle;
import android.view.View;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;
import com.example.cgz.bloodsoulnote2.net.retrofit.request.IpService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Retrofit2Activity extends BaseActivity {

    private static final String TAG = "Retrofit2Activity";

    private String url = "http://wthrcdn.etouch.cn";

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
//                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            IpService ipService = retrofit.create(IpService.class);
            Call call = ipService.getIpMsg("深圳");
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    log(TAG, response.body().toString());
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
