package com.fkweather;

import com.fkweather.models.LocateWeather;
import com.fkweather.models.Message;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;


public interface MessageClient {


    @POST("/getmessage/")
    void  getMessage(@Query("t") int t, Callback<Message[]> msg );//q is must format be city,country

    @FormUrlEncoded
    @POST("/addmessage/")
    void  addMessage(@Field("text") String text,@Field("userlink") String userlink,@Field("username") String username,@Field("temperature") int temperature , Callback<String> msg );//q is must format be city,country



}
