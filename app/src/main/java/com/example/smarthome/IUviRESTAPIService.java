package com.example.smarthome;

import com.example.smarthome.models.Uvi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface IUviRESTAPIService {

    //http://api.openweathermap.org/data/2.5/uvi?appid={appid}&lat={lat}&lon={lon}
    @GET("/data/2.5/uvi")
    Call<Uvi> getUvi(@Query("lat") String latitud, @Query("lon") String longitud, @Query("appid") String appid);

}
