package com.example.cgz.bloodsoulnote2.net.retrofit.request;

import com.example.cgz.bloodsoulnote2.databinding.demo1.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by cgz on 18-3-30.
 */

public interface IpService {

    @GET("/weather_mini")
    Call getIpMsg(@Query("city")String city);

    @POST("tips")
    @FormUrlEncoded
    Call<User> getUsers3(@Field("api_key") String api_key, @Field("module") String module, @Field("api_sign") String api_sign);


}
