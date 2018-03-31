package com.example.cgz.bloodsoulnote2.net.retrofit.request;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by cgz on 18-3-30.
 */

public interface IpService {

    @GET("/weather_mini")
    Call getIpMsg(@Query("city")String city);

}
