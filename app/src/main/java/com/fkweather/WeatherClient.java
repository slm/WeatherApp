package com.fkweather;

import com.fkweather.models.LocateWeather;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface WeatherClient {

    @GET("/data/2.5/weather")
    void  getWeather(@Query("lat") Double lat,@Query("lon") Double lon, @Query("APPID") String APPID, Callback<LocateWeather> rc );//q is must format be city,country

}
